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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;

public class mentee implements Initializable {
	public Button loginbutton;
	public TextField textField;
	public TableView tableview;
	public TableView tableview1;
	public AnchorPane apane;
	private ObservableList<ObservableList> data;
	private ObservableList<ObservableList> data1;
	FileInputStream input = new FileInputStream("E:\\bw.jpg");
	Image image = new Image(input);

	public mentee() throws FileNotFoundException {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		BackgroundSize backgroundSize= new BackgroundSize(1.0,1.0,false,false,false,true);
		BackgroundImage myBI= new BackgroundImage(image,
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				backgroundSize);
		apane.setBackground(new Background(myBI));

		data1 = FXCollections.observableArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mentee1", "root", "ravi97292");

			String q = "Select * from mentors ";
			Statement ps = cn.createStatement();


			ResultSet rs = ps.executeQuery(q);
			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {


				final int j = i;
				TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
				col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
					public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> kush) {
						return new SimpleStringProperty(kush.getValue().get(j).toString());
					}
				});

				tableview1.getColumns().addAll(col);
				System.out.println("Column [" + i + "] ");
			}


			while (rs.next()) {
				ObservableList<String> row = FXCollections.observableArrayList();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					row.add(rs.getString(i));
				}
				System.out.println("Row [1] added " + row);
				data1.add(row);
			}
			tableview1.setItems(data1);

			cn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		loginbutton.setOnMouseClicked(event -> {
			tableview.getItems().clear();
			tableview.getColumns().clear();
			String prn1 = textField.getText();
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
	}
}
