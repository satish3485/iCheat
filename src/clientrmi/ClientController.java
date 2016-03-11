package clientrmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import commonrmi.LoginRequest;
import commonrmi.LoginResponse;
import commonrmi.SignupRequest;
import commonrmi.SignupResponse;
import serverrmi.Service;

public class ClientController {

	String remoteControllerName, registryIP;
	int registryPort;
	Registry registry;
	Service remoteController;

	public ClientController(String registryIP, int registryPort, String remoteControllerName) {
		this.registryIP = registryIP;
		this.registryPort = registryPort;
		this.remoteControllerName = remoteControllerName;

		try {
			// Get a reference to the server's registry
			registry = LocateRegistry.getRegistry(registryIP, registryPort);

			// Search for the reference of name remoteControllerName in the registry of the server
			remoteController = (Service) registry.lookup(remoteControllerName);

			System.out.println("ClientController connected to registry " + registryIP + " through port " + registryPort + ".");
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

	public LoginResponse verifyUserLogin(String username, String password) {
		try {
			return (LoginResponse) remoteController.send(new LoginRequest(username, password));
		} catch (RemoteException e) {
			// Something went wrong -> show an error message to user!
			return null;
		}
	}

	public SignupResponse verifySignupInfo(String username, String password, String confirmPassword, String email) {
		try {
			return (SignupResponse) remoteController.send(new SignupRequest(username, password, confirmPassword, email));
		} catch (RemoteException e) {
			// Something went wrong -> show an error message to user!
			return null;
		}
	}

	public Service getRemoteController() {
		return remoteController;
	}

}
