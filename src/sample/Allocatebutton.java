package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Callback;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.String.valueOf;

public class Allocatebutton<Person> implements Initializable {
	public ComboBox cbox;
	public AnchorPane apane;
	public TableView<Person> tv;
	public TableView tableview;
	public Button bt;
	private ObservableList<ObservableList> data;
	private ObservableList<ObservableList> data1;
	FileInputStream input = new FileInputStream("E:\\bw.jpg");
	Image image = new Image(input);


	public Allocatebutton() throws FileNotFoundException {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bt.setStyle(
				"-fx-background-radius: 60em; " +
						"-fx-min-width: 60px; " +
						"-fx-min-height: 60px; " +
						"-fx-max-width: 60px; " +
						"-fx-max-height: 60px;"+
						"-fx-background-color: white, darkseagreen;" +
						"-fx-background-insets: 0, 10;" +
						"-fx-background-radius: 60, 45;"
		);
		cbox.getItems().add("Meeta Kumar");
		cbox.getItems().add("Rupali Gangarde");
		cbox.getItems().add("Shruti Patil");


		BackgroundSize backgroundSize= new BackgroundSize(1.0,1.0,false,false,false,true);
		BackgroundImage myBI= new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
		apane.setBackground(new Background(myBI));

		data = FXCollections.observableArrayList();
		data1 = FXCollections.observableArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mentee1", "root", "ravi97292");
			Statement smt = cn.createStatement();
			String q = "Select * from mentors ";
			ResultSet rs = smt.executeQuery(q);

			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {

				final int j = i;
				TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
				col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

				tv.getColumns().addAll(col);
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
			tv.setItems((ObservableList<Person>) data);


			cn.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mentee1", "root", "ravi97292");
			Statement smt = cn.createStatement();

			String q = "Select * from users ";
			ResultSet rs = smt.executeQuery(q);


			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {

				final int j = i;
				TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
				col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
					public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
						return new SimpleStringProperty(param.getValue().get(j).toString());
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
				data1.add(row);
			}
			tableview.setItems(data1);

			cn.close();
		} catch (Exception e) {
			System.out.println(e);
		}



		tableview.getSelectionModel().selectedItemProperty().addListener((ChangeListener) (observableValue, oldValue, newValue) -> {



			if (tableview.getSelectionModel().getSelectedItem() != null) {
				System.out.println("a");
				Object PRN= null;
				TableView.TableViewSelectionModel selectionModel = tableview.getSelectionModel();
				ObservableList selectedCells = selectionModel.getSelectedCells();
				TablePosition tablePosition = (TablePosition) selectedCells.get(0);
				PRN= tablePosition.getTableColumn().getCellData(newValue);
				System.out.println("Selected Value" + PRN);

				Object finalPRN = PRN;
				bt.setOnMouseClicked(event -> {
					String mentorname= (String) cbox.getValue();
					if(mentorname.equals("Meeta Kumar")){
						String f= (String) finalPRN;
						System.out.println("a");
						try {
							Class.forName("com.mysql.jdbc.Driver");
							Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mentee1", "root", "ravi97292");

							String q = "update users set IDMENTOR = 1 where PRN = ?";
							PreparedStatement preparedStmt = cn.prepareStatement(q);
							preparedStmt.setInt(1, Integer.parseInt(valueOf(f)));


							preparedStmt.executeUpdate();
							cn.close();

						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						try {
							tableview.getItems().clear();
							tableview.getColumns().clear();
							Class.forName("com.mysql.jdbc.Driver");
							Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mentee1", "root", "ravi97292");
							Statement smt = cn.createStatement();

							String q = "Select * from users ";
							ResultSet rs = smt.executeQuery(q);


							for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {

								final int j = i;
								TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
								col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
									public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
										return new SimpleStringProperty(param.getValue().get(j).toString());
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
								data1.add(row);
							}
							tableview.setItems(data1);

							cn.close();
						} catch (Exception e) {
							System.out.println(e);
						}


					}
					if(mentorname.equals("Rupali Gangarde")){
						String f= (String) finalPRN;
						System.out.println("a");
						try {
							Class.forName("com.mysql.jdbc.Driver");
							Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mentee1", "root", "ravi97292");
							String q = "update users set IDMENTOR = 2 where PRN = ?";
							PreparedStatement preparedStmt = cn.prepareStatement(q);
							preparedStmt.setInt(1, Integer.parseInt(valueOf(f)));


							preparedStmt.executeUpdate();
							cn.close();

						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						try {
							tableview.getItems().clear();
							tableview.getColumns().clear();
							Class.forName("com.mysql.jdbc.Driver");
							Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mentee1", "root", "ravi97292");
							Statement smt = cn.createStatement();

							String q = "Select * from users ";
							ResultSet rs = smt.executeQuery(q);


							for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {

								final int j = i;
								TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
								col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
									public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
										return new SimpleStringProperty(param.getValue().get(j).toString());
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
								data1.add(row);
							}
							tableview.setItems(data1);

							cn.close();
						} catch (Exception e) {
							System.out.println(e);
						}


					}
					if(mentorname.equals("Shruti Patil")){
						String f= (String) finalPRN;
						System.out.println("a");
						try {
							Class.forName("com.mysql.jdbc.Driver");
							Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mentee1", "root", "ravi97292");

							String q = "update users set IDMENTOR = 3 where PRN = ?";
							PreparedStatement preparedStmt = cn.prepareStatement(q);
							preparedStmt.setInt(1, Integer.parseInt(valueOf(f)));


							preparedStmt.executeUpdate();
							cn.close();

						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						try {
							tableview.getItems().clear();
							tableview.getColumns().clear();
							Class.forName("com.mysql.jdbc.Driver");
							Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mentee1", "root", "ravi97292");
							Statement smt = cn.createStatement();

							String q = "Select * from users ";
							ResultSet rs = smt.executeQuery(q);


							for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {

								final int j = i;
								TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
								col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
									public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
										return new SimpleStringProperty(param.getValue().get(j).toString());
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
								data1.add(row);
							}
							tableview.setItems(data1);

							cn.close();
						} catch (Exception e) {
							System.out.println(e);
						}


					}
				});
			}


		});
	}


	public void mouseclick(MouseEvent event) {
		System.out.println("check");
	}
}
