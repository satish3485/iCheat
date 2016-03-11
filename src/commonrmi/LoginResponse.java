package commonrmi;

import java.util.Map;

public class LoginResponse extends Response {

	private static final long serialVersionUID = 2830077656866422610L;

	public LoginResponse() {
		super();
	}

	public LoginResponse(int status, Map<String, Object> data) {
		super(status, data);
	}
}
