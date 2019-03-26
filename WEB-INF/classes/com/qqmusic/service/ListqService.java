package com.qqmusic.service;

import java.util.List;

import com.qqmusic.dao.ListqDao;
import com.qqmusic.entity.Listq;

/*
 * 
 * �������� �赥ģ���ҵ���߼�
 * 
 * */

public class ListqService {

	/*
	 * ��ѯ���и赥�� ҵ���߼�
	 * 	
	 *  ����dao�����ȥ���ݿ���ȡ������
	 * 
	 * 
	 * */
	
	public List<Listq> getAllListqService() {
		// TODO Auto-generated method stub
		
		ListqDao listqDao = new ListqDao();
		
		List<Listq> list = listqDao.getAllListqDao();
		
		return list;
	}
	
	
	/*
	 * 	ͨ�� id ȡ Listq�����ҵ���߼�����
	 * 
	 * */
	public Listq getListqByNameService(String name){
		
		if(name == null|| name.equals("")){
			//���ݴ���
			return null;
		}
		
		//���� Dao�㷽��ȥ�������ݿ�
		ListqDao listqDao = new ListqDao();
		
		Listq listq = listqDao.getListqByNameDao(name);
		
		return listq;
		
	}
	
	public Listq getListqByIdService(int id){
		
		if(id == 0){
			//���ݴ���
			return null;
		}
		
		//���� Dao�㷽��ȥ�������ݿ�
		ListqDao listqDao = new ListqDao();
		
		Listq listq = listqDao.getListqByIdDao(id);
		
		return listq;
		
	}

}
