package action.impl;

import com.hqch.simple.container.Container;
import com.hqch.simple.resource.sql.ConnectionResource;
import com.hqch.simple.rpc.NotifEvent;
import com.hqch.simple.rpc.NotificationManager;
import com.hqch.simple.rpc.Transaction;
import com.hqch.simple.rpc.server.RPCServer;

import dao.TestDao;
import vo.User;
import action.TestAction;

public class TestActionImpl implements TestAction {

	@Transaction
	@Override
	public User say(String name) {
		System.out.println("sdfsafsdfdsfdsf" + name);
		
		ConnectionResource conn = (ConnectionResource) Container.get()
				.getDataSourceByName("dataSource");
		
		TestDao dao = new TestDao();
		dao.setConnectionResource(conn);
		
		User user = dao.queryUser();
//		dao.insert1();
//		dao.insert2();
		
//		int i = 1/0;
//		System.out.println(i);
		
		RPCServer server = (RPCServer) Container.get().getServer("RPCServer");
		NotificationManager manager = server.getNotificationManager();
		
		NotifEvent event = new NotifEvent();
		event.setType(-111);
		event.setObj(user);
		
		manager.sendNotif(event);
		return user;
	}

	@Override
	public String test(String name) {
		return "@@@@@@@@@@2" + name;
	}

	
}
