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

public class RegServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6203658875221136960L;

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
		String email = req.getParameter("email");

		User user = new User(name, password, email);

		UserService userService = new UserService();
		boolean isReg = userService.regService(user);

		HttpSession session = req.getSession();
		session.setAttribute("messageTitle", "�û�ע��");
		if (isReg) {
			user = userService.loginService(user);
			System.out.println("ע��ɹ�");
			session.setAttribute("messageContext", "���ѳɹ�ע�ᣡ");
			session.setAttribute("user", user);
		} else {
			session.setAttribute("messageContext", "ע��ʧ�ܣ�");
			System.out.println("ע��ʧ��");
		}

		resp.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = resp.getWriter();
		out.println("success");

		resp.sendRedirect("message.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
