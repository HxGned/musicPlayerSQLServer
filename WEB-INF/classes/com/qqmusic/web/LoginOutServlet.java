package com.qqmusic.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * 1���û���¼���ʵ�servlet
 * 		��ȡ �û����� 
 * 
 * 
 * */


public class LoginOutServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1634435321526587374L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.getWriter().append("Served at: ").append(req.getContextPath());
		//�������Ӧ��һ����������
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		
		HttpSession session  =req.getSession();
		if(session.getAttribute("user")==null)
		{
			session.setAttribute("messageTitle","�û�ע��");
			session.setAttribute("messageContext","����û�е�¼��");
		}
		else
		{
			session.setAttribute("user",null);
			session.setAttribute("messageTitle","�û�ע��");
			session.setAttribute("messageContext","���ѳɹ�ע����");
		}
		resp.sendRedirect("message.jsp");

		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
