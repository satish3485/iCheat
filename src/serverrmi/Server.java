package serverrmi;

import java.rmi.registry.Registry;

public class Server {
	public static void main(String[] args) {
		switch (args.length) {
		case 1:
			new ServerController(Integer.parseInt(args[0]));
			break;
		default:
			new ServerController(Registry.REGISTRY_PORT);
		}
	}
}
