package io.core9.plugin.widgets.c9wt.menu;

import io.core9.core.plugin.Core9Plugin;
import io.core9.plugin.widgets.datahandler.DataHandlerFactory;
import io.core9.plugin.widgets.datahandler.DataHandlerFactoryConfig;

public interface MenuDataHandler<T extends DataHandlerFactoryConfig> extends DataHandlerFactory<T>, Core9Plugin {

}
