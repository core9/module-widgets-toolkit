package io.core9.plugin.session.login;

import java.util.Map;


public interface LoginRenderer {


	String render();



	LoginRenderer setUrl(String url);

	LoginRenderer setRequest(Map<String, Object> req);

}
