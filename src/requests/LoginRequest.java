package requests;

public class LoginRequest extends Request {

	private static final long serialVersionUID = 6458313435899694826L;

	final String username, password;

	public LoginRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUserName() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
