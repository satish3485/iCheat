package testsrmi;

import java.rmi.registry.Registry;
import clientrmi.ClientController;
import serverrmi.ServerController;
import serverrmi.services.RemoteService;
import serverrmi.services.Service;
import java.util.Random;

public class TestRMI {

	public static final Random randomPortGenerator = new Random();

	/**
	 * Based on: http://stackoverflow.com/a/363692/3924118
	 */
	private static int randInt(int min, int max) {
		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		return randomPortGenerator.nextInt((max - min) + 1) + min;
	}

	private static void testServerStatus(ServerController serverController, int registryPort) {
		assert serverController.getRegistry() != null;
		assert serverController.getRemoteService() != null;
		assert serverController.getStub() != null;
		assert serverController.getRemoteService() instanceof RemoteService;
		assert serverController.getStub() instanceof Service;
		assert serverController.getRegistry() instanceof Registry;
		assert serverController.getRegistryPort() == registryPort;
		assert System.getSecurityManager() != null;
	}

	private static void testClientStatus(ClientController clientController, ServerController serverController,
			int registryPort) {
		assert clientController.getRegistry() != null;
		assert clientController.getRemoteController().equals(serverController.getStub());
		assert clientController.getRegistryIP().equals("localhost");
		assert clientController.getRegistryPort() == registryPort;
		assert clientController.getRemoteControllerName().equals(RemoteService.class.getSimpleName());
	}

	public static void testConnection() {
		int registryPort = randInt(1024, 65535);

		ServerController serverController = new ServerController(registryPort);

		ClientController clientController = new ClientController("localhost", registryPort,
				RemoteService.class.getSimpleName());

		// Assert stuff for server controller
		testServerStatus(serverController, registryPort);

		// Assert things for client controller
		testClientStatus(clientController, serverController, registryPort);

		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " finished.");
	}

	public static void testManyClientsConnecting(int n) {
		int registryPort = randInt(1024, 65535);

		ServerController serverController = new ServerController(registryPort);

		testServerStatus(serverController, registryPort);

		for (int i = 0; i < n; ++i) {
			ClientController c = new ClientController("localhost", registryPort, RemoteService.class.getSimpleName());
			testClientStatus(c, serverController, registryPort);
		}

		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " finished.");

	}

	public static void main(String[] args) {
		TestRMI.testConnection();
		TestRMI.testManyClientsConnecting(10);
	}
}
