package io.core9.plugin.session.login;

import io.core9.plugin.flow.AbstractFlowContext;
import io.core9.plugin.server.VirtualHost;
import io.core9.plugin.session.Session;

public class Context extends AbstractFlowContext {

	private static final long serialVersionUID = 5460235636668422129L;

    private Session session;

	private VirtualHost virtualhost;

	public AbstractFlowContext setSession(Session session) {
		this.session = session;
		setSessionId(session.getSessionId());
		setCache(session.getCache());
	    return this;
    }

	public Session getSession(){
		return session;
	}
	

	
	public Context setVirtualHost(VirtualHost virtualhost){
		this.virtualhost = virtualhost;
		return this;
	}
	
	public VirtualHost getVirtualHost(){
		return virtualhost;
	}
}
