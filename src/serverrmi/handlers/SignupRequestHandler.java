package serverrmi.handlers;

import requests.Request;
import requests.SignupRequest;
import responses.Response;
import responses.SignupResponse;

public class SignupRequestHandler implements RequestHandler {

	@Override
	public Response handle(Request r) {
		SignupRequest sr = (SignupRequest) r;
		if (sr.getUsername().equals("root") && sr.getPassword().equals(sr.getConfirmPassword())
				&& sr.getPassword().equals("root") && sr.getEmail().equals("root@root.com")) {
			return new SignupResponse();
		} else {
			return new SignupResponse(400, null);
		}
	}

}
