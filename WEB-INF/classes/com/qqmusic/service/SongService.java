package com.qqmusic.service;

import java.util.List;

import com.qqmusic.dao.SongDao;
import com.qqmusic.entity.Song;

/*
 * 
 * �������� ����ģ���ҵ���߼�
 * 
 * */

public class SongService {

	/*
	 * ��ѯ���и����� ҵ���߼�
	 * 	
	 *  ����dao�����ȥ���ݿ���ȡ������
	 * 
	 * 
	 * */
	
	public List<Song> getAllSongService() {
		// TODO Auto-generated method stub
		
		SongDao songDao = new SongDao();
		
		List<Song> list = songDao.getAllSongDao();
		
		return list;
	}
	
	public List<Song> getAllSongBySingerNameService(String name) {
		// TODO Auto-generated method stub
		
		SongDao songDao = new SongDao();
		List<Song> list = songDao.getAllSongBySingerNameDao(name);	
		return list;
	}
	
	public List<Song> getAllSongByAlbumIDService(int id) {
		// TODO Auto-generated method stub
		
		SongDao songDao = new SongDao();
		List<Song> list = songDao.getAllSongByAlbumIDDao(id);	
		return list;
	}
	
	public List<Song> getAllSongByListqIDService(int id) {
		SongDao songDao = new SongDao();
		List<Song> list = songDao.getAllSongByListqIDDao(id);	
		return list;
	}
	/*
	 * 	ͨ�� id ȡ Song�����ҵ���߼�����
	 * 
	 * */
	public Song getSongByNameService(String name){
		
		if(name == null|| name.equals("")){
			//���ݴ���
			return null;
		}
		
		//���� Dao�㷽��ȥ�������ݿ�
		SongDao songDao = new SongDao();
		
		Song song = songDao.getSongByNameDao(name);
		
		return song;
		
	}
	
	public Song getSongByIdService(int id){
		
		if(id == 0){
			//���ݴ���
			return null;
		}
		
		//���� Dao�㷽��ȥ�������ݿ�
		SongDao songDao = new SongDao();
		
		Song song = songDao.getSongByIdDao(id);
		
		return song;
		
	}



}
