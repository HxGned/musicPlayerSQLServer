package com.qqmusic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * 1����ȡ Connection ����  
 * 		ʹ�� ����ģʽ ȥ��ȡ Connection ����
 * 2���ͷ������Դ
 * */

public class BaseDao {

	// ά��һ��˽�е� Connection ����
	private static Connection conn = null;

	// �ṩһ�������ķ����ñ����ܻ�ȡ�����Connection ����
	public static Connection getConnection() {

		if (conn == null) {

			try {
				String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
				String dbURL = "jdbc:sqlserver://www.adamqqq.com:1433;DatabaseName=qqmusic";
				String userName = "LoginAdmin";
				String userPwd = "qqmusic-123";

				// ����Connection����
				// 1)���� jdbc������

				Class.forName(driverName);
				// 2)�������ݿ�ʵ������ȡ���Ӷ���
				conn = DriverManager.getConnection(dbURL, userName, userPwd);

				System.out.println(conn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// �Զ����쳣
				System.out.println("���ݿ������쳣");
			}

		}

		return conn;

	}

	/*
	 * �ر������Դ
	 * 
	 */

	public static void close(Statement stmt, ResultSet rs) {

		try {

			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		} catch (Exception e) {
			System.out.println("�ͷ���Դ�쳣");
			e.printStackTrace();
		}

	}
}
