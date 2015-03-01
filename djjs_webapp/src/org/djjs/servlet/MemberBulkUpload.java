package org.djjs.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.djjs.dao.MemberDAO;
import org.djjs.model.AdminVO;
import org.djjs.model.NewMemberVO;
import org.djjs.util.DBUtil;
import org.djjs.util.DJJSCSVParser;
import org.djjs.util.MemberUtil;

public class MemberBulkUpload extends HttpServlet {

	private static final long serialVersionUID = 8207749003731325575L;

	private volatile boolean IN_PROCESS = false;

	private static final Logger log = Logger.getLogger(MemberBulkUpload.class);
	private String IMAGE_STORE;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		IMAGE_STORE = System.getenv("image_store");
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Object adminType = req.getSession().getAttribute("is_super_user");
		if (null != adminType && adminType.toString().equals("false")) {
			RequestDispatcher dis = req
					.getRequestDispatcher("member_csv_upload.jsp");
			dis.forward(req, resp);
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doProcess(req, resp);
	}

	private synchronized void updateFlag() {
		IN_PROCESS = (IN_PROCESS) ? false : true;
	}

	private void doProcess(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String message = "";
		if (IN_PROCESS) {
			 message = "<h2>Another upload in progress, please try after some time</h2>";
			redirectToDisplayMessage(req, resp, message);
			return;
		}
		updateFlag();
		FileItemFactory factory = new DiskFileItemFactory();
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// file size should not exceed 10MB
		upload.setFileSizeMax(10 * 1024 * 1024);
		byte[] data = null;
		try {
			List<FileItem> items = upload.parseRequest(req);
			Iterator<FileItem> itr = items.iterator();
			while (itr.hasNext()) {
				FileItem fit = (FileItem) itr.next();
				String name = fit.getFieldName();
				if (name.equals("doc")) {
					data = fit.get();
				}
			}// while

		} catch (FileUploadException e) {
			log.error("Failed to upload csv document");
			e.printStackTrace();
			return;
		} finally {
			updateFlag();
		}
		AdminVO vo = (AdminVO) req.getSession().getAttribute("Admin");
		String adminIdWithTimestamp = vo.getId() + "_" + new Date().getTime();
		boolean flag = MemberUtil.getInstance().storeCSV(data,
				adminIdWithTimestamp);
		List<NewMemberVO> list = new ArrayList<NewMemberVO>();
		String errorMsg = DJJSCSVParser.parseCSV(new File(IMAGE_STORE + "/"
				+ adminIdWithTimestamp + ".csv"), list);
		if (0 < errorMsg.length()) {
			updateFlag();
			redirectToDisplayMessage(req, resp, errorMsg);
			return;
		}
		errorMsg = persist(list);
		updateFlag();
		if(0 < errorMsg.length()) {
			redirectToDisplayMessage(req, resp, errorMsg);
			return;
		}
		// releasing resources		
		redirectToDisplayMessage(req, resp, "Upload processing successful");
	}
	
	private String persist(List<NewMemberVO> list) {
		if(list==null || list.size()==0) {
			return "No Member info. found";
		}
		StringBuilder msg = new StringBuilder("<table border=\"1\">");
		Connection con = DBUtil.getConnection();
		for(NewMemberVO vo : list) {
				setCorrectValues(vo);				
				//----
				MemberDAO dao = new MemberDAO();
				int mid=0;								
				try {
				    mid = dao.storeMember(vo, con);
				} catch (RuntimeException e) {
				    msg.append("<td>Failed to add member. Please try again or contact administrator</td>");
				    e.printStackTrace();
				    return msg.toString();
				}
				vo.setMemberID(Integer.toString(mid));
				msg.append("<td>Edit "+vo.getFirstNameText() + " <a href=\"getUser?mid="+mid +"\">click</a> </td>");
				dao.storeMemberAddress(vo, con);
				dao.storeQualification(vo, con);
				if(!vo.getSelectSwami().equals("3")) {
				log.info("Member has taken deeksha");
				dao.storeDeekshaDetails(vo, con);
				}
								
				//---
		}
		
		DBUtil.commitDB(con);
		DBUtil.closeConnection(con);
		msg.append("</table>");
		return msg.toString();
	}
	
	private void setCorrectValues(NewMemberVO vo) {
		// 1. set vip
		String vip = vo.getIsvip();
		/*if("3".equals(vip)) {
			vo.setIsvip("N");
		} else {
			vo.setIsvip("Y");
		}*/
		String wc = vo.getWorkCategory();
		if(!StringUtils.isNumeric(wc)) {
			vo.setWorkCategory("4");
		}
		
	}
	

	private void redirectToDisplayMessage(HttpServletRequest req,
			HttpServletResponse resp, String mesage) throws ServletException,
			IOException {
		RequestDispatcher disp = req.getRequestDispatcher("messageDisplay.jsp");
		req.setAttribute("showMessage", mesage);
		disp.forward(req, resp);
	}

}
