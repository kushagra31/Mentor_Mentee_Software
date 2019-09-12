package sample;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;

public class newwindow1controller implements Initializable {
	public TextField Name;
	public TextField PRN;
	public TextField PRN1;
	public TextField Marks;
	public Button subbutton;
	public AnchorPane apane;
	FileInputStream input = new FileInputStream("E:\\bw.jpg");
	Image image = new Image(input);

	public newwindow1controller() throws FileNotFoundException {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		BackgroundSize backgroundSize=new BackgroundSize(1.0,1.0,false,false,false,true);
		BackgroundImage myBI= new BackgroundImage(image,
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				backgroundSize);
		apane.setBackground(new Background(myBI));
		subbutton.setStyle(
				"-fx-background-radius: 60em; " +
						"-fx-min-width: 60px; " +
						"-fx-min-height: 60px; " +
						"-fx-max-width: 60px; " +
						"-fx-max-height: 60px;"+
						"-fx-background-color: white, darkseagreen;" +
						"-fx-background-insets: 0, 10;" +
						"-fx-background-radius: 60, 45;"
		);

		subbutton.setOnMouseClicked(event ->
				{
					String nam= Name.getText();
					String prn=PRN.getText();
					String marks=Marks.getText();
					String email=PRN1.getText();

					try
					{

						String myDriver = "com.mysql.jdbc.Driver";
						String myUrl = "jdbc:mysql://localhost/mentee1";
						Class.forName(myDriver);
						Connection conn = DriverManager.getConnection(myUrl, "root", "ravi97292");



						String sql="Insert into users (PRN,NAME,ATTENDENCE,IDMENTOR,EMAILID) VALUES (?,?,?,?,?)";
						PreparedStatement ps=conn.prepareStatement(sql);
						ps.setString(1, valueOf(prn));
						ps.setString(2,nam);
						ps.setString(3, valueOf(marks));
						ps.setString(5, email);
						if(Integer.valueOf(prn)<=10) {
							ps.setString(4, String.valueOf(1));
						}
						if(Integer.valueOf(prn) >10 && Integer.valueOf(prn) <=20) {
							ps.setString(4, String.valueOf(2));
						}
						if(Integer.valueOf(prn) >20 && Integer.valueOf(prn) <=30) {
							ps.setString(4, String.valueOf(3));
						}
						if(Integer.valueOf(prn) >30) {
							ps.setString(4, String.valueOf(-1));
						}



						ps.executeUpdate();

						conn.close();

					}
					catch (Exception e)
					{
						System.err.println("Got an exception!");
						System.err.println(e.getMessage());
					}
				}
		);

	}
}
