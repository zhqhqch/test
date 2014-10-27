package server;

import vo.User;
import action.TestAction;

import com.hqch.simple.container.Container;
import com.hqch.simple.web.HttpHandler;
import com.hqch.simple.web.HttpMessage;

public class UserServlet implements HttpHandler {

	public void test(HttpMessage request){
		System.out.println(request.getRequestParams());
		TestAction testAction = Container.get().
				createRemoteAction(TestAction.class, "system");
		
		User user = testAction.say(request.getRequestParams().get("name").toString());
		request.back("哈哈ok" + user);
		System.out.println("eeeeeeeee");
	}
	
}
