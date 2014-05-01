package io.core9.plugin.widgets.c9wt;

import io.core9.plugin.widgets.c9wt.pager.PagerWidget;
import io.core9.plugin.widgets.datahandler.DataHandler;
import io.core9.plugin.widgets.widget.WidgetImpl;

import java.io.IOException;
import java.io.InputStreamReader;

import net.xeoh.plugins.base.annotations.PluginImplementation;

import com.google.common.io.CharStreams;

@PluginImplementation
public class PagerWidgetImpl extends WidgetImpl implements PagerWidget {
	
	@Override
	public DataHandler<?> getDataHandler() {
		return null;
	}

	@Override
	public String getName() {
		return "c9wt_pager";
	}

	@Override
	public String getTemplate() {
		try {
			return CharStreams.toString(new InputStreamReader(this.getClass().getResourceAsStream("/pager/pager.soy")));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getTemplateName() {
		return "io.core9.c9wt.pager";
	}

	@Override
	public String getId() {
		return "C9WTPAGERWIDGET";
	}

}
