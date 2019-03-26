package com.qqmusic.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.qqmusic.entity.Album;

/*
 * 		�����û� ���ݵ� ���ݳ־ò�
 * */ 
public class AlbumDao {
	
	private Connection conn = null;
	private String generalSql="select * from AlbumTable";
	private String idname="id";
	private String namename="Name";
	
	private Album getAlbumByRS(ResultSet rs)
	{
		Album tempAlbum=null;
		try {
			int id = rs.getInt("id");
			String name = rs.getString("Name");
			String style = rs.getString("astyle");
			String language = rs.getString("alanguage");
			Date date= rs.getDate("adate");
			String company = rs.getString("acompany");
			
			//�����ݷ�װ�� Album����
			tempAlbum = new Album(id,name,style,language,date,company);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return tempAlbum;
	}

	public List<Album> getAllAlbumDao() {
		
		conn = BaseDao.getConnection();
		
		String sql = generalSql;
		
		List<Album> list = new ArrayList<Album>();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt =  conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			//�����Ĺ��̣��� List<Album> ��װ��
			while(rs.next()){
				
				//�����ݷ�װ�� Album����
				Album tempAlbum = getAlbumByRS(rs);
				
				//��Album����浽 list ������
				list.add(tempAlbum);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			BaseDao.close(stmt, rs);
		}
		
		return list;
	
	}

	/*
	 * ͨ�� id ȥ���ݿ��� ȡ�� album����
	 * 
	 * */
	public Album getAlbumByIdDao(int id) {
		
		
		conn = BaseDao.getConnection();
		
		String sql = generalSql+" where "+idname+" = ?";
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		Album album = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			
			rs = pstm.executeQuery();
			
			while(rs.next()){
				//�����ݷ�װ�� Album����
				album = getAlbumByRS(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			BaseDao.close(pstm, rs);
		}
		
		return album;
	}
	
	public Album getAlbumByNameDao(String name) {

		conn = BaseDao.getConnection();

		String sql = generalSql+" where "+namename+" = ?"; // ռλ��

		PreparedStatement pstm = null;
		ResultSet rs = null;

		Album newAlbum = null;

		try {
			pstm = conn.prepareStatement(sql);
			// ���Ԥ���� sql �е�ռλ��
			pstm.setString(1, name);

			rs = pstm.executeQuery();

			// �������while ѭ����˵�� ��������û������ǲ��������
			while (rs.next()) {

				//�����ݷ�װ�� Album����
				newAlbum = getAlbumByRS(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(pstm, rs);
		}

		return newAlbum;
	}
	
	
}

