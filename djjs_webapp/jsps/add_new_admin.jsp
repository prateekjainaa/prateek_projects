<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html
	xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript">
	function addAdmin() {
		document.getElementById("module").value="addUser";
		document.forms[0].submit();
	}

	function reset() {
		document.forms[0].reset();
	}

	function validate() {
		var uname=document.getElementById("username").value;
		var pass=document.getElementById("passwd").value;
		var pass2=document.getElementById("passwd2").value;
		var name=document.getElementById("name").value;
		var email=document.getElementById("email").value;
		
		if(uname.length == 0) {
			alert("username should be present");
			return;
		}
		if(pass.length == 0) {
			alert("password should be present");
			return;
		}
		if(name.length == 0) {
			alert("name should be present");
			return;
		}
		if(email.length == 0) {
			alert("email should be present");
			return;
		}

		if(pass == pass2) {
			addAdmin();			
		} else {
			alert("passwords don't match");
			return;
		}
			
	} 
	
</script>
</head>

<body>
<table align="right">
<tr>
 <td><a href="logout">logout</a>
 </td>
</tr>

</table>
<% if(null == session) {
	out.println("<h3>Invalid session</h3>");
	return;
}
	%>
<form action="consoleadminlogin" method="post">
<table align="center">
 <tr>
  <td>
<label>user name*</label>  
  </td>
  <td>
  <input type="text" name="userName" id="username"/>
  </td>
 </tr>
<tr>
  <td>
<label>password*</label>  
  </td>
  <td>
  <input type="password" name="pass1" id="passwd"/>
  </td>
 </tr>
 <tr>
  <td>
<label>re-type password*</label>  
  </td>
  <td>
  <input type="password" name="pass2" id="passwd2"/>
  </td>
 </tr>
 <tr>
 <td>
<label>Name*</label>  
  </td>
  <td>
 <input type="text" name="name" value="" id="name"/>
  </td>
</tr>
 <tr>
 <td>
<label>email*</label>  
  </td>
  <td>
 <input type="text" name="email" value="" id="email"/>
  </td>
</tr>
<tr>
  <td>
 <input type="hidden" name="module" value="" id="module"/>
  </td>
  <td>
	<a href="javascript:void(0);" onClick="validate()">Add</a> &nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onClick="reset()">reset</a>
  </td>
 </tr>

</table>
</form>
</body>
</html>