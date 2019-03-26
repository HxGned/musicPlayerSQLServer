package com.qqmusic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.qqmusic.entity.Listq;

/*
 * 		�����û� ���ݵ� ���ݳ־ò�
 * */
public class ListqDao {
	
	private Connection conn = null;
	private String generalSql="select * from ShowList";
	private String idname="id";
	private String namename="Name";
	
	private Listq getListqByRS(ResultSet rs)
	{
		Listq tempListq=null;
		try {
			int id = rs.getInt("id");
			String info = rs.getString("linfo");
			String name = rs.getString("Name");
			Boolean open = rs.getBoolean("lopen");
			String style = rs.getString("lstyle");
			int times = rs.getInt("ltimes");
			int uid = rs.getInt("uid");
			String uname = rs.getString("uname");
	
			
			//�����ݷ�װ�� Listq����
			tempListq = new Listq(id,name,uid,style, info,times,open, uname);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return tempListq;
	}

	public List<Listq> getAllListqDao() {
		
		conn = BaseDao.getConnection();
		
		String sql = generalSql;
		
		List<Listq> list = new ArrayList<Listq>();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt =  conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			//�����Ĺ��̣��� List<Listq> ��װ��
			while(rs.next()){
				
				//�����ݷ�װ�� Listq����
				Listq tempListq = getListqByRS(rs);
				
				//��Listq����浽 list ������
				list.add(tempListq);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			BaseDao.close(stmt, rs);
		}
		
		return list;
	
	}

	/*
	 * ͨ�� id ȥ���ݿ��� ȡ�� listq����
	 * 
	 * */
	public Listq getListqByIdDao(int id) {
		
		
		conn = BaseDao.getConnection();
		
		String sql = generalSql+" where "+idname+" = ?";
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		Listq listq = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			
			rs = pstm.executeQuery();
			
			while(rs.next()){
				//�����ݷ�װ�� Listq����
				listq = getListqByRS(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			BaseDao.close(pstm, rs);
		}
		
		return listq;
	}
	
	public Listq getListqByNameDao(String name) {

		conn = BaseDao.getConnection();

		String sql = generalSql+" where "+namename+" = ?"; // ռλ��

		PreparedStatement pstm = null;
		ResultSet rs = null;

		Listq newListq = null;

		try {
			pstm = conn.prepareStatement(sql);
			// ���Ԥ���� sql �е�ռλ��
			pstm.setString(1, name);

			rs = pstm.executeQuery();

			// �������while ѭ����˵�� ��������û������ǲ��������
			while (rs.next()) {

				//�����ݷ�װ�� Listq����
				newListq = getListqByRS(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(pstm, rs);
		}

		return newListq;
	}
	
	
}

