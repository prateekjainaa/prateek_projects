<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript">

function submit0() {
	var tt = document.getElementById("swap_admin");
	document.getElementById("module").value="swapAdmins";
	tt.submit();
}

function reset0() {
	var tt = document.getElementById("swap_admin");
	tt.reset();
}

</script>
<body>
<%
String s = (String)request.getAttribute("swap_admin");

%>
<form id="swap_admin" action="consoleadminlogin" method="post">
<table align="center"> 
<tr>
<td>
<select id="admin1" name="admin1">
<%= s%>
</select>
</td>
<td>
<select id="admin2" name="admin2">
<%= s%>
</select>
</td>

</tr>
<tr>
<td colspan="1">
&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="submit0()">submit</a>
</td>
<td colspan="1">
&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="reset0()">reset</a>
</td>
</tr>
</table>
<input type="hidden" name="module" id="module"/></form>
</body>
</html>