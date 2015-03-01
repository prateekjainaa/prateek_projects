/**
 * Jul 4, 2009
 * @author Prateek Jain
 */
package org.djjs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.djjs.dao.MemberDAO;
import org.djjs.model.MemberVO;
import org.djjs.util.DBUtil;
import org.djjs.util.MemberUtil;

public class AddUser extends HttpServlet {

    private static final long serialVersionUID = 4510670359799512226L;
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
	boolean isMultipart = ServletFileUpload.isMultipartContent(req);

	FileItemFactory factory = new DiskFileItemFactory();

	// Create a new file upload handler
	ServletFileUpload upload = new ServletFileUpload(factory);
	// file size should not exceed 10MB
	upload.setFileSizeMax(10 * 1024 * 1024);
	// Parse the request
	MemberVO vo = new MemberVO();
	try {
	    List<FileItem> items = upload.parseRequest(req);
	    Iterator<FileItem> itr = items.iterator();
	    while (itr.hasNext()) {
		FileItem fit = (FileItem) itr.next();
		vo = MemberUtil.getInstance().extractFieldsFromForm(vo, fit);
	    }
	} catch (FileUploadException e) {
	    log.error("Failed to upload document");
	    e.printStackTrace();
	}
	MemberDAO dao = new MemberDAO();
	int mid=0;
	PrintWriter out = resp.getWriter();
	Connection con = DBUtil.getConnection();
	
	try {
	    mid = dao.storeMember(vo, con);
	} catch (RuntimeException e) {
	    out.write("Failed to add member. Please try again or contact administrator");
	    e.printStackTrace();
	    return;
	}
	vo.setMemberID(Integer.toString(mid));
	boolean imageStatus = MemberUtil.getInstance().storeImage(vo.getImagePath(), vo.getMemberID());
	dao.storeMemberAddress(vo, con);
	dao.storeQualification(vo, con);
	if(!vo.getSelectSwami().equals("3")) {
	log.info("Member has taken deeksha");
	dao.storeDeekshaDetails(vo, con);
	}
	DBUtil.commitDB(con);
	if(mid>0) {
	out.write("Member successfully added with ID "+ vo.getMemberID() );
	} else {
	    out.write("Failed to add member. Please try again or contact administrator");
	}	
	
    }

	

}
