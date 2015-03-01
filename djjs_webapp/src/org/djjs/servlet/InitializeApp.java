/**
 * Aug 17, 2009
 * @author Prateek Jain
 */
package org.djjs.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.djjs.dao.AdminDao;
import org.djjs.dao.AreaDao;
import org.djjs.dao.ConsoleAdminDAO;
import org.djjs.dao.MemberDAO;
import org.djjs.model.AdminVO;
import org.djjs.model.Country;
import org.djjs.model.Qualification;
import org.djjs.model.Sewa;
import org.djjs.model.State;
import org.djjs.model.SuperUserVO;
import org.djjs.util.Constants;

/**
 * /**
 * 
 * @author pjain
 * 
 */
public class InitializeApp extends HttpServlet {

	private static final Logger log = Logger.getLogger(InitializeApp.class);

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

		String user = req.getParameter("name");
		String pass = req.getParameter("pass");
		String countryLimit = "";
		String stateLimit = "";
		ConsoleAdminDAO da0 = new ConsoleAdminDAO();
		HttpSession sess = req.getSession(true);
		String dest = "welcome_admin.jsp";
		/** check already logged in user */
		if(null!=sess.getAttribute("is_super_user") && ((Boolean)sess.getAttribute("is_super_user"))) {
		    RequestDispatcher dis = req.getRequestDispatcher(dest);
			dis.forward(req, resp);
			return;
		}		
		/** check already logged in user */
		
		SuperUserVO v0 = da0.validateAdmin(user, pass);
		
		if (v0!=null && v0.getId() > 0) {
			countryLimit = Constants.ALLOW_ALL;
			stateLimit = Constants.ALLOW_ALL;
			sess.setAttribute("is_super_user", true);
			//c00k = new Cookie("is_super_user", "true");
			
		} else {
			sess.setAttribute("is_super_user", false);
			//c00k = new Cookie("is_super_user", "false");
			
			AdminDao dao = new AdminDao();
			AdminVO vo = dao.login(user, pass);
			Set<Country> count = null;
			if ((null == vo) || (vo.getId() <= 0)) {
				String loginForm = "admin_login.jsp";
				RequestDispatcher dis = req.getRequestDispatcher(loginForm);
				dis.forward(req, resp);
				return;
			} else {
				AreaDao dao1 = new AreaDao();
				count = dao1.getCountriesOfAdmin(vo.getId());
				for (Country c : count) {
					Set<State> st = dao1.getAdminCountryStates(vo.getId(), c
							.getId());
					st = dao1.getAdminAssignedDistricts(st, vo.getId());
					for (State s : st) {
						c.addState(s);
					}// for
					vo.addCountry(c);
				}// for
				//HttpSession sess = req.getSession(true);
				sess.setAttribute("Admin", vo);
			}// else

			for (Country c : count) {
				countryLimit += c.getId() + "~";
				Set<State> set = c.getStates();
				for (State s : set) {
					stateLimit += s.getId() + "~";
				}
			}
		}// else
		log.debug("Initialzing session with cl=" + countryLimit + " ,sl="
				+ stateLimit);

		if (null == countryLimit || countryLimit.length() == 0) {
			log.error("Initializing app. with invalid values");
			return;
		}
		sess.setAttribute("countryLimit", countryLimit);
		sess.setAttribute("stateLimit", stateLimit);
		sess.setAttribute("useAddessFilter", "true");
		getServletContext().setAttribute("allSewas", getSewas());
		getServletContext().setAttribute("allQualifications", getQualifications());
		log.debug("session initialzation done");
		RequestDispatcher disp = req.getRequestDispatcher(dest);
		disp.forward(req, resp);
	
		
	}
	
	private Map<Integer, String> getSewas() {
		MemberDAO dao = new MemberDAO();
		List<Sewa> sewas = dao.getSewaList();
		Map<Integer, String> map = new HashMap<Integer, String>();
		for(Sewa s : sewas)
			map.put(s.getValue(), s.getName());
		return map;
	}
	
	private Map<Integer, String> getQualifications() {
		MemberDAO dao = new MemberDAO();
		List<Qualification> qas = dao.getQualificationList();
		Map<Integer, String> map = new HashMap<Integer, String>();
		for(Qualification s : qas)
			map.put(s.getValue(), s.getName());
		return map;
	}
	
}
