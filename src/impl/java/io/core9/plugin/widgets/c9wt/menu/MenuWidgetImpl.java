package io.core9.plugin.widgets.c9wt.menu;

import io.core9.plugin.widgets.datahandler.DataHandler;
import io.core9.plugin.widgets.datahandler.DataHandlerGlobal;
import io.core9.plugin.widgets.widget.WidgetImpl;

import java.io.IOException;
import java.io.InputStreamReader;

import net.xeoh.plugins.base.annotations.PluginImplementation;
import net.xeoh.plugins.base.annotations.events.PluginLoaded;

import com.google.common.io.CharStreams;

@PluginImplementation
public class MenuWidgetImpl extends WidgetImpl implements MenuWidget {
	
	private DataHandler<MenuDataHandlerConfig> handler;
	
	@PluginLoaded
	public void onMenuDataHandlerLoaded(MenuDataHandler<MenuDataHandlerConfig> menuDataHandler) {
		MenuDataHandlerConfig options = new MenuDataHandlerConfig();
		DataHandlerGlobal<String> MenuID = new DataHandlerGlobal<String>();
		MenuID.setGlobal(true);
		options.setMenuID(MenuID);
		this.handler = menuDataHandler.createDataHandler(options);
	}
	
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
	public String getId() {
		return "C9WTMENUWIDGET";
	}

}
