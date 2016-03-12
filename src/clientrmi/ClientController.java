package clientrmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import requests.LoginRequest;
import requests.SignupRequest;
import responses.LoginResponse;
import responses.SignupResponse;
import serverrmi.services.Service;

public class ClientController {

	private String remoteControllerName, registryIP;
	private int registryPort;
	
	private Registry registry;
	private Service remoteController;

	public ClientController(String registryIP, int registryPort, String remoteControllerName) {
		this.remoteControllerName = remoteControllerName;
		this.registryIP = registryIP;
		this.registryPort = registryPort;

		try {
			// Get a reference to the server's registry
			registry = LocateRegistry.getRegistry(registryIP, registryPort);

			// Search for the reference of name remoteControllerName in the registry of the server
			remoteController = (Service) registry.lookup(remoteControllerName);

			System.out.println(
					"ClientController connected to registry " + registryIP + " through port " + registryPort + ".");
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
			return (SignupResponse) remoteController
					.send(new SignupRequest(username, password, confirmPassword, email));
		} catch (RemoteException e) {
			// Something went wrong -> show an error message to user!
			return null;
		}
	}

	/* GETTERS */

	public String getRemoteControllerName() {
		return remoteControllerName;
	}

	public String getRegistryIP() {
		return registryIP;
	}

	public int getRegistryPort() {
		return registryPort;
	}

	public Registry getRegistry() {
		return registry;
	}

	public Service getRemoteController() {
		return remoteController;
	}

}
