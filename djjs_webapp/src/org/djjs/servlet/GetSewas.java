package org.djjs.servlet;

/**
 * @author Prateek
 * @since 13-june-2009
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.djjs.dao.MemberDAO;
import org.djjs.model.Qualification;
import org.djjs.model.Sewa;
import org.djjs.util.JSONUtil;

public class GetSewas extends HttpServlet {

	private static final Logger log = Logger.getLogger(GetSewas.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
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
		String request = req.getParameter("method");
		PrintWriter out = resp.getWriter();
		String json = "";
		if("getAll".equalsIgnoreCase(request)) {
			json = getSewaListJson();
			out.write(json);
		} else if("getAllNoJson".equalsIgnoreCase(request)) {		
			List<Sewa> sewas = getSewaList();
			req.setAttribute("sewas", sewas);
		} else if("qaJSONList".equalsIgnoreCase(request)) {
			json = getQualificationListJson();
			out.write(json);
		} else if("qaList".equalsIgnoreCase(request)) {
			List<Qualification> qas = getQualificationList();
			req.setAttribute("qualificationsList", qas);
		}
		
	}

	private String getSewaListJson() {
		String json;
		List<Sewa> sewas = getSewaList();
		json = JSONUtil.getInstance()
				.convertJavaObjectToComboBoxJsonString(sewas);
		return json;
	}

	private List<Sewa> getSewaList() {
		MemberDAO dao = new MemberDAO();
		List<Sewa> sewas = dao.getSewaList();
		return sewas;
	}
	
	private String getQualificationListJson() {
		String json;
		List<Qualification> qas = getQualificationList();
		json = JSONUtil.getInstance()
				.convertJavaObjectToComboBoxJsonString(qas);
		return json;
	}

	private List<Qualification> getQualificationList() {
		MemberDAO dao = new MemberDAO();
		List<Qualification> sewas = dao.getQualificationList();
		return sewas;
	}


}
