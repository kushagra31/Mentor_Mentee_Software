package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Callback;

import javax.imageio.spi.IIOServiceProvider;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;

import static java.lang.String.valueOf;

public class updatecontroller implements Initializable {
	public TextField prntextfield;
	public TextField prntextfield1;

	public Button updatestudentbutton;
	public Button showdatabasebutton;
	public TableView tableview;
	public ObservableList<ObservableList> data;
	public ComboBox cbox;
	public AnchorPane apane;
	FileInputStream input = new FileInputStream("E:\\bw.jpg");
	javafx.scene.image.Image image = new Image(input);

	public updatecontroller() throws FileNotFoundException {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		BackgroundSize backgroundSize= new BackgroundSize(1.0,1.0,false,false,false,true);
		BackgroundImage myBI= new BackgroundImage(image,
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				backgroundSize);

		apane.setBackground(new Background(myBI));
		cbox.getItems().add("PRN");
		cbox.getItems().add("NAME");
		cbox.getItems().add("EMAILID");
		cbox.getItems().add("ATTENDENCE");
		cbox.getItems().add("IDMENTOR");

		showdatabasebutton.setOnMouseClicked(event -> {
			tableview.getItems().clear();
			tableview.getColumns().clear();
			String prn1 = prntextfield.getText();
			data = FXCollections.observableArrayList();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mentee1", "root", "ravi97292");

				String q = "Select * from users where PRN = ? ";
				PreparedStatement ps = cn.prepareStatement(q);
				ps.setInt(1, Integer.parseInt(valueOf(prn1)));

				ResultSet rs = ps.executeQuery();
				for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {


					final int j = i;
					TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
					col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
						public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> kush) {
							return new SimpleStringProperty(kush.getValue().get(j).toString());
						}
					});

					tableview.getColumns().addAll(col);
					System.out.println("Column [" + i + "] ");
				}


				while (rs.next()) {
					ObservableList<String> row = FXCollections.observableArrayList();
					for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
						row.add(rs.getString(i));
					}
					System.out.println("Row [1] added " + row);
					data.add(row);
				}
				tableview.setItems(data);

				cn.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		});

    updatestudentbutton.setOnMouseClicked(event -> {
	String value = (String) cbox.getValue();
	String prn1 = prntextfield.getText();
	String value1= prntextfield1.getText();
	if(value.equals("PRN")) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mentee1", "root", "ravi97292");

			String q = "update users set PRN = ? where PRN = ? ";
			PreparedStatement ps = cn.prepareStatement(q);
			ps.setInt(1, Integer.parseInt(valueOf(value1)));
			ps.setInt(2, Integer.parseInt(valueOf(prn1)));

			ps.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	if(value.equals("NAME")) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mentee1", "root", "ravi97292");

			String q = "update users set NAME = ? where PRN = ? ";
			PreparedStatement ps = cn.prepareStatement(q);
			ps.setString(1, valueOf(value1));
			ps.setInt(2, Integer.parseInt(valueOf(prn1)));

			ps.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	if(value.equals("EMAILID")) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mentee1", "root", "ravi97292");

			String q = "update users set EMAILID = ? where PRN = ? ";
			PreparedStatement ps = cn.prepareStatement(q);
			ps.setString(1, valueOf(value1));
			ps.setInt(2, Integer.parseInt(valueOf(prn1)));

			ps.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	if(value.equals("ATTENDENCE")) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mentee1", "root", "ravi97292");
			String q = "update users set ATTENDENCE = ? where PRN = ? ";
			PreparedStatement ps = cn.prepareStatement(q);
			ps.setInt(1, Integer.parseInt(valueOf(value1)));
			ps.setInt(2, Integer.parseInt(valueOf(prn1)));

			ps.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	if(value.equals("IDMENTOR")) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mentee1", "root", "ravi97292");

			String q = "update users set IDMENTOR = ? where PRN = ? ";
			PreparedStatement ps = cn.prepareStatement(q);
			ps.setInt(1, Integer.parseInt(valueOf(value1)));
			ps.setInt(2, Integer.parseInt(valueOf(prn1)));

			ps.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


});

	}

}
