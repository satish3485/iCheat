package serverrmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import commonrmi.Request;
import commonrmi.Response;

public interface Service extends Remote {
	// Send a request and receive a response
	public Response send(Request r) throws RemoteException;
}
