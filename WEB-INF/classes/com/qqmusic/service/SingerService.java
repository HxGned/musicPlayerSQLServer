package com.qqmusic.service;

import java.util.List;

import com.qqmusic.dao.SingerDao;
import com.qqmusic.entity.Singer;

/*
 * 
 * �������� ����ģ���ҵ���߼�
 * 
 * */

public class SingerService {

	/*
	 * ��ѯ���и��ֵ� ҵ���߼�
	 * 	
	 *  ����dao�����ȥ���ݿ���ȡ������
	 * 
	 * 
	 * */
	
	public List<Singer> getAllSingerService() {
		// TODO Auto-generated method stub
		
		SingerDao singerDao = new SingerDao();
		
		List<Singer> list = singerDao.getAllSingerDao();
		
		return list;
	}
	
	/*
	 * 	ͨ�� id ȡ Singer�����ҵ���߼�����
	 * 
	 * */
	public Singer getSingerByNameService(String name){
		
		if(name == null|| name.equals("")){
			//���ݴ���
			return null;
		}
		
		//���� Dao�㷽��ȥ�������ݿ�
		SingerDao singerDao = new SingerDao();
		
		Singer singer = singerDao.getSingerByNameDao(name);
		
		return singer;
		
	}
	
	public Singer getSingerByIdService(int id){
		
		if(id == 0){
			//���ݴ���
			return null;
		}
		
		//���� Dao�㷽��ȥ�������ݿ�
		SingerDao singerDao = new SingerDao();
		
		Singer singer = singerDao.getSingerByIdDao(id);
		
		return singer;
		
	}


}
