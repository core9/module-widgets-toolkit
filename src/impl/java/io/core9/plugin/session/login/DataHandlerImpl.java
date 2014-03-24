package io.core9.plugin.session.login;


import io.core9.plugin.flow.AbstractFlowContext;
import io.core9.plugin.flow.FlowExecutor;
import io.core9.plugin.server.request.Request;
import io.core9.plugin.server.vertx.VertxServer;
import io.core9.plugin.session.Session;
import io.core9.plugin.session.SessionManager;
import io.core9.plugin.widgets.datahandler.DataHandler;
import io.core9.plugin.widgets.datahandler.DataHandlerDefaultConfig;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataHandlerImpl {


    private static final transient Logger log = LoggerFactory.getLogger(DataHandlerImpl.class);
	
	public static DataHandler<DataHandlerDefaultConfig> getInstance(final VertxServer server, final SessionManager sessionManager){
		
		return new DataHandler<DataHandlerDefaultConfig>() {



			private HashMap<String, Object> data = new HashMap<>();
			
			@Override
			public Map<String, Object> handle(Request req) {

				Map<String, Object> params = RequestUtils.getParams(req);
				Session session = sessionManager.getVertxSession(req, server);
				AbstractFlowContext context = new Context().setVirtualHost(req.getVirtualHost()).setSession(session).setStateHandler(new StateHandlers()).setRequest(params);

				String flowData = FlowExecutor.execute(context);

				if (flowData.length() == 0 || flowData == null) {
					flowData = "Oeps er ging iets verkeert..";
				}
				
				
				String user = (String) params.get("email");
				String pass = (String) params.get("pass");
				String key = (String) params.get("key");
				
				if(user == null || pass == null){
					user = "";
					pass = "";
				}
				//FIXME needs more sophistication..
				if(key != null && key.length() > 4){
					session.put("encryptionkey", key);
				}
				
				Subject currentUser = sessionManager.getCurrentUser(session);
				
		        if (!currentUser.isAuthenticated()) {
		            UsernamePasswordToken token = new UsernamePasswordToken(user.toLowerCase().trim(), pass.toLowerCase().trim());
		            token.setRememberMe(true);
		            try {
		                currentUser.login(token);
		            } catch (UnknownAccountException uae) {
		                log.info("There is no user with username of " + token.getPrincipal());
		            } catch (IncorrectCredentialsException ice) {
		                log.info("Password for account " + token.getPrincipal() + " was incorrect!");
		            } catch (LockedAccountException lae) {
		                log.info("The account for username " + token.getPrincipal() + " is locked.  " +
		                        "Please contact your administrator to unlock it.");
		            }
		            // ... catch more exceptions here (maybe custom ones specific to your application?
		            catch (AuthenticationException ae) {
		                //unexpected condition?  error?
		            }
		        }else{
		        	req.getResponse().sendRedirect(307, "/admin");
		        }
				
		        String action = (String) params.get("action");
		        if(action.equals("logout")){
		        	currentUser.logout();
		        }
				
				data.put("data", flowData);
				return data;
			}



			@Override
			public DataHandlerDefaultConfig getOptions() {
				return new DataHandlerDefaultConfig();
			}
		};
	}
}
