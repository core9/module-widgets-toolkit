package io.core9.plugin.session.login;

import static org.rendersnake.HtmlAttributesFactory.*;

import java.io.IOException;
import java.util.Map;

import org.rendersnake.HtmlCanvas;


public class HtmlRenderer implements LoginRenderer {

	private HtmlCanvas html = new HtmlCanvas();

	@SuppressWarnings("unused")
    private String url;

    private Map<String, Object> request;


	@Override
	public String render() {
		String result = "rendered page..";
		try {
			result = renderOrder();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	private String renderOrder() throws IOException {

		html.div(id("checkoutdiv")).h3().content("Login :");
		html.form(action("update").method("post"));
		html.table(id("checkout"));
		html.tr()
			.td().content("<div style=\"position:relative;height:40px;\"" + request.toString() + "</div>", NO_ESCAPE)
			.td()._td()
			.td().content("")
			.td()._td()
			.td()._td()
			._tr();
		html.tr()
			.td().content("<label for=\"email\">Email</label>", NO_ESCAPE)
			.td().content("<input type=\"text\" name=\"email\" value=\" " + getParam("email") + " \">", NO_ESCAPE)
			.td()._td()
			.td()._td()
			.td()._td()
			._tr();
		html.tr()
			.td().content("<label for=\"pass\">Password</label>", NO_ESCAPE)
			.td().content("<input type=\"password\" name=\"pass\" value=\"" + getParam("pass") + "\">", NO_ESCAPE)
			.td()._td()
			.td()._td()
			.td()._td()
			._tr();
		html.tr()
			.td().content("<label for=\"key\">Key</label>", NO_ESCAPE)
			.td().content("<input type=\"password\" name=\"key\" value=\"" + getParam("key") + "\">", NO_ESCAPE)
			.td()._td()
			.td().content("Optional")
			.td()._td()
			._tr();
		html._table();
		html.input(type("submit").value("submit"));
		html._form();
		html._div();
		
		return html.toHtml();
	}

	private String getParam(String key) {
		String result = (String) request.get(key);
		if(result == null){
			result = "";
		}
	    return result.trim();
    }

	@Override
    public LoginRenderer setUrl(String url) {
	    this.url = url;
		return this;
    }

	@Override
    public LoginRenderer setRequest(Map<String, Object> req) {
	    this.request = req;
		return this;
    }

}
