package serverrmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

	private Service controller;

	public Server(int registryPort) {

		System.setProperty("java.security.policy", "file:./server.policy");

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		controller = new SMS();

		try {
			Service stub = (Service) UnicastRemoteObject.exportObject(controller, registryPort);

			Registry registry = LocateRegistry.createRegistry(registryPort);

			registry.rebind(SMS.class.getSimpleName(), stub);

			System.out.println("Server and registry running. Registry at port " + registryPort + ".");

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		switch (args.length) {
		case 1:
			new Server(Integer.parseInt(args[0]));
			break;
		default:
			new Server(Registry.REGISTRY_PORT);
		}
	}

}
