package serverrmi;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import commonrmi.*;

/**
 * Server's main service remote class.
 */
public class SMS implements Service {

	/**
	 * Clients call this method to send a request to this controller and to receive after that a
	 * response.
	 */
	@Override
	public Response send(Request r) throws RemoteException {

		if (r instanceof LoginRequest) {

			LoginRequest lr = (LoginRequest) r;
			System.out.println(lr.getUserName());
			System.out.println(lr.getPassword());

			if (lr.getUserName().equals("root") && lr.getPassword().equals("root")) {

				return new LoginResponse();
			} else {
				Map<String, Object> data = new HashMap<>();
				data.put("Description", "User " + lr.getUserName() + " not found");
				return new LoginResponse(404, data);
			}

		} else if (r instanceof SignupRequest) {
			SignupRequest sr = (SignupRequest) r;
			if (sr.getUsername().equals("root") && sr.getPassword().equals(sr.getConfirmPassword())
					&& sr.getPassword().equals("root") && sr.getEmail().equals("root@root.com")) {
				return new SignupResponse();
			} else {
				return new SignupResponse(400, null);
			}

		} else {
			return null;
		}
	}

}
