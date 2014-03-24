package io.core9.plugin.widgets.c9wt.menu;

import io.core9.plugin.server.request.Request;
import io.core9.plugin.widgets.Core9GlobalConfiguration;
import io.core9.plugin.widgets.datahandler.DataHandlerDefaultConfig;
import io.core9.plugin.widgets.datahandler.DataHandlerGlobalString;

public class MenuDataHandlerConfig extends DataHandlerDefaultConfig {
	
	@Core9GlobalConfiguration(type = "menu")
	private DataHandlerGlobalString menuID;

	/**
	 * @return the menuName
	 */
	public DataHandlerGlobalString getMenuID() {
		return menuID;
	}
	
	/**
	 * Return the menuID from a global
	 * @param request
	 * @return
	 */
	public String getMenuID(Request request) {
		if(menuID.isGlobal()) {
			return request.getContext(this.getComponentId() + ".menuID", menuID.getValue());
		}
		return menuID.getValue();
	}

	/**
	 * @param menuName the menuName to set
	 */
	public void setMenuID(DataHandlerGlobalString menuID) {
		this.menuID = menuID;
	}

}
