package serverrmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import serverrmi.services.RemoteService;
import serverrmi.services.Service;

public class ServerController {

	private Service remoteService;

	public ServerController(int registryPort) {

		System.setProperty("java.security.policy", "file:./server.policy");

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		remoteService = new RemoteService();
		
		try {
			Service stub = (Service) UnicastRemoteObject.exportObject(remoteService, registryPort);

			Registry registry = LocateRegistry.createRegistry(registryPort);

			registry.rebind(RemoteService.class.getSimpleName(), stub);

			System.out.println("Server and registry running. Registry at port " + registryPort + ".");

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
