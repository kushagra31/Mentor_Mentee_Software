package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

public class deletebuttoncontroller implements Initializable {
	public TextField prntextfield;
	public Button deletestudentbutton;
	public Button showdatabasebutton;
	public TableView tableview;
	public AnchorPane apane;
	public ObservableList<ObservableList> data;
	FileInputStream input = new FileInputStream("E:\\bw.jpg");
	javafx.scene.image.Image image = new Image(input);

	public deletebuttoncontroller() throws FileNotFoundException {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		BackgroundSize backgroundSize= new BackgroundSize(1.0,1.0,false,false,false,true);
		BackgroundImage myBI= new BackgroundImage(image,
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				backgroundSize);

		apane.setBackground(new Background(myBI));
		showdatabasebutton.setOnMouseClicked(event -> {
			tableview.getItems().clear();
			tableview.getColumns().clear();
			String prn1 = prntextfield.getText();
			data = FXCollections.observableArrayList();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mentee1", "root", "ravi97292");

				String q = "Select * from users where PRN = ? ";
				PreparedStatement ps=cn.prepareStatement(q);
				ps.setInt(1,Integer.parseInt(valueOf(prn1)));

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
		deletestudentbutton.setOnMouseClicked(event1 -> {
			String prn = prntextfield.getText();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mentee1", "root", "ravi97292");
				Statement smt = cn.createStatement();
				String q = "Delete from users where PRN = ? ";
				PreparedStatement ps=cn.prepareStatement(q);
				ps.setInt(1,Integer.parseInt(valueOf(prn)));
				ps.executeUpdate();
				cn.close();


		} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		});

	}
}
