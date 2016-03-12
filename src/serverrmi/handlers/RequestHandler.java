package serverrmi.handlers;

import requests.Request;
import responses.Response;

public interface RequestHandler {
	Response handle(Request r);
}
