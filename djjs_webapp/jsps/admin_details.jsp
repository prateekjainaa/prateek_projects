

<%@page import="java.util.Iterator"%>
<%@page import="org.djjs.model.State"%>
<%@page import="org.djjs.model.District"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="org.djjs.model.Country"%>
<%@page import="java.util.Set"%>
<%@page import="org.djjs.model.AdminVO"%>

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
	AdminVO vo = (AdminVO) request.getAttribute("adminDetail");

	Set count = vo.getCountries();
	Date cd = vo.getCreatedDate();
	SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");
	String cds = fmt.format(cd);
	String email = vo.getEmail();
	int id = vo.getId();
	String active = vo.getIsActive();
	String name = vo.getName();
	String uName = vo.getUserName();
%>
<table align="center">
	<tr>
		<td>Entered Info.</td>
	</tr>
</table>

<table align="center" border="1">
	<tr>
		<td>ID</td>
		<td>
		<%
			out.print(id);
		%>
		</td>
	</tr>
	<tr>
		<td>Name</td>
		<td><%=name%></td>
	</tr>
	<tr>
		<td>User Name</td>
		<td><%=uName%></td>
	</tr>
	<tr>
		<td>created date</td>
		<td><%=cds%></td>
	</tr>
	<tr>
		<td>Email</td>
		<td><%=email%></td>
	</tr>
	<tr>
		<td>active</td>
		<td><%=active%></td>
	</tr>
</table>
<table align="center">
	<tr>
		<td>
		<%
			if (active.equalsIgnoreCase("N")) {
		%> <a
			href="consoleadminlogin?module=changeActiveState&aid=<%=id%>&status=Y">Enable</a>
		<%
			} else {
		%> <a
			href="consoleadminlogin?module=changeActiveState&aid=<%=id%>&status=N">disable</a>
		<%
			}
		%>
		</td>
		<td><a href="passwd_change.jsp?aid=<%=id%>">change password</a></td>
	</tr>
</table>


<br />
<br />
<table align="center">
	<tr>
		<td>Assigned Areas</td>
	</tr>

</table>


<%
	if (count != null) {
		Iterator itr = count.iterator();
		while (itr.hasNext()) {
			Country c = (Country) itr.next();
			out.print("<p> <font size=5>");
			out.println(c.getName());
			out.print("</font></p>");
%>

<table align="center" border="1">
	<th>State</th>
	<th>District</th>
	<%
		Set sts = c.getStates();
				if (sts != null && sts.size() > 0) {
					Iterator itr1 = sts.iterator();
					while (itr1.hasNext()) {
						out.print("<tr>");
						out.print("<td>");
						State st = (State) itr1.next();
						out.println(st.getName() + "</td>");
						String dist = districtString(st);
						out.print(dist);
						out.println("</tr>");
					}
				}//if
				out.println("</tr></table>");
			}
		}/* while */

	%>

	<%!private String districtString(State st) {
		String d = "<td>";
		Set sd = st.getDistricts();
		if ((sd == null) || (sd.size() == 0)) {
			d += "<font color=\"red\">Nothing assigned</font><br/>";
		} else {

			Iterator itr = sd.iterator();
			while (itr.hasNext()) {
				District dis = (District) itr.next();
				String name = dis.getName();
				d += name + "<br/>";
			}
		}
		d += "</td>";

		return d;
	}%>

</table>
<br />

<%
	Cookie[] cook = request.getCookies();
	String adminType = "false";
	for (int i = 0; i < cook.length; i++) {
		if (cook[i].getName().equals("is_super_user")) {
			adminType = cook[i].getValue();
		}
	}
	
%>

<a href="assignAreas?module=loadcountries&id=<%=id%>">Assign Areas</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="assignAreas?module=getAssignedAreas&aid=<%=id%>">Remove
Areas</a>


</body>
</html>
