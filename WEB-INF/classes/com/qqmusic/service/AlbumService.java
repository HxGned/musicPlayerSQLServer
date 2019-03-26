package com.qqmusic.service;

import java.util.List;

import com.qqmusic.dao.AlbumDao;
import com.qqmusic.entity.Album;

/*
 * 
 * �������� ר��ģ���ҵ���߼�
 * 
 * */

public class AlbumService {

	/*
	 * ��ѯ����ר���� ҵ���߼�
	 * 	
	 *  ����dao�����ȥ���ݿ���ȡ������
	 * 
	 * 
	 * */
	
	public List<Album> getAllAlbumService() {
		// TODO Auto-generated method stub
		
		AlbumDao albumDao = new AlbumDao();
		
		List<Album> list = albumDao.getAllAlbumDao();
		
		return list;
	}
	
	
	/*
	 * 	ͨ�� id ȡ Album�����ҵ���߼�����
	 * 
	 * */
	public Album getAlbumByNameService(String name){
		
		if(name == null|| name.equals("")){
			//���ݴ���
			return null;
		}
		
		//���� Dao�㷽��ȥ�������ݿ�
		AlbumDao albumDao = new AlbumDao();
		
		Album album = albumDao.getAlbumByNameDao(name);
		
		return album;
		
	}
	
	public Album getAlbumByIdService(int id){
		
		if(id == 0){
			//���ݴ���
			return null;
		}
		
		//���� Dao�㷽��ȥ�������ݿ�
		AlbumDao albumDao = new AlbumDao();
		
		Album album = albumDao.getAlbumByIdDao(id);
		
		return album;
		
	}

}
