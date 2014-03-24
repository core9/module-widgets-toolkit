package io.core9.plugin.widgets.c9wt;

import java.io.IOException;
import java.io.InputStreamReader;

import com.google.common.io.CharStreams;

import io.core9.plugin.template.closure.ClosureTemplateEngine;
import net.xeoh.plugins.base.annotations.PluginImplementation;
import net.xeoh.plugins.base.annotations.injections.InjectPlugin;

@PluginImplementation
public class C9wtPluginImpl implements C9wtPlugin {

	@InjectPlugin
	public ClosureTemplateEngine engine;

	@Override
	public void execute() {
		try {
			engine.addString("io.core9.c9wt.pager.soy", CharStreams.toString(new InputStreamReader(this.getClass().getResourceAsStream("/pager/pager.soy"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
