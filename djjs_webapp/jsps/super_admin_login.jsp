<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<script type="text/javascript">
	function login() {
		document.getElementById("module").value="adminLogin";
		document.forms[0].submit();
	}

	function reset() {
		document.forms[0].reset();
	}

	function validate() {
		var name=document.getElementById("username").value;
		var pass=document.getElementById("passwd").value;
		if(name.length == 0) {
			alert("username should be present");
			return;
		}
		if(pass.length == 0) {
			alert("password should be present");
			return;
		}
			login();
	} 
	
</script>
<title>Admin Console</title>
</head>


<body>

<form action="consoleadminlogin" method="post">
<table align="center">
 <tr>
  <td>
<label>user name</label>  
  </td>
  <td>
  <input type="text" name="name" id="username"/>
  </td>
 </tr>
<tr>
  <td>
<label>password</label>  
  </td>
  <td>
  <input type="password"name="pass" id="passwd"/>
  </td>
 </tr>
 <tr>
  <td>
 <input type="hidden" name="module" value="" id="module"/>
  </td>
  <td>
	<a href="javascript:void(0);" onClick="validate()">login</a> &nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onClick="reset()">reset</a>
  </td>
 </tr>
</table>

</form>
</body>
</html>