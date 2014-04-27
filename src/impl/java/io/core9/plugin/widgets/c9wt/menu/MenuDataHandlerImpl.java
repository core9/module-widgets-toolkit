package io.core9.plugin.widgets.c9wt.menu;

import io.core9.plugin.admin.plugins.AdminConfigRepository;
import io.core9.plugin.server.request.Request;
import io.core9.plugin.widgets.datahandler.DataHandler;
import io.core9.plugin.widgets.datahandler.DataHandlerFactoryConfig;

import java.util.HashMap;
import java.util.Map;

import net.xeoh.plugins.base.annotations.PluginImplementation;
import net.xeoh.plugins.base.annotations.injections.InjectPlugin;

@PluginImplementation
public class MenuDataHandlerImpl implements MenuDataHandler<MenuDataHandlerConfig> {
	
	@InjectPlugin
	private AdminConfigRepository configRepository;

	@Override
	public String getName() {
		return "Menu";
	}
	
	@Override
	public Class<? extends DataHandlerFactoryConfig> getConfigClass() {
		return MenuDataHandlerConfig.class;
	}

	@Override
	public DataHandler<MenuDataHandlerConfig> createDataHandler(final DataHandlerFactoryConfig options) {
		return new DataHandler<MenuDataHandlerConfig>() {

			@Override
			public Map<String, Object> handle(Request req) {
				

				
				Map<String,Object> result = new HashMap<String,Object>();
				Map<String, Object> menu = configRepository.readConfig(req.getVirtualHost(), ((MenuDataHandlerConfig) options).getMenuID(req));
				if(menu == null) {
					menu = new HashMap<String,Object>();
				}
				result.put("menu", menu);
				return result;
			}

			@Override
			public MenuDataHandlerConfig getOptions() {
				return (MenuDataHandlerConfig) options;
			}
		};
	}
}
