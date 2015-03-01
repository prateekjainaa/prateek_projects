/**
 * Aug 8, 2009
 * @author Prateek Jain
 */
package org.djjs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.djjs.dao.ContactUSDAO;
import org.djjs.dao.MemberDAO;
import org.djjs.model.Container;
import org.djjs.model.MemberVO;
import org.djjs.util.MemberUtil;

/**
/**
 * @author pjain
 *
 */
public class GetUser extends HttpServlet {

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doProcess(req, resp);
    }
    
    
    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
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
	if(null != cid && cid.length()>0) {
	 PrintWriter pw = resp.getWriter();
	 ContactUSDAO dao = new ContactUSDAO();
	 List<Container> lst = dao.getStates(Integer.parseInt(cid));
	 StringBuilder bld = new StringBuilder();
	 for(Container cnt : lst) {
	     bld.append(cnt.getName()+":"+cnt.getValue()+",");
	 }
	 bld.delete(bld.length()-1, bld.length());
	 pw.write(bld.toString());
	 pw.close();  
	 return;
	}	
	
	if(null != sid && sid.length()>0) {
		 PrintWriter pw = resp.getWriter();
		 ContactUSDAO dao = new ContactUSDAO();
		 List<Container> lst = dao.getDistricts(Integer.parseInt(sid));
		 StringBuilder bld = new StringBuilder();
		 for(Container cnt : lst) {
		     bld.append(cnt.getName()+":"+cnt.getValue()+",");
		 }
		 bld.delete(bld.length()-1, bld.length());
		 pw.write(bld.toString());
		 pw.close();  
		 return;
		}	
	
	String memberID = req.getParameter("mid");
	String editReq = req.getParameter("edit");
	MemberDAO dao = new MemberDAO();
	MemberVO vo = dao.getUserData(Integer.parseInt(memberID));
	MemberUtil.getInstance().copyImageForDisplay(vo.getImagePath(), vo.getMemberID());
	vo.setImagePath(null);
	vo = dao.getAddress(vo);
	vo = dao.getDeekshaDetails(vo);
	vo = dao.getQualification(vo);
		
	ContactUSDAO daoc = new ContactUSDAO();
	List<Container> lst1 = daoc.getCountries();
	StringBuilder b = new StringBuilder("");
	for(Container cont : lst1) {
	    if(vo.getCountryCodes().equals(cont.getValue())) {
		b.append("<option value=\""+cont.getValue()+"\" SELECTED >"+cont.getName()+"</option>");
	    } else {
	    b.append("<option value=\""+cont.getValue()+"\">"+cont.getName()+"</option>");
	    }
	}
	List<Container> lst2 = daoc.getStates(Integer.parseInt(vo.getCountryCodes()));
	StringBuilder c = new StringBuilder("");
	for(Container cont2 : lst2) {
	    if(vo.getStateCodes().equals(cont2.getValue())) {
		c.append("<option value=\""+cont2.getValue()+"\" SELECTED >"+cont2.getName()+"</option>");
	    } else {
	    c.append("<option value=\""+cont2.getValue()+"\">"+cont2.getName()+"</option>");
	    }
	}
	List<Container> lst3 = daoc.getDistricts(Integer.parseInt(vo.getStateCodes()));
	StringBuilder d = new StringBuilder("");
	for(Container cont3 : lst3) {
	    if(vo.getDistrictCodes().equals(cont3.getValue())) {
		d.append("<option value=\""+cont3.getValue()+"\" SELECTED >"+cont3.getName()+"</option>");
	    } else {
	    d.append("<option value=\""+cont3.getValue()+"\">"+cont3.getName()+"</option>");
	    }
	}	
	req.setAttribute("countries", b.toString());
	req.setAttribute("stateCodes", c.toString());
	req.setAttribute("districtCodes", d.toString());
	req.setAttribute("member", vo);
	String redirectPath = "/edit.jsp";
	if((null == editReq) || (editReq.length()==0)) {
	    redirectPath = "/view.jsp";
	}
	RequestDispatcher disp = req.getRequestDispatcher(redirectPath);
	disp.forward(req, resp);
	
    }   
    
}

