<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript">


</script>
<body>
<table align="right">
<tr>
 <td><a href="logout">logout</a>
 </td>
</tr>

</table>

<%
	String adminType = request.getSession().getAttribute("is_super_user").toString();
	
%>
<p/>
<p/>
<p/>
<p/>
<%
	if (adminType.equals("false")) {
		out.print("<a href=\"addMember.jsp\">Add User</a>");
		out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"memberBulkUpload\">csv upload</a>");
		out.println("&nbsp;&nbsp;<a href=\"sample_upload.csv\">(sample csv)</a>");
	}
%>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/searchuser.html">Search</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="javascript:window.open('ashrams')">Aashram List</a>


<br/><br/><br/><br/>Reference Data<br/>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="countries.csv">country ids</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="states.csv">state ids</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="districts.csv">district ids</a>


<table border="1">

<tr><td>Field name </td> <td>value</td></tr>
<tr><td> Samarpit</td> <td>1</td></tr>
<tr><td> Premi </td> <td>2</td></tr>
<tr><td>Not Deekshit </td> <td>3</td></tr>
</table>
<br/><br/>
<table border="1">

<tr><td>Field name </td> <td>value</td></tr>
<tr><td> General</td> <td>1</td></tr>
<tr><td> Vip </td> <td>2</td></tr>
<tr><td> V.Vip </td> <td>3</td></tr>
</table>



</body>
</html>