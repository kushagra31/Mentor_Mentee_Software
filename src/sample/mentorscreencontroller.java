package sample;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mentorscreencontroller implements Initializable {

	public Button displaybutton;
	public Button addremarkbutton;
	public AnchorPane apane;
	FileInputStream input = new FileInputStream("E:\\bw.jpg");
	Image image = new Image(input);

	public mentorscreencontroller() throws FileNotFoundException {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		BackgroundSize backgroundSize= new BackgroundSize(1.0,1.0,false,false,false,true);
		BackgroundImage myBI= new BackgroundImage(image,
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				backgroundSize);
		apane.setBackground(new Background(myBI));
    displaybutton.setOnMouseClicked(event -> {
	    try{
		    Parent rootw = FXMLLoader.load(getClass().getResource("displaybuttonmentor.fxml"));
		    Stage stage=new Stage();
		    stage.setScene(new Scene(rootw,450,450));
		    stage.setTitle("Mentor Mentee Software");
		    stage.setMaximized(true);
		    stage.show();


	    } catch (IOException e) {
		    e.printStackTrace();
	    }

     });
		addremarkbutton.setOnMouseClicked(event -> {
			try{
				Parent rootw = FXMLLoader.load(getClass().getResource("remarkmentor.fxml"));
				Stage stage=new Stage();
				stage.setScene(new Scene(rootw,450,450));
				stage.setTitle("Mentor Mentee Software");
				stage.setMaximized(true);
				stage.show();


			} catch (IOException e) {
				e.printStackTrace();
			}

		});
	}
}
