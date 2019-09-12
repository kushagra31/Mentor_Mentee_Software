package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class Controller implements Initializable {

	public Button submitbutton;
	public TextField namefield;
	public PasswordField passfield;
	public GridPane mycontainer;

	FileInputStream input = new FileInputStream("E:\\sit.jpg");
	Image image = new Image(input);

	public Controller() throws FileNotFoundException {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		BackgroundSize backgroundSize= new BackgroundSize(1.0,1.0,false,false,false,true);
		BackgroundImage myBI= new BackgroundImage(image,
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				backgroundSize);

		mycontainer.setBackground(new Background(myBI));

		submitbutton.setOnMouseClicked((event) -> {
			if(namefield.getText().equals("admin") && passfield.getText().equals("admin")) {
				try {
					Parent rootw = FXMLLoader.load(getClass().getResource("newwindow.fxml"));
					Stage stage = new Stage();
					stage.setTitle("Mentor Mentee Software");
					stage.setScene(new Scene(rootw, 450, 450));
					stage.setMaximized(true);

					stage.show();


				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			else if(namefield.getText().equals("m") && passfield.getText().equals("m")){
				try{
					Parent rootw =FXMLLoader.load(getClass().getResource("mentorscreen.fxml"));
				    Stage stage=new Stage();
				    stage.setScene(new Scene(rootw,450,450));
				    stage.setMaximized(true);
				    stage.show();


				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			else if(namefield.getText().equals("men") && passfield.getText().equals("men")){
				try{
					Parent rootw =FXMLLoader.load(getClass().getResource("mentee.fxml"));
					Stage stage=new Stage();
					stage.setScene(new Scene(rootw,450,450));
					stage.setMaximized(true);
					stage.show();


				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			else{
				Alert a=new Alert(Alert.AlertType.NONE);
				a.setAlertType(Alert.AlertType.ERROR);
				a.setTitle("Mentor Mentee Login");
				a.setContentText("Wrong Username or Password");
				a.show();
			}
			((Node)(event.getSource())).getScene().getWindow().hide();

		});
	}


}
