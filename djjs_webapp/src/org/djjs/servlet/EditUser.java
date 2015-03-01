/**
 * Aug 11, 2009
 * @author Prateek Jain
 */
package org.djjs.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import org.djjs.dao.EditUserDao;
import org.djjs.model.SearchVO;
import org.djjs.util.MemberUtil;

/**
 * /**
 * 
 * @author pjain
 * 
 */
public class EditUser extends HttpServlet {

    private static final Logger log = Logger.getLogger(AddUser.class);

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

	SearchVO vo = MemberUtil.getInstance().extractMemberInfoFromUI(req);
	EditUserDao dao = new EditUserDao();
	boolean flag = dao.updateUser(vo) && dao.updateUserAddress(vo)
		&& dao.updateUserDeekshaDetails(vo)
		&& dao.updateQualification(vo);
	String message = "Update Failed. Please try again with Valid values or try after some time.";
	if (flag) {
	    message = "Update sucessful.";
	}

	RequestDispatcher disp = req.getRequestDispatcher("messageDisplay.jsp");
	req.setAttribute("showMessage", message);
	disp.forward(req, resp);

    }

}
