package org.djjs.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class Logout extends HttpServlet{

	private static final Logger log = Logger.getLogger(Logout.class);
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doProcess(req, resp);
	}

	private void doProcess(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		log.debug("logging out, destroying all relevent information from cache...");
		req.getSession().setMaxInactiveInterval(0);
		Cookie[] cook = req.getCookies();
		for(Cookie c : cook) {
			c.setMaxAge(0);
			resp.addCookie(c);			
		}
		log.debug("Successfully destroyed all cache information for user");
		RequestDispatcher dis = req.getRequestDispatcher("index.html");
		dis.forward(req, resp);
		
	}
	
	
}
