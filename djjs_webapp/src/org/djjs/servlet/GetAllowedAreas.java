/**
 * Aug 8, 2009
 * @author Prateek Jain
 */
package org.djjs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.djjs.dao.AreaDao;
import org.djjs.model.AdminVO;
import org.djjs.model.Country;
import org.djjs.model.District;
import org.djjs.model.State;

/**
 * /**
 * 
 * @author pjain
 * 
 */
public class GetAllowedAreas extends HttpServlet {

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doProcess(req, resp);
	}

	public void doProcess(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String cid = req.getParameter("cid");
		String sid = req.getParameter("sid");
		if (null != cid && cid.length() > 0) {
			PrintWriter pw = resp.getWriter();
			HttpSession sess = req.getSession();
			AdminVO vo = (AdminVO) sess.getAttribute("Admin");
			AreaDao da = new AreaDao();
			Set<State> allowedStates = da.getAdminCountryStates(vo.getId(),
					Integer.parseInt(cid));

			StringBuilder bld = new StringBuilder();
			for (State cnt : allowedStates) {
				bld.append(cnt.getName() + ":" + cnt.getId() + ",");
			}
			bld.delete(bld.length() - 1, bld.length());
			pw.write(bld.toString());
			pw.close();
			return;
		} else if (null != sid && sid.length() > 0) {
			PrintWriter pw = resp.getWriter();
			HttpSession sess = req.getSession();
			AdminVO vo = (AdminVO) sess.getAttribute("Admin");
			Set<District> ds = getAllowedDistricts(vo, Integer.parseInt(sid));
			
			
			StringBuilder bld = new StringBuilder();
			for (District cnt : ds) {
				bld.append(cnt.getName() + ":" + cnt.getId() + ",");
			}
			bld.delete(bld.length() - 1, bld.length());
			pw.write(bld.toString());
			pw.close();
			return;
		}
		

	}
	
	private Set<District> getAllowedDistricts(AdminVO vo, int sid) {
		Set<Country> setC = vo.getCountries();
		for(Country c : setC) {
			Set<State> ss = c.getStates();
			for(State s : ss) {
				if(sid==s.getId()) {
					return s.getDistricts();
				}
			}
		}
		return new HashSet();
		
	}
	
	

}
