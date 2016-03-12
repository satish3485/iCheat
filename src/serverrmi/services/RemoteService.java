package serverrmi.services;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import requests.CreateCourseRequest;
import requests.EditProfileRequest;
import requests.LoginRequest;
import requests.Request;
import requests.ShowProfileRequest;
import requests.SignupRequest;
import responses.Response;
import serverrmi.handlers.CreateCourseRequestHandler;
import serverrmi.handlers.EditProfileRequestHandler;
import serverrmi.handlers.LoginRequestHandler;
import serverrmi.handlers.RequestHandler;
import serverrmi.handlers.ShowProfileRequestHandler;
import serverrmi.handlers.SignupRequestHandler;

/**
 * Server's main service remote class.
 */
public class RemoteService implements Service {

	// Table to store call-backs to call depending on the request
	// String is the name of the request is can be obtained using reflection
	Map<String, RequestHandler> table;

	public RemoteService() {

		table = new HashMap<>();

		// Register your "call-backs" here!
		table.put(LoginRequest.class.getSimpleName(), new LoginRequestHandler());
		table.put(SignupRequest.class.getSimpleName(), new SignupRequestHandler());
		table.put(CreateCourseRequest.class.getSimpleName(), new CreateCourseRequestHandler());
		table.put(EditProfileRequest.class.getSimpleName(), new EditProfileRequestHandler());		
		table.put(ShowProfileRequest.class.getSimpleName(), new ShowProfileRequestHandler());
		
		
	}

	/**
	 * Remote method provided by this remote class.
	 * 
	 * Depending on the dynamic type of request r, a different request's handler will be executed.
	 */
	@Override
	public Response send(Request r) throws RemoteException {
		return table.get(r.getClass().getSimpleName()).handle(r);
	}
}
