
<%@page import="org.djjs.model.SuperUserVO"%><html>
<% SuperUserVO vo = (SuperUserVO)session.getAttribute("sp"); %>
<head>
<script type="text/javascript">
function listAdmins(){
	var mypara=document.getElementById("myhiddenForm");
	var module=document.getElementById("module");
	module.value="listUsers";
	mypara.submit();
}

function swapAdmin(){
	var mypara=document.getElementById("myhiddenForm");
	var module=document.getElementById("module");
	module.value="listAdmins";
	mypara.submit();	
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

Welcome! <%= vo.getName() %>

<table align="center" border="1"> 
<tr>
  <td> ID: </td>
  <td><%= vo.getId() %>
</tr>
<tr>
  <td> user-name</td>
  <td><%= vo.getName() %>
</tr>
<tr>
  <td> email</td>
  <td><%= vo.getEmail() %>
</tr>

</table>

<table align="center">
<tr> 
<td> 
<a href="javascript:void(0);" onClick="listAdmins()"> Admins</a>&nbsp;&nbsp;&nbsp;
</td>
<td>
<a href="add_new_admin.jsp"> Add Admin</a>&nbsp;&nbsp;&nbsp;
</td>
<td>
<a href="javascript:void(0);" onClick="swapAdmin()"> Swap Admins</a>
</td>
</tr>
</table>

<form id="myhiddenForm" action="consoleadminlogin" method="post">
<input type="hidden" name="module" id="module"/>
</form>

</body>

</html>
