package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;


import com.hqch.simple.StringUtil;
import com.hqch.simple.netty.io.RequestInfo;
import com.hqch.simple.netty.io.ResponseInfo;

public class Client {

	public static void main(String[] args) throws Exception {
		ResponseInfo response = new ResponseInfo();
		response.setServiceID("userService.login");
		Map<String, Object> data1 = new HashMap<String, Object>();
		data1.put("userName", "sss");
		data1.put("sex", false);
		response.setData(data1);
		
		System.out.println(response);
		
		Client client = new Client();
		for(int i = 0;i < 1;i++){
			client.test(i);
//			Thread.sleep(200);
		}
	}
	
	private void test(int i){
		new PushThread(i).start();
	}
	
	private class PushThread extends Thread {

		
		private int i;
		public PushThread(int i){
			this.i = i;
		}
		
		@Override
		public void run() {
			try{
				Socket client = new Socket("localhost", 10002);
				
				RequestInfo info = new RequestInfo();
				info.setId(StringUtil.generateID());
				if(i % 2 == 1){
					info.setSn("UserService.login");
				} else {
					info.setSn("UserService.test");
				}
				
				info.setTime(System.currentTimeMillis());
				
				Map<String, Object> data = new HashMap<String, Object>();
				
				
				OutputStream out = client.getOutputStream();
				for(int j = 0;j<1;j++){
					data.put("userID", 111);
					data.put("userName", i + "hah 哈哈" + "===" + j);
					System.out.println(i + "$$$$" + j);
					data.put("sex", true);
					info.setData(data);
					String msg = JSONObject.fromObject(info).toString() + "\n";
					out.write((msg).getBytes("UTF-8"));
					out.flush();
//					Thread.sleep(200);
				}
				
				BufferedReader br = new BufferedReader(new InputStreamReader(
						client.getInputStream()));
				String msg = null;
				while ((msg = br.readLine()) != null)
					System.out.println(msg);
//				
				br.close();
				out.close();
			} catch (Exception e){
				System.out.println(e.getMessage());
			}
			
		}
		
	}
}
