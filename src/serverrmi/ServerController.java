package serverrmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import serverrmi.services.RemoteService;
import serverrmi.services.Service;

public class ServerController {

	private Service remoteService, stub;
	private Registry registry;
	private int registryPort;

	public ServerController(int registryPort) {

		this.registryPort = registryPort;

		System.setProperty("java.security.policy", "file:./server.policy");

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		remoteService = new RemoteService();

		try {
			stub = (Service) UnicastRemoteObject.exportObject(remoteService, registryPort);
			registry = LocateRegistry.createRegistry(registryPort);
			registry.rebind(RemoteService.class.getSimpleName(), stub);
			System.out.println("Server and registry running. Registry at port " + registryPort + ".");

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/* GETTERS */

	public int getRegistryPort() {
		return registryPort;
	}

	public Service getRemoteService() {
		return remoteService;
	}

	public Service getStub() {
		return stub;
	}

	public Registry getRegistry() {
		return registry;
	}

}
