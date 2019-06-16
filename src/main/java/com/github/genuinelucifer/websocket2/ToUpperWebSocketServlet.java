package com.github.genuinelucifer.websocket2;

import javax.servlet.annotation.WebServlet;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
 
@WebServlet(urlPatterns="/toUpper")
public class ToUpperWebSocketServlet  extends WebSocketServlet{
 
    /**
	 * Suppress Eclipse warning
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public void configure(WebSocketServletFactory factory) {
         
          factory.register(ToUpperWebSocket.class);
         
    }
 
}
