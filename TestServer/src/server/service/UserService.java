package server.service;

import java.util.concurrent.TimeUnit;

import vo.User;
import action.TestAction;

import com.hqch.simple.container.Container;
import com.hqch.simple.server.GameRoom;
import com.hqch.simple.server.GameService;
import com.hqch.simple.server.ServiceContext;
import com.hqch.simple.server.Synchronized;

public class UserService implements GameService{

	@Override
	public void beforeService(ServiceContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterService(ServiceContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(ServiceContext context, Throwable e) {
		// TODO Auto-generated method stub
		
	}

	public UserService(){
		System.out.println("UserSerivce init");
	}
	
	@Synchronized
	public void login(ServiceContext context){
		String userName = context.getAsString("userName");
		int age = context.getAsInt(false, "age");
		boolean sex = context.getAsBoolean(true, "sex");
		context.getSession().put("ggg", "dddddd");
		System.out.println(userName + "---" + age + "----" + sex);
		
//		try {
//			Thread.sleep(12000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		context.sendMessage("login", "登录成功");
		
		System.out.println(context.getSession().get("ggg"));
	}
	
	public void heartbeat(ServiceContext context){
		System.out.println("#########");
	}
	
	public void test(ServiceContext context){
		String name = context.getAsString("name");
		TestAction testAction = Container.get().
				createRemoteAction(TestAction.class, "system");
		
		User user = testAction.say(name);
//		String user = testAction.test(name);
		System.out.println(user + "###############");
		context.sendMessage("test", user);
	}
	
	
	public void game(ServiceContext context){
		GameRoom gameRoom = Container.get().createGameRoom("aaaaaaaaa");
		gameRoom.scheduleAtFixedRate(new PKRunnable(3, 10, TimeUnit.SECONDS));
	}
}
