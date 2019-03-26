package com.qqmusic.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.qqmusic.entity.Singer;

/*
 * 		�����û� ���ݵ� ���ݳ־ò�
 * 
 * 
 * */
public class SingerDao {
	
	private Connection conn = null;
	private String idname ="id";
	private String namename ="Name";
	private String introductionname ="Sinfo";
	private String generalSql="select * from SingerTable";
	
	private Singer getSingerByRS(ResultSet rs)
	{
		Singer tempSinger=null;
		try {
			int id = rs.getInt(idname);
			String name = rs.getString(namename);
			String introduction = rs.getString(introductionname);
			Boolean sex =rs.getBoolean("ssex");
			Date birthday =rs.getDate("sbirthday");
			String nation =rs.getString("snation");
			String hometown =rs.getString("shometown");
			String image = rs.getString("simage");
			
			//�����ݷ�װ�� Singer����
			tempSinger = new Singer(id,name,introduction,sex,birthday,nation,hometown,image);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return tempSinger;
	}

	public List<Singer> getAllSingerDao() {
		
		conn = BaseDao.getConnection();
		
		String sql = generalSql;
		
		List<Singer> list = new ArrayList<Singer>();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt =  conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			//�����Ĺ��̣��� List<Singer> ��װ��
			while(rs.next()){
				
				//�����ݷ�װ�� Singer����
				Singer tempSinger = getSingerByRS(rs);
				
				//��Singer����浽 list ������
				list.add(tempSinger);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			BaseDao.close(stmt, rs);
		}
		
		return list;
	
	}

	/*
	 * ͨ�� id ȥ���ݿ��� ȡ�� singer����
	 * 
	 * */
	public Singer getSingerByIdDao(int id) {
		
		
		conn = BaseDao.getConnection();
		
		String sql = generalSql+" where "+idname+" = ?";
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		Singer singer = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			
			rs = pstm.executeQuery();
			
			while(rs.next()){
				//�����ݷ�װ�� Singer����
				singer = getSingerByRS(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			BaseDao.close(pstm, rs);
		}
		
		return singer;
	}
	
	public Singer getSingerByNameDao(String name) {

		conn = BaseDao.getConnection();

		String sql = generalSql+" where "+namename+" = ?"; // ռλ��

		PreparedStatement pstm = null;
		ResultSet rs = null;

		Singer newSinger = null;

		try {
			pstm = conn.prepareStatement(sql);
			// ���Ԥ���� sql �е�ռλ��
			pstm.setString(1, name);

			rs = pstm.executeQuery();

			// �������while ѭ����˵�� ��������û������ǲ��������
			while (rs.next()) {

				//�����ݷ�װ�� Singer����
				newSinger = getSingerByRS(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(pstm, rs);
		}

		return newSinger;
	}
	

}

