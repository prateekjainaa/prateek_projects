package org.djjs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.djjs.dao.AreaDao;
import org.djjs.dao.ConsoleAdminDAO;
import org.djjs.dao.ContactUSDAO;
import org.djjs.model.AdminVO;
import org.djjs.model.Container;
import org.djjs.model.Country;
import org.djjs.model.District;
import org.djjs.model.State;
import org.djjs.model.SuperUserVO;

public class AssignAreasToAdmin extends HttpServlet {

	private static final Logger log = Logger
			.getLogger(AssignAreasToAdmin.class);

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

		String module = req.getParameter("module");
		String aid = req.getParameter("id");
		String dest = "add_areas.jsp";
		if (module.equals("loadcountries")) {
			String cl = getCountries();
			req.setAttribute("cl", cl);
			req.setAttribute("id", aid);
			RequestDispatcher disp = req.getRequestDispatcher(dest);
			disp.forward(req, resp);
		} else if (module.equalsIgnoreCase("addAreas")) {
			String cid = req.getParameter("countryCodes");
			String sid = req.getParameter("stateCodes");
			String[] didArr = req.getParameterValues("districtCodes");
			String did = "";
			for(String s0 : didArr) {
				did += s0+",";
			}
			aid = req.getParameter("aid");
			AreaDao ad = new AreaDao();
			boolean flag = ad.assignArea(Integer.parseInt(cid), Integer
					.parseInt(sid), did, Integer.parseInt(aid));
			String msg = "Assignment of areas failed. Try again or contact administrator.";
			if (flag) {
				msg = "Assgnment of areas succeded.";
			}
			dest = "messageDisplay.jsp";
			req.setAttribute("showMessage", msg);
			RequestDispatcher disp = req.getRequestDispatcher(dest);
			disp.forward(req, resp);

		} else if (module.equalsIgnoreCase("removeAreas")) {
			String cid = req.getParameter("countryList");
			String sid = req.getParameter("stateList");
			String[] did = req.getParameterValues("districtList");
			aid = req.getParameter("aid");
			AreaDao dao = new AreaDao();
			String dist = "";
			for (String dis : did) {
				dist += dis + ",";
			}
			String msg = "District modification failed.";
			dest = "messageDisplay.jsp";
			if (dao.removeDistricts(Integer.parseInt(aid), dist.substring(0,
					dist.length() - 1), Integer.parseInt(sid))) {
				msg = "District modification succeded.";
			}
			req.setAttribute("showMessage", msg);
			RequestDispatcher disp = req.getRequestDispatcher(dest);
			disp.forward(req, resp);

		} else if (module.equalsIgnoreCase("getAssignedAreas")) {
			aid = req.getParameter("aid");
			int _intAid = Integer.parseInt(aid);
			ConsoleAdminDAO dao = new ConsoleAdminDAO();
			AdminVO vo = dao.getAdminDetails(_intAid);
			AreaDao dao1 = new AreaDao();
			Set<Country> count = dao1.getCountriesOfAdmin(_intAid);
			for (Country c : count) {
				Set<State> st = dao1.getAdminCountryStates(_intAid, c.getId());
				st = dao1.getAdminAssignedDistricts(st, _intAid);
				for (State s : st) {
					c.addState(s);
				}// for
				vo.addCountry(c);
			}// for
			req.setAttribute("adminDetail", vo);
			dest = "remove_areas.jsp";
			RequestDispatcher disp = req.getRequestDispatcher(dest);
			disp.forward(req, resp);
		} else if (module.equalsIgnoreCase("getAllowedStates")) {
			aid = req.getParameter("aid");
			String cid = req.getParameter("cid");
			AreaDao dao = new AreaDao();
			Set<State> set = dao.getAdminCountryStates(Integer.parseInt(aid),
					Integer.parseInt(cid));
			StringBuilder sb = new StringBuilder();
			for (State s : set) {
				sb.append(s.getName() + ":" + s.getId() + ",");
			}
			String ss = sb.substring(0, sb.length() - 1);
			resp.getWriter().print(ss);
		} else if (module.equalsIgnoreCase("getAllowedDistricts")) {
			aid = req.getParameter("aid");
			// String cid = req.getParameter("cid");
			String sid = req.getParameter("sid");
			AreaDao dao = new AreaDao();
			String name = dao.getStateName(Integer.parseInt(sid));
			Set<State> s = new HashSet<State>();
			State st = new State();
			st.setId(Integer.parseInt(sid));
			st.setName(name);
			s.add(st);
			s = dao.getAdminAssignedDistricts(s, Integer.parseInt(aid));
			// only one state will be here. no need to worry.
			StringBuilder sb = new StringBuilder();
			Set<District> ds = null;
			for (State s1 : s) {
				ds = s1.getDistricts();
			}
			for (District ds1 : ds) {
				sb.append(ds1.getName() + ":" + ds1.getId() + ",");
			}
			String ss = sb.substring(0, sb.length() - 1);
			resp.getWriter().print(ss);

		}

	}

	private String getCountries() {
		StringBuilder options = new StringBuilder();
		ContactUSDAO dao = new ContactUSDAO();
		List<Container> lst = dao.getCountries();
		for (Container c : lst) {
			options.append("<option value=\"" + c.getValue() + "\">");
			options.append("" + c.getName() + "</option>");
		}
		return options.toString();
	}

}
