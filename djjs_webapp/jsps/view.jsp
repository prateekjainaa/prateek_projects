<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="org.djjs.model.MemberVO,java.util.Map,java.util.regex.Matcher,java.util.regex.Pattern;"%><html
	xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
    MemberVO vo = (MemberVO) request.getAttribute("member");
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript">
   function edit() {
        var path = "getUser?mid="+<%= vo.getMemberID()%>+"&edit=true";
        window.location=path;
   }
</script>
<title>Member Info.</title>
</head>


<body>
<table align="right">
<tr>
 <td><a href="logout">logout</a>
 </td>
</tr>

</table>
<table>
	<tr>
		<td><img
			src="/100988798789/members/faces/<%=vo.getMemberID()%>.jpg"
			width="100" height="100" align="left" alt="Member Image N/A" /></td>
		<td>

		<div id="infoDIV">
		<table border="1">
			<tr>
				<td>Member Name</td>
				<td><%=vo.getFirstNameText() + "  " + vo.getLastNameText()%></td>
			</tr>
			<tr>
				<td>Guardian Name</td>
				<td><%= nullRemover(vo.getGaurdianNameText())%></td>
			</tr>
			<tr>
				<td>Date Of Birth</td>
				<td><%=vo.getDateOfBirth()%></td>
			</tr>
			<tr>
				<td>Full Postal Address</td>
				<td><%=vo.getFullAddText()%></td>
			</tr>
			<tr>
				<td>Mobile Number</td>
				<td><%=vo.getPhoneMobileText()%></td>
			</tr>
			<tr>
				<td>Phone Number</td>
				<td><%= nullRemover(vo.getPhoneLandText())%></td>
			</tr>
			<tr>
				<td>Email ID</td>
				<td><%=vo.getEmailText()%></td>
			</tr>

			<tr>
				<td>Sex</td>
				<td><%=vo.getSex()%></td>
			</tr>
			
			<tr>
				<td>Category</td>
				<td><%= getVip(vo.getIsvip())%></td>
			</tr>
			
			<tr>
				<td>Work Category</td>
				<td><%= getWorkCategory(vo.getWorkCategory())%></td>
			</tr>
			
			<tr>
				<td>Sewas</td>
				<td><%= getSewas(vo.getSewaIds())%></td>
			</tr>

			<tr>
				<td>Status</td>
				<td><%= getSwamiStatus(vo.getSelectSwami())%></td>
			</tr>
			<tr>
				<td>Deeksha Date</td>
				<td><%= nullRemover(vo.getDeekshaDate())%></td>
			</tr>
			<tr>
				<td>Deeksha Place</td>
				<td><%= nullRemover(vo.getDeekshaAshramText())%></td>
			</tr>
			<tr>
				<td>Qualification</td>
				<td><%= getQualifications(nullRemover(vo.getQualificationText()))%></td>
			</tr>
			<tr>
				<td>Occupation</td>
				<td><%= nullRemover(vo.getOccupationText())%></td>
			</tr>
			<tr>
				<td>Other Info</td>
				<td><%= nullRemover(vo.getOtherProfText())%></td>
			</tr>
		</table>
		</div>

		</td>

	</tr>
</table>
<br/>
<p align="center">
<font size="5">
<a href="javascript:void(0);"
	onClick="window.print()">Print</a> | <a
	href="javascript:void(0);" onClick="edit()">Edit</a></font></p>
</body>

<%!


  private String getVip(String s) {
    if("1".equals(s)) {
    	return "General";
    } else if("2".equals(s)) {
    	return "Vip";
    } else if("3".equals(s)) {
    	return "V.Vip";
    }  
  	return "unknown";
  }
  
  private String getWorkCategory(String s) {
  	if("1".equals(s)) {
    	return "Corporate";
    } else if("2".equals(s)) {
    	return "Medical";
    } else if("3".equals(s)) {
    	return "Business";
    }  
  	return "Others";
  
  
  }
 
  private String getSwamiStatus(String s) {
    String status = "Premi";
    if(s.equals("1")) {
	    status = "Samarpit";
    } else if(s.equals("3")) {
	    status = "Not Deekshit"; 
     }
      return status;
}

 private String nullRemover(String s) {
     if(null == s || s.length()==0 || s.equals("null")) {
	   s = "";
     }
     return s;
     
 }

private String getSewas(String sewaIds) {
 Map sewaMap = (Map)getServletContext().getAttribute("allSewas");
 String label = "";
 if(null!=sewaIds && sewaIds.length()>0) {
	 String[] sewas = sewaIds.split(",");
	 for(String sewa : sewas) {
	 	int id = Integer.parseInt(sewa.trim());
	 	label += sewaMap.get(id) + ", ";
	 }//for
	 return label.substring(0, label.length()-2);
 } 
 return "";
}

private String getQualifications(String qaIds) {
 Map qaMap = (Map)getServletContext().getAttribute("allQualifications");
 String label = "";
 if(null!=qaIds && qaIds.length()>0 && startsWithDigit(qaIds)) {
	 String[] qas = qaIds.split(",");
	 for(String qa : qas) {
	 	int id = Integer.parseInt(qa.trim());
	 	label += qaMap.get(id) + ", ";
	 }//for
 return label.substring(0, label.length()-2);
 } 
 return "";
}
  
  private boolean startsWithDigit(String input) {
  	Pattern p = Pattern.compile("^\\d");
		Matcher m = p.matcher(input);
		if(m.find()) {
			return true;
		}
  	return false;
  }
%>
</html>