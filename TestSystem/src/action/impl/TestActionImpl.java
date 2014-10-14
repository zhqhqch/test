package action.impl;

import vo.User;
import action.TestAction;

public class TestActionImpl implements TestAction {

	@Override
	public User say(String name) {
		System.out.println("sdfsafsdfdsfdsf" + name);
		return new User(1, name);
	}

	@Override
	public String test(String name) {
		return "@@@@@@@@@@2" + name;
	}

	
}
