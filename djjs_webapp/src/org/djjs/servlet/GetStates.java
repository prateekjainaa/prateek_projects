package org.djjs.servlet;

/**
 * @author Prateek
 * @since 13-june-2009
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.djjs.dao.ContactUSDAO;
import org.djjs.model.AddressVO;
import org.djjs.model.AdminVO;
import org.djjs.model.Container;
import org.djjs.model.Country;
import org.djjs.model.District;
import org.djjs.model.State;
import org.djjs.util.Constants;
import org.djjs.util.JSONUtil;

public class GetStates extends HttpServlet {

	private static final Logger log = Logger.getLogger(GetStates.class);

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
		String json = "";
		// HttpSession ses = req.getSession();
		//Cookie[] c = req.getCookies();
		HttpSession session = req.getSession();
		String cl = null;
		String sl = null;
		Boolean isLoggedin = Boolean.FALSE;
		if (!session.isNew()) {
		    sl = (String)session.getAttribute("stateLimit");
		    cl = (String)session.getAttribute("countryLimit");
		    isLoggedin = Boolean.parseBoolean((String)session.getAttribute("useAddessFilter"));
		}// if
		String fromUI = req.getParameter("param");

		if (!isLoggedin) {
			log.error("application not initialized, or user not logged in.");
			cl = "";//Constants.ALLOW_ALL;
			sl = "";//Constants.ALLOW_ALL;
		}

		if (Constants.GET_COUNTRIES.equals(fromUI)) {
			json = getCountries(cl, req);
		} else if (Constants.GET_STATES.equals(fromUI)) {
			int countryID = Integer.parseInt(req.getParameter("param2"));
			json = getStates(countryID, sl, req);
		} else if (Constants.GET_ADDRESS.equals(fromUI)) {
			int stateID = Integer.parseInt(req.getParameter("param2"));
			json = getAddresses(stateID);
		} else if (Constants.GET_DISTRICTS.equals(fromUI)) {
			int stateID = Integer.parseInt(req.getParameter("param2"));
			json = getDistricts(stateID, req);
		} else {
			System.out.println("Illegal input " + fromUI);
		}

		PrintWriter out = resp.getWriter();
		out.write(json);
	}

	private String getCountries(String countryLimit, HttpServletRequest req) {

		List<Container> list = new ArrayList<Container>();
		if (!countryLimit.equalsIgnoreCase(Constants.ALLOW_ALL)) {
			HttpSession sess = req.getSession();
			AdminVO vo = (AdminVO) sess.getAttribute("Admin");
			Set<Country> count = vo.getCountries();
			for (Country c : count) {
				Container cont = new Container();
				cont.setName(c.getName());
				cont.setValue(((Integer) c.getId()).toString());
				list.add(cont);
			}
		} else {
			ContactUSDAO dao = new ContactUSDAO();
			list = dao.getCountries();
		}

		String json = JSONUtil.getInstance()
				.convertJavaObjectToComboBoxJsonString(list);
		return json;
	}

	private String getStates(int id, String stateLimit, HttpServletRequest req) {
		List<Container> list = null;

		if (!stateLimit.equalsIgnoreCase(Constants.ALLOW_ALL)) {
			HttpSession sess = req.getSession();
			AdminVO vo = (AdminVO) sess.getAttribute("Admin");
			Set<Country> count = vo.getCountries();
			list = new ArrayList<Container>();
			for (Country c : count) {
				if (c.getId() == id) {
					Set<State> st = c.getStates();
					for (State st0 : st) {
						Container co = new Container();
						co.setValue(((Integer) st0.getId()).toString());
						co.setName(st0.getName());
						list.add(co);
					}// for
				}// if
			}
		} else {
			ContactUSDAO dao = new ContactUSDAO();
			list = dao.getStates(id);
		}
		String json = JSONUtil.getInstance()
				.convertJavaObjectToComboBoxJsonString(list);
		return json;
	}

	private String getDistricts(int id, HttpServletRequest req) {
		List<Container> list = new ArrayList<Container>();
		HttpSession sess = req.getSession();
		boolean useAddessFilter = Boolean.parseBoolean((String)sess.getAttribute("useAddessFilter"));
		//Cookie[] c00k = req.getCookies();
		//for (Cookie c : c00k) {
		//	if (c.getName().equalsIgnoreCase("useAddessFilter")) {
				//String cval = c.getValue();
				if (useAddessFilter) {
					AdminVO vo = (AdminVO) sess.getAttribute("Admin");
					if(vo != null) {
					Set<Country> count = vo.getCountries();
						for (Country c0 : count) {
							Set<State> st = c0.getStates();
							for (State st0 : st) {
								if (st0.getId() == id) {
									Set<District> dis = st0.getDistricts();
									for (District d : dis) {
										Container con = new Container();
										con.setName(d.getName());
										con.setValue(((Integer) d.getId())
												.toString());
										list.add(con);
									}// for
								}// if
							}// for

						}// for
					}// if
				}
		//	}
		//}//for

		if (sess==null || sess.isNew()) {
			ContactUSDAO dao = new ContactUSDAO();
			list = dao.getDistricts(id);
		} else {			
			Boolean val = (Boolean)sess.getAttribute("is_super_user");
			if (val) {
				ContactUSDAO dao = new ContactUSDAO();
				list = dao.getDistricts(id);
			}// if
		}
		String json = JSONUtil.getInstance()
				.convertJavaObjectToComboBoxJsonString(list);
		return json;
	}

	private String getAddresses(int sid) {
		ContactUSDAO dao = new ContactUSDAO();
		List<AddressVO> lst = dao.getAddreses(sid);
		AddressVO[] addArr = lst.toArray(new AddressVO[] {});
		String json = JSONUtil.getInstance().convertJavaObjectToJsonString(
				addArr);
		return json;
	}

}
