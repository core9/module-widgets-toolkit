package io.core9.plugin.session.login;

import java.util.Map;
import io.core9.plugin.server.request.Request;

public class RequestUtils {

	private RequestUtils() {
	}

	public static Map<String, Object> getParams(Request req) {
		Map<String, Object> params = req.getBodyAsMap();
		params.putAll(req.getParams());
		return params;
	}

}
