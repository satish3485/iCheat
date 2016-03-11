package gui;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import serverrmi.SMS;
import clientrmi.ClientController;
import commonrmi.LoginResponse;
import commonrmi.SignupResponse;
import gui.User;

public class Main extends Application {

	private Stage stage;
	private User loggedUser;
	private final double MINIMUM_WINDOW_WIDTH = 300.0;
	private final double MINIMUM_WINDOW_HEIGHT = 300.0;

	static String registryIP = "localhost";
	static int registryPort = 3000;
	static String remoteObjectName = SMS.class.getSimpleName();

	ClientController controller = new ClientController(registryIP, registryPort, remoteObjectName);

	public static void main(String[] args) {
		Main.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			stage.setTitle("FXML Login Sample");
			stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
			stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
			gotoLogin();
			primaryStage.show();
		} catch (Exception ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	public boolean userLogging(String username, String password) {
		LoginResponse response = controller.verifyUserLogin(username, password);

		if (response != null && response.getStatusCode() == 200) {
			gotoHome();
			return true;
		} else {
			return false;
		}
	}

	boolean userRegistering(String username, String password, String confirmPassword, String email) {
		SignupResponse response = controller.verifySignupInfo(username, password, confirmPassword, email);

		if (response != null && response.getStatusCode() == 200) {
			gotoLogin();
			return true;
		} else {
			return false;
		}
	}

	void gotoRegister() {
		gotoRegister2();
	}

	void userLogout() {
		loggedUser = null;
		gotoLogin();
	}

	void editProfile() {
		gotoProfile();
	}

	void backHome() {
		gotoHome();
	}

	private void gotoRegister2() {
		try {
			RegisterController register = (RegisterController) replaceSceneContent("Register.fxml");
			register.setApp(this);
		} catch (Exception ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void gotoProfile() {
		try {
			ProfileController profile = (ProfileController) replaceSceneContent("Profile.fxml");
			profile.setApp(this);
		} catch (Exception ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void gotoLogin() {
		try {
			LoginController login = (LoginController) replaceSceneContent("Login.fxml");
			login.setApp(this);
		} catch (Exception ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void gotoHome() {
		try {
			HomeController home = (HomeController) replaceSceneContent("Home.fxml");
			home.setApp(this);
		} catch (Exception ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private Initializable replaceSceneContent(String fxml) throws Exception {

		FXMLLoader loader = new FXMLLoader();
		InputStream in = Main.class.getResourceAsStream(fxml);
		loader.setBuilderFactory(new JavaFXBuilderFactory());
		loader.setLocation(Main.class.getResource(fxml));
		AnchorPane page;

		try {
			page = (AnchorPane) loader.load(in);
		} finally {
			in.close();
		}

		Scene scene = new Scene(page, 800, 600);

		stage.setScene(scene);
		stage.sizeToScene();

		return (Initializable) loader.getController();
	}

}
