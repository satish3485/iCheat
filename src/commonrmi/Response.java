package commonrmi;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Extend this class to implement a specific response.
 * 
 * All objects passed around using RMI need to be serializable or implement Remote.
 */
public abstract class Response implements Serializable {

	private static final long serialVersionUID = -1141059146727857444L;

	Map<String, Object> data;
	int statusCode;

	public Response() {
		this(200, new HashMap<>());
	}

	public Response(int status, Map<String, Object> data) {
		statusCode = status;
		this.data = data;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public int getStatusCode() {
		return statusCode;
	}
}
