
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript">

</script>
</head>
<body>
 <font size="5" color="blue"><%= request.getAttribute("showMessage")%></font>
 <%
	Cookie[] cook = request.getCookies();
	String adminType = "false";
	for (int i = 0; i < cook.length; i++) {
		if (cook[i].getName().equals("is_super_user")) {
			adminType = cook[i].getValue();
		}
	}
	
%>
<%
if(adminType.equalsIgnoreCase("true")) {
%>
<a href="/djjs_webapp/super_welcome.jsp">Home</a>
<%
} else if(adminType.equalsIgnoreCase("false")) { 
%>    
    <a href="/djjs_webapp/welcome_admin.jsp">Home</a>
<%}%>
</body>
</html>