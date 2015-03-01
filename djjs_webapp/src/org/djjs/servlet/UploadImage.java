/**
 * Aug 10, 2009
 * @author Prateek Jain
 */
package org.djjs.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
import org.djjs.util.MemberUtil;


/**
 * @author pjain
 *
 */
public class UploadImage extends HttpServlet {

	private static final long serialVersionUID = 6726699294604537106L;
	private static final Logger log = Logger.getLogger(UploadImage.class);
    private static final String IMAGE_STORE = System.getenv("image_store");
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
	//boolean isMultipart = ServletFileUpload.isMultipartContent(req);

	FileItemFactory factory = new DiskFileItemFactory();

	// Create a new file upload handler
	ServletFileUpload upload = new ServletFileUpload(factory);
	// file size should not exceed 10MB
	upload.setFileSizeMax(10 * 1024 * 1024);
	String mid = null;
	byte[] data = null;
	try {
	    List<FileItem> items = upload.parseRequest(req);
	    Iterator<FileItem> itr = items.iterator();
	    while (itr.hasNext()) {
		FileItem fit = (FileItem) itr.next();
		String name = fit.getFieldName();
		if(name.equals("mid")) {
		    mid = fit.getString();
		} else if (name.equals("doc")) {
		    data = fit.get();
		}
	    }//while
	    
	} catch (FileUploadException e) {
	    log.error("Failed to upload document");
	    e.printStackTrace();
	}
	boolean flag = storeImage(data, mid);
	MemberDAO da0 = new MemberDAO();
	flag = flag && da0.updateDBForImage(Integer.parseInt(mid));
	RequestDispatcher disp = req.getRequestDispatcher("messageDisplay.jsp");
	String message = "Upload Failed. Please try again.";
	if(flag) {
	    message = "Upload Successfull.";
	}
	req.setAttribute("showMessage", message);
	disp.forward(req, resp);
	
    }
    
    private boolean storeImage(byte[] data, String id) {
	return MemberUtil.getInstance().storeImage(data, id);
    }
    
}

