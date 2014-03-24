package io.core9.plugin.session.login;

import static au.com.ds.ef.FlowBuilder.from;
import static au.com.ds.ef.FlowBuilder.on;
import static io.core9.plugin.session.login.StateHandlers.Events.*;
import static io.core9.plugin.session.login.StateHandlers.States.*;

import java.util.HashMap;
import java.util.Map;

import au.com.ds.ef.StateEnum;
import au.com.ds.ef.call.ContextHandler;
import au.com.ds.ef.call.StateHandler;
import io.core9.plugin.flow.AbstractFlowStateHandler;
import io.core9.plugin.flow.AbstractFlowContext;
import io.core9.plugin.session.Session;

public class StateHandlers extends AbstractFlowStateHandler {

	private Map<String, Object> req = new HashMap<String, Object>();


    private Session session;

	protected String action;
	
	private Login login;


	public enum Events implements au.com.ds.ef.EventEnum {
		initialize, start, terminate
	}

	public enum States implements au.com.ds.ef.StateEnum {
		UNINITIALIZED, WALKING, RUNNING, DONE, SHOW_CHECKOUT
	}

	public void run() {

		super.flow =

		from(UNINITIALIZED).transit(
		        on(initialize).to(SHOW_CHECKOUT).transit(on(terminate).finish(DONE)),
		        on(start).to(WALKING).transit(on(terminate).finish(DONE)));

		// do some setup and sanatizing etc...
		flow.whenEnter(UNINITIALIZED, new ContextHandler<AbstractFlowContext>() {



			@Override
			public void call(AbstractFlowContext context) throws Exception {
				
				
				req = context.getRequest();
				action = (String) req.get("action");

				session = ((Context) context).getSession();

				login = (Login) session.get("login");
				if (login == null) {
					login = new LoginImpl(context.getSessionId());
				}
				
				LoginRenderer renderer = new HtmlRenderer().setRequest(req);

				login.setRenderer(renderer);

				session.put("login", login);

				context.trigger(start);

			}

		});


		flow.whenEnter(WALKING, new ContextHandler<AbstractFlowContext>() {
			@Override
			public void call(AbstractFlowContext context) throws Exception {

				String res = login.toHtml();
				context.setData("<br>" + res);
				context.trigger(terminate);

			}
		});

		flow.whenEnter(RUNNING, new ContextHandler<AbstractFlowContext>() {
			@Override
			public void call(AbstractFlowContext context) throws Exception {
				context.setData("running");
				context.trigger(terminate);
			}
		});

		flow.whenFinalState(new StateHandler<AbstractFlowContext>() {
			@Override
			public void call(StateEnum state, AbstractFlowContext context) throws Exception {
				context.setResult("final");
			}
		});

	}

}
