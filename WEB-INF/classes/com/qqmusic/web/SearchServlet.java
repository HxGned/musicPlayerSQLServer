package com.qqmusic.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qqmusic.entity.Singer;
import com.qqmusic.entity.Song;
import com.qqmusic.service.SingerService;
import com.qqmusic.service.SongService;

/*
 * 1���û���¼���ʵ�servlet
 * 		��ȡ �û�����
 * 
 * 
 * */

public class SearchServlet extends HttpServlet {

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

		// 1)��ȡ
		String name = req.getParameter("searchname");

		// 2)��֤

		// 2.1�������� ��װ��ʵ������

		// 2.2)���� Service���� ȥ����ҵ���߼��Ĵ������ݵ���֤��
		SongService songService = new SongService();
		Song songResult = songService.getSongByNameService(name);

		SingerService singerService = new SingerService();
		Singer singerResult = singerService.getSingerByNameService(name);

		// 3) ����������ת����
		HttpSession session = req.getSession();
		session.setAttribute("messageTitle", "����");
		if (songResult != null) {
			session.setAttribute("song", songResult);

			Singer singer2 = singerService.getSingerByIdService(songResult.getSinger());
			session.setAttribute("singerName", singer2.getName());
			session.setAttribute("singer", singer2);
			resp.sendRedirect("song.jsp");
		} else if (singerResult != null) {
			session.setAttribute("singer", singerResult);
			resp.sendRedirect("singer.jsp");
		} else {
			session.setAttribute("messageContext", "�Ҳ������������");
			resp.sendRedirect("message.jsp");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
