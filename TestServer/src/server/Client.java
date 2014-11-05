package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.hqch.simple.netty.io.RequestInfo;
import com.hqch.simple.netty.io.RequestParam;
import com.hqch.simple.netty.io.ResponseInfo;
import com.hqch.simple.util.StringUtil;

public class Client {

	private static long time = 0;
	
	public static void main(String[] args) throws Exception {
		ResponseInfo response = new ResponseInfo(null);
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
					info.setSn("UserService.test");
				} else {
					info.setSn("UserService.login");
				}
				
				info.setTime(System.currentTimeMillis());
				
				long start = System.currentTimeMillis();
				OutputStream out = client.getOutputStream();
				for(int j = 0;j<10;j++){
					List<RequestParam> data = new ArrayList<RequestParam>();
					data.add(new RequestParam("userID", 111));
					data.add(new RequestParam("userName", i + "hah 哈哈" + "===" + j));
					System.out.println(i + "$$$$" + j);
					data.add(new RequestParam("sex", true));
					data.add(new RequestParam("name", i + "三的经费和" + "===" + j));
					info.setData(data);
					String msg = JSONObject.fromObject(info).toString() + "\n";
					out.write((msg).getBytes("UTF-8"));
					out.flush();
//					Thread.sleep(200);
//					client = new Socket("localhost", 10002);
				}
				
				BufferedReader br = new BufferedReader(new InputStreamReader(
						client.getInputStream()));
				String msg = null;
				
				long end = System.currentTimeMillis();
				long temp = end - start;
				System.out.println("=======" + temp);
				time += temp;
				
				while ((msg = br.readLine()) != null){
					System.out.println(msg);
				}
//				
				br.close();
				out.close();
			} catch (Exception e){
				System.out.println(e.getMessage());
			}
			System.out.println("!!!!!!!!!" + time);
		}
		
	}
}
