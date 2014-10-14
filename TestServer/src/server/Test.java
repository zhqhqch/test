package server;

import net.sf.json.JSONObject;
import vo.User;

public class Test {

	public static void main(String[] args) {
		User o = new User(1, "3434");
		System.out.println(JSONObject.fromObject(o));
	}
}
