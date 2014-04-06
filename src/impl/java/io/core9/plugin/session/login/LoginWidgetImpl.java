package io.core9.plugin.session.login;

import io.core9.plugin.server.vertx.VertxServer;
import io.core9.plugin.session.SessionManager;
import io.core9.plugin.widgets.Component;
import io.core9.plugin.widgets.pagemodel.PageModel;
import io.core9.plugin.widgets.pagemodel.PageModelImpl;
import io.core9.plugin.widgets.widget.Widget;
import io.core9.plugin.widgets.widget.WidgetImpl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import net.xeoh.plugins.base.annotations.PluginImplementation;
import net.xeoh.plugins.base.annotations.injections.InjectPlugin;

import com.google.common.io.CharStreams;

@PluginImplementation
public class LoginWidgetImpl implements LoginWidget {
	
	@InjectPlugin
	private VertxServer server;

	@InjectPlugin
	private SessionManager sessionManager;

	private List<Widget> widgets = new ArrayList<Widget>();
	
	@Override
    public List<Widget> getWidgets() {
		WidgetImpl widget = new WidgetImpl();
		widget.setName("login_form");
		widget.setTemplateName("form.login.header");
		widget.setDataHandler(DataHandlerImpl.getInstance(server, sessionManager));
		try {
			widget.setTemplate(CharStreams.toString(new InputStreamReader(this.getClass().getResourceAsStream("/form/loginwidget.soy"))));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		widgets.add(widget);
		return widgets;
    }

	@Override
    public List<PageModel> getPageModels() {
		List<PageModel> models = new ArrayList<PageModel>();
/*		PageModel product = new PageModelImpl();
		product.setName("login");
		product.setPath("/login/:action");
		product.setTemplateName("noflow");
		product.addComponent(new Component("login_form"));
		models.add(product);*/

		return models;
    }






}
