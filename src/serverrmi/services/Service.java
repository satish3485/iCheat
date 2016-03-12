package serverrmi.services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import requests.Request;
import responses.Response;

public interface Service extends Remote {

	// Send a request and receive a response
	public Response send(Request r) throws RemoteException;

}
