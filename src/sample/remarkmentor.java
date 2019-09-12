package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;

public class remarkmentor implements Initializable {
	public Button attendence;
	public TextField textField;
	public ComboBox cbox;
	public AnchorPane apane;
	private ObservableList<ObservableList> data;
	FileInputStream input = new FileInputStream("E:\\bw.jpg");
	Image image = new Image(input);

	public remarkmentor() throws FileNotFoundException {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		BackgroundSize backgroundSize= new BackgroundSize(1.0,1.0,false,false,false,true);
		BackgroundImage myBI= new BackgroundImage(image,
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				backgroundSize);

		apane.setBackground(new Background(myBI));


		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mentee1", "root", "ravi97292");
			Statement smt = cn.createStatement();
			String q = "Select * from users where idmentor= '1'";


			ResultSet rs = smt.executeQuery(q);
			data = FXCollections.observableArrayList();
			while (rs.next()) {
				cbox.getItems().addAll(rs.getString("PRN"));

			}


		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		attendence.setOnMouseClicked(event -> {
			Object value =  cbox.getValue();
			String attendence = textField.getText();
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mentee1", "root", "ravi97292");

					String q = "update users set ATTENDENCE = ? where PRN = ? ";
					PreparedStatement ps = cn.prepareStatement(q);
					ps.setString(1, valueOf(attendence));
					ps.setInt(2, Integer.parseInt(valueOf(value)));

					ps.executeUpdate();

				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}

		});
	}
}
