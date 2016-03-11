package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * Home Controller.
 */
public class HomeController extends AnchorPane implements Initializable {

	@FXML
	private Button createCourseBtn;

	@FXML
	private TextField mainMsg;

	@FXML
	private Hyperlink logout;

	private Main application;

	public void setApp(Main application) {
		this.application = application;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		mainMsg.setText("Welcome!");

	}

	public void createCourse() {

	}

	public void editProfile(ActionEvent event) {
		if (application == null) {
			// We are running in isolated FXML, possibly in Scene Builder.
			// NO-OP.
			return;
		}

		application.editProfile();
	}

	public void homeExit(ActionEvent event) {
		if (application == null) {
			// We are running in isolated FXML, possibly in Scene Builder.
			// NO-OP.
			return;
		}

		application.userLogout();
	}

}
