package server;

import com.hqch.simple.container.Container;
import com.hqch.simple.web.WebServer;

public class WebServerApp {

	public static void main(String[] args) {
		Container.get().init();
		
		WebServer server = (WebServer) Container.get().getServer("WebServer");
		server.registerHandler("user", new UserServlet());
	}
}
