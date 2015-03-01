package org.djjs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.djjs.dao.AdminDao;
import org.djjs.dao.AreaDao;
import org.djjs.dao.ConsoleAdminDAO;
import org.djjs.model.AdminVO;
import org.djjs.model.Country;
import org.djjs.model.District;
import org.djjs.model.State;
import org.djjs.model.SuperUserVO;

public class ConsoleAdminLogin extends HttpServlet {

	private static final Logger log = Logger.getLogger(ConsoleAdminLogin.class);

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
		if (module.equalsIgnoreCase("adminLogin")) {
			adminLogin(req, resp);
		} else if (module.equalsIgnoreCase("listUsers")) {
			listAdmins(req, resp);
		} else if (module.equalsIgnoreCase("addUser")) {
			addAdmin(req, resp);
		} else if (module.equalsIgnoreCase("adminDetail")) {
			adminDetail(req, resp);
		} else if (module.equalsIgnoreCase("changeActiveState")) {
			changeActiveState(req, resp);
		} else if(module.equalsIgnoreCase("listAdmins")) {
			swapStep1(req, resp);
		} else if(module.equalsIgnoreCase("swapAdmins")) {
			swapStep2(req, resp);
		} else if(module.equalsIgnoreCase("chgPasswd")) {
			chgPasswd(req, resp);
		}
		
	}

	/**
	 * change passowrd for admin not super_admin
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void chgPasswd(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		
		String aid = req.getParameter("aid");
		String pass = req.getParameter("pass");
		int id = Integer.parseInt(aid);		
		String msg = "Failed to change password.";
		AdminDao dao = new AdminDao();
		if(dao.changePassword(id, pass)) {
			msg = "Pasword change request succeded.";
		}
		String dest = "messageDisplay.jsp";
		req.setAttribute("showMessage", msg);
		RequestDispatcher disp = req.getRequestDispatcher(dest);
		disp.forward(req, resp);
		
	}
	
	private void changeActiveState(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		String adminID = req.getParameter("aid");
		String status = req.getParameter("status");
		int aid = Integer.parseInt(adminID);
		ConsoleAdminDAO dao = new ConsoleAdminDAO();
		
		String msg = "modification request failed!";
		if(dao.changeAdminStatus(aid,status)) {
			msg = "modification request successful";
		}
		String dest = "messageDisplay.jsp";
		req.setAttribute("showMessage", msg);
		RequestDispatcher disp = req.getRequestDispatcher(dest);
		disp.forward(req, resp);
		
	}
	
	private void adminDetail(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String adminID = req.getParameter("aid");
		ConsoleAdminDAO dao = new ConsoleAdminDAO();
		int aid = Integer.parseInt(adminID);
		AdminVO vo = dao.getAdminDetails(aid);
		AreaDao dao1 = new AreaDao();
		Set<Country> count = dao1.getCountriesOfAdmin(aid);
		for (Country c : count) {
			Set<State> st = dao1.getAdminCountryStates(aid, c.getId());
			st = dao1.getAdminAssignedDistricts(st, aid);
			for (State s : st) {
				c.addState(s);
			}// for
			vo.addCountry(c);
		}//for
		req.setAttribute("adminDetail", vo);
		String dest = "admin_details.jsp";
		RequestDispatcher disp = req.getRequestDispatcher(dest);
		disp.forward(req, resp);
		
	}

	private void listAdmins(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//String sql = "select * from pi_admins where createdBy=?";
		HttpSession sess = req.getSession();
		SuperUserVO vo = (SuperUserVO) sess.getAttribute("sp");
		log.debug("Listing admins by " + vo.getId());
		ConsoleAdminDAO dao = new ConsoleAdminDAO();
		List<AdminVO> lst = dao.listAdminsBySuperAdmin(vo.getId());
		req.setAttribute("listAdmins", lst);
		String dest = "admins_list.jsp";
		RequestDispatcher disp = req.getRequestDispatcher(dest);
		disp.forward(req, resp);

	}
	
	private void swapStep1(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		HttpSession sess = req.getSession();
		SuperUserVO vo = (SuperUserVO) sess.getAttribute("sp");
		log.debug("Listing admins by " + vo.getId());
		ConsoleAdminDAO dao = new ConsoleAdminDAO();
		List<AdminVO> lst = dao.listAdminsBySuperAdmin(vo.getId());
		StringBuilder sb = new StringBuilder();
		for(AdminVO v0 : lst) {
			sb.append("<option value=\""+v0.getId()+"\">"+v0.getEmail()+"</option>");
		}
		req.setAttribute("swap_admin", sb.toString());
		String dest = "swap_admin.jsp";
		RequestDispatcher disp = req.getRequestDispatcher(dest);
		disp.forward(req, resp);
	}
	
	private void swapStep2(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		
		String a = req.getParameter("admin1");
		String b = req.getParameter("admin2");
		ConsoleAdminDAO dao = new ConsoleAdminDAO();
		String msg = "Assigment Failed! Try again or contact administrator.";
		if(dao.swapAdmins(Integer.parseInt(a), Integer.parseInt(b))) {
			msg = "Assignment Sucessful. Admin " + a + ", swaped with " + b;
		}		
		String dest = "messageDisplay.jsp";
		req.setAttribute("showMessage", msg);
		RequestDispatcher disp = req.getRequestDispatcher(dest);
		disp.forward(req, resp);
	}

	private void adminLogin(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("trying to login as superuser");
		String user = req.getParameter("name");
		String pass = req.getParameter("pass");
		ConsoleAdminDAO dao = new ConsoleAdminDAO();
		SuperUserVO svo = dao.validateAdmin(user, pass);
		HttpSession sess = req.getSession();
		sess.setAttribute("sp", svo);
		Cookie cook = new Cookie("is_super_user", "true");
		cook.setPath("/");
		resp.addCookie(cook);
		
		String dest = "super_admin_login.jsp";
		if (0 < svo.getId()) {
			dest = "super_welcome.jsp";
		}
		RequestDispatcher disp = req.getRequestDispatcher(dest);
		disp.forward(req, resp);

	}

	private void addAdmin(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = req.getParameter("name");
		String userName = req.getParameter("userName");
		String email = req.getParameter("email");
		String pass1 = req.getParameter("pass1");
		HttpSession sess = req.getSession();
		SuperUserVO svo = (SuperUserVO) sess.getAttribute("sp");

		AdminVO vo = new AdminVO();
		vo.setEmail(email);
		vo.setUserName(userName);
		vo.setName(name);
		vo.setPasswd(pass1);
		vo.setSuperAdminID(svo.getId());

		ConsoleAdminDAO dao = new ConsoleAdminDAO();
		vo = dao.addAdmin(vo);
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		String href= "<a href=\"/djjs_webapp/super_welcome.jsp\">Home</a>";
		out.println("Admin added successfully with id=" + vo.getId() + href);

	}

}
