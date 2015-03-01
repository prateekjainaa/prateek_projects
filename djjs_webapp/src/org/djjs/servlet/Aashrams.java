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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.djjs.dao.AreaDao;
import org.djjs.dao.ContactUSDAO;
import org.djjs.model.AddressVO;
import org.djjs.model.AdminVO;
import org.djjs.model.Container;
import org.djjs.model.Country;
import org.djjs.model.District;
import org.djjs.model.State;
import org.djjs.util.Constants;
import org.djjs.util.JSONUtil;

public class Aashrams extends HttpServlet {

    private static final Logger log = Logger.getLogger(Aashrams.class);

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
	ContactUSDAO dao = new ContactUSDAO();
	List<Container> list = dao.getCountries();
	req.setAttribute("allCountries", list);
	String dest = "aashramList.jsp";
	RequestDispatcher disp = req.getRequestDispatcher(dest);
	disp.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {
	doProcess(req, resp);
    }

    private void doProcess(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {
	String sid = req.getParameter("sid");
	if(sid==null || sid.length()==0) {
	    return;
	}
	StringBuffer buff = new StringBuffer();
	ContactUSDAO dao = new ContactUSDAO();
	List<AddressVO> vo = dao.getAddreses(Integer.parseInt(sid));
	buff.append("<tr> <th>ID</th> <th>Address</th><th>Name</th><th>Headed By</th> <th>Phone</th><th>Zip</th></tr>");
	for(int i=0; i<vo.size(); i++) {
	 buff.append(getHtmlRow(vo.get(i)));   
	}	
	resp.getWriter().write(buff.toString());
	resp.getWriter().close();
	return;	
    }
    
    private String getHtmlRow(AddressVO vo) {
	StringBuilder builder = new StringBuilder();
	builder.append("<tr>");
	builder.append("<td>"+removeNull(vo.getAshramID())+"</td>");
	builder.append("<td>"+removeNull(vo.getDescription())+"</td>");
	builder.append("<td>"+removeNull(vo.getName())+"</td>");
	builder.append("<td>"+removeNull(vo.getHeadedBy())+"</td>");
	builder.append("<td>"+removeNull(vo.getPhone())+"</td>");
	builder.append("<td>"+removeNull(vo.getZip())+"</td>");
	builder.append("</tr>");
	return builder.toString();
    }
    
    private String removeNull(String input) {
	return (null==input) ? "" : input;
    }

}
