package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * Register Controller.
 */
public class RegisterController extends AnchorPane implements Initializable {

	@FXML
	private TextField user;
	@FXML
	private TextField email;
	@FXML
	private PasswordField password;
	@FXML
	private PasswordField confirmPassword;
	@FXML
	private CheckBox subscribed;
	@FXML
	private Hyperlink logout;
	@FXML
	private Button registerBtn;

	@FXML
	private Label success;

	private Main application;

	public void setApp(Main application) {
		this.application = application;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void registerFormExit(ActionEvent event) {
		if (application == null) {
			// We are running in isolated FXML, possibly in Scene Builder.
			// NO-OP.
			return;
		}

		application.userLogout();
	}

	public void registerProfile(ActionEvent event) {
		if (application == null) {
			// We are running in isolated FXML, possibly in Scene Builder.
			// NO-OP.
			animateMessage("Hello");
			return;
		} else {
			if (!application.userRegistering(user.getText(), password.getText(), confirmPassword.getText(),
					email.getText())) {
				animateMessage("Failed!");
			}
		}
	}

	public void resetRegisterForm(ActionEvent event) {
		if (application == null) {
			return;
		}
		System.out.println("ResetProfile");
		user.setText("");
		email.setText("");
		password.setText("");
		confirmPassword.setText("");
		subscribed.setSelected(false);
		success.setOpacity(0.0);

	}

	private void animateMessage(String txt) {
		FadeTransition ft = new FadeTransition(Duration.millis(1000), success);
		success.setText(txt);
		ft.setFromValue(0.0);
		ft.setToValue(1);
		ft.play();
	}
}
