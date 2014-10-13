package server;

import server.service.UserService;

import com.hqch.simple.container.Container;
import com.hqch.simple.server.GameServer;

public class Server {

	public static void main(String[] args) {
		Container.get().init();
		
		GameServer server = (GameServer) Container.get().getServer("GameServer");
		server.registerService(new UserService());
	}
}
