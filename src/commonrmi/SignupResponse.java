package commonrmi;

import java.util.Map;

// Objects passed over the network with RMI must be serializable or implement Remote.
public class SignupResponse extends Response {

	private static final long serialVersionUID = 9104119452090317289L;

	public SignupResponse() {
		super();
	}

	public SignupResponse(int status, Map<String, Object> data) {
		super(status, data);
	}
}
