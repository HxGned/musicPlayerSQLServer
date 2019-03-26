package com.qqmusic.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qqmusic.entity.User;
import com.qqmusic.service.UserService;

/*
 * 1���û���¼���ʵ�servlet
 * 		��ȡ �û�����
 * 
 * 
 * */

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1634435321526587374L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.getWriter().append("Served at: ").append(req.getContextPath());
		// �������Ӧ��һ����������
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		// 1)��ȡ�û���������
		String name = req.getParameter("username");

		String password = req.getParameter("password");

		System.out.println(name + "--" + password);

		// 2)ȥ��֤ ����û��������������ݿ������Ƿ����

		// 2.1�������� ��װ��ʵ������
		User user = new User(name, password);

		// 2.2)���� Service���� ȥ����ҵ���߼��Ĵ������ݵ���֤��
		UserService userService = new UserService();
		User newUser = userService.loginService(user);

		// 3) ����������ת����
		HttpSession session = req.getSession();
		session.setAttribute("messageTitle", "�û���¼");
		if (newUser == null) {
			// ��¼���ɹ�
			System.out.println("��¼���ɹ�");
			session.setAttribute("messageContext", "�û������������");
		} else {
			// ��¼�ɹ�
			// �������û����� �� Session �������
			// ���ڣ���session������󵥴������Ϊ ��̨��ǰ�˵����� ���ݣ������
			System.out.println("��¼�ɹ�");
			session.setAttribute("messageContext", "���ѳɹ���¼��");
			session.setAttribute("user", newUser);
		}

		resp.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = resp.getWriter();
		out.println("success");

		resp.sendRedirect("./message.jsp");
		// ��ת

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
