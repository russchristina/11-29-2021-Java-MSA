package repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.UserEntity;
import dao.UserLoginDAOInterface;
import util.ConnectUtil;

public class UserLoginDAO implements UserLoginDAOInterface{

	@Override
	public UserEntity insertUser(String username, String password, boolean isManager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserEntity getUser(String username) {
		UserEntity  userEntity = null;
		
		final String SQL= "select * from Login where username = ?";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectUtil.getConnection();
			stmt = conn.prepareStatement(SQL);
			
			stmt.setString(1, username);
			set = stmt.executeQuery();
			
			if(set.next()) {
				userEntity = new UserEntity(
						set.getInt(1),
						set.getString(2),
						set.getString(3),
						set.getBoolean(4)
						);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			
			try {
				conn.close();
				stmt.close();
				set.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
						
		}
				
		return userEntity;
		
	}

	@Override
	public UserEntity updateUsername(int id, String newUsername) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserEntity deleteUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
