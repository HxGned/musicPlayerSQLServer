package com.qqmusic.service;

import java.util.List;

import com.qqmusic.dao.UserDao;
import com.qqmusic.entity.User;

/*
 * 	�û�������һ��ҵ������
 *  	-- ��¼
 *  	-- ע��
 * 
 * */

public class UserService {

	/*
	 * ������� ��¼��һ��ҵ���߼�
	 * 
	 * 1���ж� user�����Ƿ����Ҫ��
	 * 2���ٽ�User���󽻸� Dao (���ݳ־ò�)����\
	 * 3)��� ��¼�ɹ�������һ��������User ����
	 * 4�������¼���ɹ������� null
	 * 
	 * 
	 * */
	public User loginService(User user) {
		
		//��֤�����Ƿ�Ϸ�
		if(user.getName() == null || "".equals(user.getName())){
			return null;
		}
		
		//���� dao �����
		UserDao userDao = new UserDao();
		
		//���� dao �㷽������¼�ɹ� ������ User���󣻵�¼���ɹ�������null
		User newUser = userDao.loginDao(user);
		
		return newUser;


	}
	
	/*
	 * 	 ע���ҵ���߼� 
	 * 		1)���� servlet  ���Ƿ� ע��ɹ�
	 * 		2)�ж� �û����Ƿ������ݿ����Ѿ����� 
	 *  		���ڣ� ֱ�ӷ���false
	 *  
	 *  		�����ڣ� ע��ɹ�����Ϣ���浽���ݿ�
	 * 
	 * */
	public boolean regService(User user){
		
		//1)�ж��û����Ƿ����
		UserDao userDao = new UserDao();
		User userByName = userDao.getUserByNameDao(user.getName());
		
		if(userByName == null){
			//��ǰ Ҫע����û��������ݿ����ǲ����ڵ�
			//System.out.println("�û�������");
			
			//�� �û����ݱ��浽���ݿ�
			boolean isSave = userDao.saveUserDao(user);
			//����ɹ������� true ,���治�ɹ�������false
			return isSave;
			
		}else{
			//System.out.println("�û����Ѵ���");
			
			return false;
		}
		
	}
	
	public List<User> getAllUserService() {
		// TODO Auto-generated method stub
		
		UserDao userDao = new UserDao();
		
		List<User> list = userDao.getAllUserDao();
		
		return list;
	}

	public User getUserByIdService(int id){
		
		if(id == 0){
			//���ݴ���
			return null;
		}
		
		//���� Dao�㷽��ȥ�������ݿ�
		UserDao userDao = new UserDao();
		
		User user = userDao.getUserByIdDao(id);
		
		return user;
		
	}

}






