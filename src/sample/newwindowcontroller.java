package sample;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class newwindowcontroller implements Initializable {
	public Button creatbutton;
	public Button mentebutton;
	public GridPane mycontainer1;
	public Button allocate;
	public Button delete;
	public Button delete1;

	FileInputStream input = new FileInputStream("E:\\bw.jpg");
	Image image = new Image(input);
	FileInputStream input1 = new FileInputStream("E:\\pizza.png");
	Image image1 = new Image(input1);
	public newwindowcontroller() throws FileNotFoundException {
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		BackgroundSize backgroundSize=new BackgroundSize(1.0,1.0,false,false,false,true);
		BackgroundImage myBI= new BackgroundImage(image,
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				backgroundSize);
		mycontainer1.setBackground(new Background(myBI));
		creatbutton.setStyle(
				"-fx-background-radius: 60em; " +
						"-fx-min-width: 60px; " +
						"-fx-min-height: 60px; " +
						"-fx-max-width: 60px; " +
						"-fx-max-height: 60px;"+
						"-fx-background-color: white, darkseagreen;" +
						"-fx-background-insets: 0, 10;" +
						"-fx-background-radius: 60, 45;"
		);
		mentebutton.setStyle(
				"-fx-background-radius: 60em; " +
						"-fx-min-width: 60px; " +
						"-fx-min-height: 60px; " +
						"-fx-max-width: 60px; " +
						"-fx-max-height: 60px;"+
						"-fx-background-color: white, darkseagreen;" +
						"-fx-background-insets: 0, 10;" +
						"-fx-background-radius: 60, 45;"
		);
		delete.setStyle(
				"-fx-background-radius: 60em; " +
						"-fx-min-width: 60px; " +
						"-fx-min-height: 60px; " +
						"-fx-max-width: 60px; " +
						"-fx-max-height: 60px;"+
						"-fx-background-color: white, darkseagreen;" +
						"-fx-background-insets: 0, 10;" +
						"-fx-background-radius: 60, 45;"
		);
		allocate.setStyle(

				"-fx-background-radius: 60em; " +
						"-fx-min-width: 60px; " +
						"-fx-min-height: 60px; " +
						"-fx-max-width: 60px; " +
						"-fx-max-height: 60px;"+
				"-fx-background-color: white, darkseagreen;" +
						"-fx-background-insets: 0, 10;" +
						"-fx-background-radius: 60, 45;"


		);
		delete1.setStyle(
				"-fx-background-radius: 60em; " +
						"-fx-min-width: 60px; " +
						"-fx-min-height: 60px; " +
						"-fx-max-width: 60px; " +
						"-fx-max-height: 60px;"+
						"-fx-background-color: white, darkseagreen;" +
						"-fx-background-insets: 0, 10;" +
						"-fx-background-radius: 60, 45;"
		);



		creatbutton.setOnMouseClicked(event -> {
			try {

				Parent rootw = FXMLLoader.load(getClass().getResource("newwindow1.fxml"));
				Stage stage = new Stage();
				stage.setTitle("Mentor Mentee Software");
				stage.setScene(new Scene(rootw, 450, 450));
				stage.setMaximized(true);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		mentebutton.setOnMouseClicked(event1 -> {
			try {

				Parent rootw = FXMLLoader.load(getClass().getResource("menteedisplay.fxml"));
				Stage stage = new Stage();
				stage.setScene(new Scene(rootw, 550, 750));
				stage.setMaximized(true);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		allocate.setOnMouseClicked(event -> {
			try {

				Parent rootw = FXMLLoader.load(getClass().getResource("allocatebutton.fxml"));
				Stage stage = new Stage();
				stage.setScene(new Scene(rootw, 550, 750));
				stage.setMaximized(true);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		delete.setOnMouseClicked(event -> {
			try {
				Parent rootw = FXMLLoader.load(getClass().getResource("deletebutton.fxml"));
				Stage stage = new Stage();
				stage.setTitle("Mentor Mentee Software");
				stage.setScene(new Scene(rootw, 450, 450));
				stage.setMaximized(true);
				stage.show();

			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		delete1.setOnMouseClicked(event -> {
			try {

				Parent rootw = FXMLLoader.load(getClass().getResource("updatebutton.fxml"));
				Stage stage = new Stage();
				stage.setTitle("Mentor Mentee Software");
				stage.setScene(new Scene(rootw, 450, 450));
				stage.setMaximized(true);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

	}
}
