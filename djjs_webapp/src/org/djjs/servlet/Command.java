/**
 * Aug 15, 2009
 * @author Prateek Jain
 */
package org.djjs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
/**
 * @author pjain
 *
 */
public class Command extends HttpServlet {
    
    private static final Logger log = Logger.getLogger(Command.class);
    
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
	
	String command = req.getParameter("command");
	String target = req.getParameter("target");
	String text = req.getParameter("text");
	String d = URLDecoder.decode(text, "UTF-8");
	log.debug("sending sms to members "+ target);
	log.debug("sms: "+ d);
	PrintWriter pw = resp.getWriter();
	pw.write("command="+command+" target="+target + "text=" + text);
	
    }
    
    

}
