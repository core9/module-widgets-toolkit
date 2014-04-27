package io.core9.plugin.widgets.c9wt.menu;

import io.core9.plugin.widgets.datahandler.DataHandler;
import io.core9.plugin.widgets.datahandler.DataHandlerGlobalString;

import java.io.IOException;
import java.io.InputStreamReader;

import net.xeoh.plugins.base.annotations.PluginImplementation;
import net.xeoh.plugins.base.annotations.injections.InjectPlugin;

import com.google.common.io.CharStreams;

@PluginImplementation
public class MenuWidgetImpl implements MenuWidget {
	
	@InjectPlugin
	private MenuDataHandler<MenuDataHandlerConfig> menuDataHandler;
	
	private DataHandler<MenuDataHandlerConfig> handler;
	
	@Override
	public DataHandler<?> getDataHandler() {
		return handler;
	}

	@Override
	public String getName() {
		return "c9wt_menu";
	}

	@Override
	public String getTemplate() {
		try {
			return CharStreams.toString(new InputStreamReader(this.getClass().getResourceAsStream("/menu/menu.soy")));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getTemplateName() {
		return "io.core9.c9wt.menu";
	}

	@Override
	public void execute() {
		MenuDataHandlerConfig options = new MenuDataHandlerConfig();
		DataHandlerGlobalString MenuID = new DataHandlerGlobalString();
		MenuID.setGlobal(true);
		options.setMenuID(MenuID);
		handler = menuDataHandler.createDataHandler(options);
	}

	@Override
	public String getId() {
		return null;
	}



}
