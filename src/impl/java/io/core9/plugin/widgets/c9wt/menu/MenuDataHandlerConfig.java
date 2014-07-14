package io.core9.plugin.widgets.c9wt.menu;

import io.core9.plugin.server.request.Request;
import io.core9.plugin.widgets.Core9GlobalConfiguration;
import io.core9.plugin.widgets.datahandler.DataHandlerDefaultConfig;
import io.core9.plugin.widgets.datahandler.DataHandlerGlobal;

public class MenuDataHandlerConfig extends DataHandlerDefaultConfig {
	
	@Core9GlobalConfiguration(type = "menu")
	private DataHandlerGlobal<String> menuID;

	/**
	 * @return the menuName
	 */
	public DataHandlerGlobal<String> getMenuID() {
		return menuID;
	}
	
	/**
	 * Return the menuID from a global
	 * @param request
	 * @return
	 */
	public String getMenuID(Request request) {
		if(menuID.isGlobal()) {
			return request.getContext(this.getComponentName() + ".menuID", menuID.getValue());
		}
		return menuID.getValue();
	}

	/**
	 * @param menuName the menuName to set
	 */
	public void setMenuID(DataHandlerGlobal<String> menuID) {
		this.menuID = menuID;
	}

}
