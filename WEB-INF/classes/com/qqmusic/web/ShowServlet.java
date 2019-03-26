package com.qqmusic.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qqmusic.entity.Album;
import com.qqmusic.entity.Listq;
import com.qqmusic.entity.Singer;
import com.qqmusic.entity.Song;
import com.qqmusic.entity.User;
import com.qqmusic.service.AlbumService;
import com.qqmusic.service.ListqService;
import com.qqmusic.service.SingerService;
import com.qqmusic.service.SongService;
import com.qqmusic.service.UserService;

/*
 * 1���û���¼���ʵ�servlet
 * 		��ȡ �û�����
 * 
 * 
 * */

public class ShowServlet extends HttpServlet {

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
		resp.setHeader("Access-Control-Allow-Origin", "*");

		// 1)��ȡ

		String singername = req.getParameter("singerid");
		int singerid = 0;
		if (singername != null) {
			singerid = Integer.parseInt(singername);
		}
		String songname = req.getParameter("songid");
		int songid = 0;
		if (songname != null) {
			songid = Integer.parseInt(songname);
		}
		String albumname = req.getParameter("albumid");
		int albumid = 0;
		if (albumname != null) {
			albumid = Integer.parseInt(albumname);
		}
		String listqname = req.getParameter("listqid");
		int listqid = 0;
		if (listqname != null) {
			listqid = Integer.parseInt(listqname);
		}
		String uidname = req.getParameter("uid");
		int uid = 0;
		if (uidname != null) {
			uid = Integer.parseInt(uidname);
		}

		// 2)��֤

		// 2.1�������� ��װ��ʵ������

		// 2.2)���� Service���� ȥ����ҵ���߼��Ĵ������ݵ���֤��
		SongService songService = new SongService();

		Song songResult = songService.getSongByIdService(songid);

		SingerService singerService = new SingerService();

		Singer singerResult = singerService.getSingerByIdService(singerid);

		AlbumService albumService = new AlbumService();

		Album albumResult = albumService.getAlbumByIdService(albumid);

		ListqService listqService = new ListqService();

		Listq listqResult = listqService.getListqByIdService(listqid);

		UserService userService = new UserService();
		User userResult = userService.getUserByIdService(uid);

		// 3) ����������ת����
		HttpSession session = req.getSession();
		session.setAttribute("messageTitle", "����");
		if (songResult != null) {
			session.setAttribute("song", songResult);
			resp.sendRedirect("song.jsp");
		} else if (singerResult != null) {
			session.setAttribute("singer", singerResult);
			List<Song> songList = songService.getAllSongBySingerNameService(singerResult.getName());
			session.setAttribute("tempList", songList);
			resp.sendRedirect("singer.jsp");
		} else if (albumResult != null) {
			session.setAttribute("album", albumResult);
			List<Song> songList = songService.getAllSongByAlbumIDService(albumResult.getId());
			session.setAttribute("tempList", songList);
			resp.sendRedirect("album.jsp");
		} else if (listqResult != null) {
			session.setAttribute("listq", listqResult);
			List<Song> songList = songService.getAllSongByListqIDService(listqResult.getId());
			session.setAttribute("tempList", songList);
			resp.sendRedirect("listq.jsp");
		} else if (userResult != null) {
			session.setAttribute("targetuser", userResult);
			resp.sendRedirect("user.jsp");
		} else {
			session.setAttribute("messageContext", "û���������");
			resp.sendRedirect("message.jsp");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
