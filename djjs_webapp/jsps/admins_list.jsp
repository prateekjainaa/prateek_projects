<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.djjs.model.AdminVO"%><html
	xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>

<body>

<%
	List lst = (List) request.getAttribute("listAdmins");
%>
<table align="right">
<tr>
 <td><a href="logout">logout</a>
 </td>
</tr>

</table>
<table align="center" border="1">
	<tr>
		<th>Name</th>
		<th>User Name</th>
		<th>Email</th>
		<th>Contact Number</th>
		<th>Address</th>
		<th>Details</th>
	</tr>
	<%
		Iterator itr = lst.iterator();
		while (itr.hasNext()) {
			AdminVO vo = (AdminVO) itr.next();
			String href= "<a href=\"consoleadminlogin?module=adminDetail&aid="+vo.getId()+"\">click here</a>";
			String html = "<tr><td>"+nullRemover(vo.getName())+"</td><td>"+nullRemover(vo.getUserName())+"</td><td>"+nullRemover(vo.getEmail())+"</td><td>"+nullRemover(vo.getContactNumber())+"</td><td>"+nullRemover(vo.getAddress())+"</td><td>"+ href+"</td><tr>";
			out.println(html);
	%>
	
		
	<%
		}// while
	%>
	
</table>
<%! private String nullRemover(String s) {
	String output = "";
	if((null != s) && (s.length()>0)){
		output = s;
	}
	return output;
}
%>

</body>

</html>

