package com.qqmusic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.qqmusic.entity.User;


/*
 * 		�����û� ���ݵ� ���ݳ־ò�
 * 
 * 
 * */

public class UserDao {

	private Connection conn = null;
	private String idname ="uid";
	private String namename ="uname";
	private String passwordname ="upassword";
	private String emailname ="uemail";
	private String generalSql="select * from UserTable";
	
	private User getUserByRS(ResultSet rs) {
		User tempUser=null;
		try {
			int id = rs.getInt(idname);
			String newName = rs.getString(namename);
			String password = rs.getString(passwordname);
			String email = rs.getString(emailname);
			tempUser = new User(id,newName, password, email);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return tempUser;
	}
	/*
	 * ������� ���� ȥ���ݿ�����ȥ��֤ ��ǰ�������Ƿ� �ܹ���¼�ɹ�
	 * 
	 * 1)�������ݿ������
	 * 
	 */
	public User loginDao(User user) {

		conn = BaseDao.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		User newUser = null;

		String sql = generalSql;

		System.out.println("--" + conn);

		try {
			// ��sql ����Ԥ����
			pstm = conn.prepareStatement(sql);
			// ȥ���ݿ�����ִ��sql��䣬���� ResultSet����
			rs = pstm.executeQuery();

			// ����ResultSet����
			while (rs.next()) {

				// ͨ���ֶ���ȡ����
				String uname = rs.getString(namename);
				String password = rs.getString(passwordname);

				if (uname.equals(user.getName()) && password.equals(user.getPassword())) {
					// �û��������� ƥ��ɹ�
					int id = rs.getInt(idname);
					String email = rs.getString(emailname);
					// ƥ��ɹ������� User ������������
					newUser = new User(id,uname, password, email);
					break;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(pstm, rs);
		}

		return newUser;

	}

	/*
	 * ͨ�� �û������� ȥ���Ҷ�Ӧ���û�
	 * 
	 */
	public User getUserByNameDao(String name) {

		conn = BaseDao.getConnection();

		String sql = generalSql+" where "+namename+" = ?"; // ռλ��

		PreparedStatement pstm = null;
		ResultSet rs = null;

		User newUser = null;

		try {
			pstm = conn.prepareStatement(sql);
			// ���Ԥ���� sql �е�ռλ��
			pstm.setString(1, name);

			rs = pstm.executeQuery();

			// �������while ѭ����˵�� ��������û������ǲ��������
			while (rs.next()) {
				int id = rs.getInt(idname);
				String newName = rs.getString(namename);
				String password = rs.getString(passwordname);
				String email = rs.getString(emailname);
				newUser = new User(id,newName, password, email);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(pstm, rs);
		}

		return newUser;
	}

	/*
	 * ���� һ���µ��û������ݿ�
	 * 
	 * 
	 */
	public boolean saveUserDao(User user) {

		conn = BaseDao.getConnection();

		PreparedStatement pstm = null;
		// ��Ӱ�������
		int row = 0;

		String sql = "INSERT INTO UserTable(uname, upassword, uemail) VALUES (?, ?,?)";

		try {
			pstm = conn.prepareStatement(sql);

			pstm.setString(1, user.getName());
			pstm.setString(2, user.getPassword());
			pstm.setString(3, user.getEmail());

			// ִ��sql ��䣬�����ķ���ֵ ����Ӱ�������
			row = pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(pstm, null);
		}

		// �����Ӱ������� Ϊ 1 ��
		if (row == 1) {
			return true;
		} else {
			return false;
		}

	}

	public List<User> getAllUserDao() {
		conn = BaseDao.getConnection();
		
		String sql = generalSql;
		
		List<User> list = new ArrayList<User>();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt =  conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			//�����Ĺ��̣��� List<user> ��װ��
			while(rs.next()){
				
				//��ȡ ��ǰ��һ���ֶε���Ϣ
				int id = rs.getInt(idname);
				String name = rs.getString(namename);
				String password = rs.getString(passwordname);
				String email = rs.getString(emailname);
				//�����ݷ�װ�� user����
				User tempuser = new User(id,name,password,email);
				
				//��user����浽 list ������
				list.add(tempuser);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			BaseDao.close(stmt, rs);
		}
		
		return list;
	
	}

	public User getUserByIdDao(int id) {

		conn = BaseDao.getConnection();

		String sql = generalSql+" where UserTable.uid = ?";

		PreparedStatement pstm = null;
		ResultSet rs = null;

		User user = null;

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);

			rs = pstm.executeQuery();

			while (rs.next()) {
				user = getUserByRS(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			BaseDao.close(pstm, rs);
		}

		return user;
	}


	
	
}
