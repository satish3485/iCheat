package commonrmi;

public class SignupRequest extends Request {

	private static final long serialVersionUID = 427231682853519898L;

	final String username, password, confirmPassword, email;

	public SignupRequest(String username, String password, String confirmPassword, String email) {
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public String getEmail() {
		return email;
	}
}
