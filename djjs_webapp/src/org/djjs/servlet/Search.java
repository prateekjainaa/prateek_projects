package org.djjs.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
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
import org.djjs.dao.SearchDAO;
import org.djjs.model.AdminVO;
import org.djjs.model.MemberVO;
import org.djjs.model.SearchVO;
import org.djjs.util.Constants;
import org.djjs.util.SearchUtil;

public class Search extends HttpServlet {

	private static final long serialVersionUID = 4510670359799512226L;
	private static final Logger log = Logger.getLogger(Search.class);

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

	public void doProcess(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// Cookie[] c = req.getCookies();
		HttpSession session = req.getSession();
		String cl = null;
		String sl = null;
		int adminId = 0;
		Boolean isLoggedin = Boolean.FALSE;
		if (!session.isNew()) {
			sl = (String) session.getAttribute("stateLimit");
			cl = (String) session.getAttribute("countryLimit");
			AdminVO adminvo = (AdminVO) session.getAttribute("Admin");
			adminId = adminvo.getId();
			isLoggedin = true;
		} else {
			return;
		}

		if ((null != sl) && (sl.endsWith("~"))) {
			sl = sl.substring(0, sl.length() - 1);
		}
		if ((null != cl) && (cl.endsWith("~"))) {
			cl = cl.substring(0, cl.length() - 1);
		}

		SearchVO vo = new SearchVO();
		HttpSession sess = req.getSession(true);
		sess.removeAttribute("members");
		vo.setAdminId(adminId);
		vo = (new SearchUtil()).fillSearchVO(req, vo);
		String basePointer = req.getParameter("base");
		if (null == basePointer || basePointer.length() == 0) {
			basePointer = "0";
		}

		boolean fillCountry = isLoggedin
				&& (!cl.equalsIgnoreCase(Constants.ALLOW_ALL));
		boolean fillState = isLoggedin
				&& (!sl.equalsIgnoreCase(Constants.ALLOW_ALL));
		if (fillCountry) {
			vo.setCountryCodes(cl);
		}
		if (fillState) {
			vo.setStateCodes(sl);
		}

		Map<String, Object> memberMap = new SearchUtil()
				.makeQueryFromMemberTable(vo);
		SearchUtil util = new SearchUtil();
		String query1 = util.makeQuery1(memberMap);
		SearchDAO dao = new SearchDAO();
		Set<Integer> set1 = dao.executeQueryForMemberID(query1);

		String query2 = util.makeQuery2(vo);
		Set<Integer> set2 = null;
		if ((query2 != null) && (query2.length() > 0)) {
			set2 = dao.executeQueryForMemberID(query2);
		}

		Set<Integer> set3 = null;
		String deeksha = vo.getDeekshaDate();
		if (null != deeksha && deeksha.length() > 0) {
			Set<Integer> temp = set2;
			if (set2 == null || set2.size() == 0) {
				temp = set1;
			}
			String query3 = util.makeQuery3(vo);
			set3 = dao.executeQueryForMemberID(query3);
		}

		String qualification = vo.getQualificationText();
		String occ = vo.getOccupationText();
		String oth = vo.getOtherProfText();
		Set<Integer> set4 = null;
		if (!(isNull(qualification) && isNull(occ) && isNull(oth))) {
			Set<Integer> temp = set3;
			if (set3 == null || set3.size() == 0) {
				temp = set2;
			} else if (temp == null || temp.size() == 0) {
				temp = set1;
			}
			String query4 = util.makeQuery4(vo);
			set4 = dao.executeQueryForMemberID(query4);
		}
		Set<Integer> useThis = intersection(set1, set2);
		useThis = intersection(useThis, set3);
		useThis = intersection(useThis, set4);

		log.debug("searching for set :" + useThis);
		List<MemberVO> lst = new ArrayList<MemberVO>();
		if(null!=useThis && useThis.size()>0) {
			lst = dao.getSearchedUsersData(useThis);
		}
		req.setAttribute("members", lst);

		String path = "searchResults.jsp";
		RequestDispatcher disp = req.getRequestDispatcher(path);
		disp.forward(req, resp);
	}

	private boolean isNull(String s) {
		if ((s == null) || (s.length() == 0)) {
			return true;
		}
		return false;
	}

	private Set<Integer> union(Set<Integer> set1, Set<Integer> set2) {

		if (set2 == null) {
			return set1;
		} else if (set1 == null) {
			return set2;
		}
		Set<Integer> set = new HashSet<Integer>();
		set.addAll(set2);
		set.addAll(set1);
		return set;
	}

	private Set<Integer> intersection(Set<Integer> set1, Set<Integer> set2) {

		if (set2 == null) {
			return set1;
		}

		Set<Integer> set = new HashSet<Integer>();
		for (Integer in : set2) {
			if (set1.contains(in)) {
				set.add(in);
			}// if

		}// for
		return set;
	}

}
