package dao;

import vo.User;

import com.hqch.simple.resource.sql.ConnectionResourceDAO;
import com.hqch.simple.resource.sql.ResultSetRow;
import com.hqch.simple.resource.sql.ResultSetRowHandler;


public class TestDao extends ConnectionResourceDAO {

	private static ResultSetRowHandler<User> HANDLER = new ResultSetRowHandler<User>() {
		@Override
		public User handleRow(ResultSetRow row) throws Exception {
			User user = new User();
			user.setId(row.getInt("id"));
			user.setName(row.getString("user_name"));
			return user;
		}
	};
	
	public User queryUser(){
		return queryForObject("select * from t_user where id=1", HANDLER);
	}
	
	
	public void insert1(){
		String sql = "insert into t_test_1 values(1, 'aaa')";
		execute(sql);
	}
	
	public void insert2(){
		String sql = "insert into t_test_2 values(2, 'aaa222')";
		execute(sql);
//		throw new DataBaseException("sssssssssssss");
	}
}
