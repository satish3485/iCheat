package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * Login Controller.
 */
public class LoginController extends AnchorPane implements Initializable {

	@FXML
	TextField userId;

	@FXML
	PasswordField password;

	@FXML
	Button loginBtn;

	@FXML
	Button registerBtn;

	@FXML
	Label errorMessage;

	private Main application;

	public void setApp(Main application) {
		this.application = application;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		errorMessage.setText("");
		userId.setPromptText("username");
		password.setPromptText("password");
	}

	public void processRegister(ActionEvent event) {
		if (application == null) {
			errorMessage.setText("Error");
			System.out.println("Error");
		} else {
			application.gotoRegister();
		}
	}

	public void processLogin(ActionEvent event) {
		if (application == null) {
			// We are running in isolated FXML, possibly in Scene Builder.
			// NO-OP.
			errorMessage.setText("Hello " + userId.getText());
		} else {
			if (!application.userLogging(userId.getText(), password.getText())) {
				errorMessage.setText("Username/Password is incorrect");
			}
		}
	}

}
