package serverrmi.handlers;

import java.util.HashMap;
import java.util.Map;
import requests.LoginRequest;
import requests.Request;
import responses.LoginResponse;
import responses.Response;

public class LoginRequestHandler implements RequestHandler {

	@Override
	public Response handle(Request r) {
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
	}

}
