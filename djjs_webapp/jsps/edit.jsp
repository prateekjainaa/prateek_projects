<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="org.djjs.model.MemberVO, java.util.*;"%><html
	xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
    MemberVO vo = (MemberVO) request.getAttribute("member");
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="/jmjk/editUSER/calendar_us.js"></script>
<link href="/jmjk/editUSER/calendar.css" rel="stylesheet" type="text/css" />

<script src='/jmjk/ext-core.js' type='text/javascript'></script>
<script type="text/javascript" src="/jmjk/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="/jmjk/ext-all-debug.js"></script>

<script type="text/javascript">
	function editProfile() {
		setsewaSelectionName();
		setQASelectionName();
		document.forms[0].submit();
	}
	
	function setsewaSelectionName() {
	  var obj = document.getElementById("sewaSelectionCombo");
	  var opts = obj.options;
	  var sewaSelection = "";
	  for (i=0; i<opts.length; i++)   {
   		 if (opts[i].selected == true) { 
   		 sewaSelection += opts[i].value+","; 
   		 }//if
   		}//for
   	  sewaSelection = sewaSelection.substring(0, sewaSelection.length - 1);
   	  document.getElementById("sewaSelectionName").value = sewaSelection;
   	  			
	}
	
	function setQASelectionName() {
		  var obj1 = document.getElementById("qaSelectionCombo");
		  var opts1 = obj1.options;
		  var qaSelection = "";
		  for (i=0; i<opts1.length; i++)   {
	   		 if (opts1[i].selected == true) { 
	   		 qaSelection += opts1[i].value+","; 
	   		 }//if
	   		}//for
	   	  qaSelection = qaSelection.substring(0, qaSelection.length - 1);
	   	  document.getElementById("qualificationText").value = qaSelection;
	   	  //alert(document.getElementById("qualificationText"));
	   	  			
	}
	
</script>
<title>Edit Member Info.</title>
</head>


<body>
    <p align="center"> <font size="5" color="blue">Edit User</p>
    <table align="right">
<tr>
 <td><a href="logout">logout</a>
 </td>
</tr>

</table>
<table align="center">
	<tr>
		<td><img
			src="/100988798789/members/faces/<%=vo.getMemberID()%>.jpg"
			width="100" height="100" align="left" alt="Member Image N/A" />
       <br/>
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onClick="editImage()">change</a> 
        </td>
		<td>

		<div id="infoDIV">
		<form action="editUser?mid=<%=vo.getMemberID()%>" name="editUserForm" method="post">
		<table border="0">
			<tr>
				<td>First Name</td>
				<td><input type="text" name="firstNameText"
					value="<%=vo.getFirstNameText()%>" /></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><input type="text" name="lastNameText"
					value="<%=vo.getLastNameText()%>" /></td>
			</tr>

			<tr>
				<td>Guardian Name</td>
				<td><input type="text" name="gaurdianNameText"
					value="<%=vo.getGaurdianNameText()%>" /></td>
			</tr>
			<tr>
				<td>Date Of Birth</td>
				<td><input type="text" name="dateOfBirth" value="<%= vo.getDateOfBirth() %>"/> 
<script language="JavaScript">
	new tcal( {
		// form name
		'formname' :'editUserForm',
		// input name
		'controlname' :'dateOfBirth'
	});
</script></td>
			</tr>
			
			<tr>
				<td><font color="red">Country</font></td>
				<td><select id="countryCodes" name="countryCodes" size="1" onclick="getStates()">
					<%= request.getAttribute("countries") %>
					</select>
				</td>
			</tr>
						
			<tr>
				<td><font color="red">State</font></td>
				<td><select id="stateCodes" name="stateCodes" size="1" onclick="getDisctricts()">
					<%= request.getAttribute("stateCodes") %>
					</select>
				</td>
			</tr>
			
			<tr>
				<td><font color="red">District</font></td>
				<td><select id="districtCodes" name="districtCodes" size="1">
					<%= request.getAttribute("districtCodes") %>
					</select>
				</td>
			</tr>
			<tr>
				<td>Town/Vilage</td>
				<td><input type="text" name="townNameText" value="<%=nullRemover(vo.getTownNameText())%>"/></td>
			</tr>
			<tr>
				<td>Full Postal Address</td>
				<td><input type="text" name="fullAddText" value="<%=nullRemover(vo.getFullAddText())%>"/></td>
			</tr>
			<tr>
				<td>Mobile Number</td>
				<td><input type="text" name="phoneMobileText" value="<%=nullRemover(vo.getPhoneMobileText())%>"/></td>
			</tr>
			<tr>
				<td>Phone Number</td>
				<td><input type="text" name="phoneLandText" value="<%=nullRemover(vo.getPhoneLandText())%>"/></td>
			</tr>
			<tr>
				<td>Email ID</td>
				<td><input type="text" name="emailText" value="<%=vo.getEmailText()%>"/></td>
			</tr>
			<tr>
				<td>Related To Ashram(Enter ashram id)</td>
				<td><input type="text" name="relatedToText" value="<%=vo.getRelatedToText()%>"/></td>
			</tr>
 
			<tr>
				<td>Sex</td>
				<td>
				<%String sex = vo.getSex(); %>
				<input type="radio" name="sex" value="1" <%if(sex.equals("M")){ %>checked <%} %>>Male
				<input type="radio" name="sex" value="2" <%if(sex.equals("F")){ %>checked <%} %>>Female
                </td>
			</tr>

			<tr>
				<td>Category</td>
				<td>
				<%String vip = vo.getIsvip(); %>
				<input type="radio" name="sam" value="1" <%if(vip.equals("1")){ %>checked <%} %>>General
				<input type="radio" name="sam" value="2" <%if(vip.equals("2")){ %>checked <%} %>>Vip
				<input type="radio" name="sam" value="3" <%if(vip.equals("3")){ %>checked <%} %>>V.Vip
                </td>
			</tr>
			
			<tr>
				<td>Work Category</td>
				<td>
				<%String wc = vo.getWorkCategory(); %>
				<input type="radio" name="sam2" value="1" <%if(wc.equals("1")){ %>checked <%} %>>Corporate
				<input type="radio" name="sam2" value="2" <%if(wc.equals("2")){ %>checked <%} %>>Medical
				<input type="radio" name="sam2" value="3" <%if(wc.equals("3")){ %>checked <%} %>>Business
				<input type="radio" name="sam2" value="4" <%if(wc.equals("4")){ %>checked <%} %>>Others
                </td>
			</tr>


			<tr>
				<td>Status</td>
				<td>
				<% String ss = vo.getSelectSwami();
				%>
				<input type="radio" name="swami" value="1" <% if(ss.equals("1")) { %> checked <%} %> > Smarpit
				<input type="radio" name="swami" value="2" <% if(ss.equals("2")) { %> checked <%} %>> Premi
				<input type="radio" name="swami" value="3" <% if(ss.equals("3")) { %> checked <%} %>> Not Deekshit
				</td>
			</tr>
			<tr>
				<td>Deeksha Date</td>
				<td><input type="text" name="deekshaDate" value="<%= nullRemover(vo.getDeekshaDate())%>"/>
<script language="JavaScript">
	new tcal( {
		// form name
		'formname' :'editUserForm',
		// input name
		'controlname' :'deekshaDate'
	});
</script>
           </td>
			</tr>
			<tr>
				<td>Deeksha Place</td>
				<td><input type="text" name="deekshaAshramText" value="<%= nullRemover(vo.getDeekshaAshramText())%>"/></td>
			</tr>
			<tr>
				<td>Qualification</td>
				<td>
				<select name="qaSelectionCombo" id="qaSelectionCombo" size="7" multiple>
				 <%= getAllSelectedQualifications(request)%>
				</select>
				
				<input type="hidden" name="qualificationText" id="qualificationText" value=""/>
				</td>
			</tr>
			<tr>
				<td>Occupation</td>
				<td><input type="text" name="occupationText" value="<%= nullRemover(vo.getOccupationText())%>"/></td>
			</tr>
			
			<tr>
				<td>Sewas</td>
				<td><select name="sewaSelectionCombo" id="sewaSelectionCombo" size="7" multiple>
				 <%= getAllSewas(request)%>
				</select>
				<input type="hidden" name="sewaSelectionName" id="sewaSelectionName" value=""/>
				</td>
			</tr>
			
			<tr>
				<td>Other Info.</td>
				<td><input type="text" name="otherProfText" value="<%=nullRemover(vo.getOtherProfText())%>"/></td>
			</tr>
		</table>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onClick="editProfile()">update</a></form>
		</div>

		</td>

	</tr>
</table>
<br />

</body>

<%!	
private String nullRemover(String s) {
    if(null == s || s.length()==0 || s.equals("null")) {
	   s = "";
    }
    return s;
    
}



private String getAllSelectedQualifications(HttpServletRequest request) {
	Map qaMap = (Map)getServletContext().getAttribute("allQualifications");
	Iterator it = qaMap.entrySet().iterator();
	
	String[] qaIds = {""};
	
	String qaText = ((MemberVO)request.getAttribute("member")).getQualificationText();
	if(qaText!=null && qaText.length()>0) {
	 qaIds = qaText.split(",");
	}
	String option = "";
	while (it.hasNext()) {
		Map.Entry pairs = (Map.Entry)it.next();
		if(isQASelected(qaIds, (Integer)pairs.getKey())) {
		option += "<option value=\""+pairs.getKey()+" \" selected>" + pairs.getValue() +" </option>";
		} else {
		option += "<option value=\""+pairs.getKey()+"\">" + pairs.getValue()+" </option>";
	     }
	    }//while
	    return option;
	}


private boolean isQASelected(String[] selected, Integer id) {
 for(String s : selected) {
	if(id.toString().equals(s.trim())) {
		return true;
	}
 }
 return false;
}


private String getAllSewas(HttpServletRequest request) {
	Map sewaMap = (Map)getServletContext().getAttribute("allSewas");
	Iterator it = sewaMap.entrySet().iterator();
	String[] sewaIds = {""}; 
	String ss = ((MemberVO)request.getAttribute("member")).getSewaIds();
	if(null!=ss && ss.length()>0) {
	sewaIds = ss.split(",");
	}
	String option = "";
	while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        if(isSewaSelected(sewaIds, (Integer)pairs.getKey())) {
	        option += "<option value=\""+pairs.getKey()+" \" selected>" + pairs.getValue() +" </option>";
	        } else {
	        option += "<option value=\""+pairs.getKey()+"\">" + pairs.getValue()+" </option>";
	     }
	    }//while
	    return option;
}


private boolean isSewaSelected(String[] selected, Integer id) {
 for(String s : selected) {
	if(id.toString().equals(s.trim())) {
		return true;
	}
 }
 return false;
}

%>

<script type="text/javascript">
 function editImage() {
   window.open("upload.jsp?mid=<%= vo.getMemberID()%>","Upoad Doc.");

 }

 function getStates() {
		var index = document.getElementById('countryCodes').selectedIndex;
		var cid = document.getElementById('countryCodes')[index].value;
		//alert('cid='+cid);
		var conn = new Ext.data.Connection();
		conn.request({
		    url: 'getUser?cid='+cid,
		    method: 'GET',
		    success: function(responseObject) {
		        var ss = responseObject.responseText;
		        var objDropdown = document.getElementById('stateCodes');
		        var arr1 = ss.split(',');
		        objDropdown.length=0;		        
		        for(i=0;i<arr1.length;i++) {
		        	var state = arr1[i];
		        	var pos = state.indexOf(':');
		        	var name = state.substring(0,pos);
		        	var val = state.substring(pos+1, state.length);
		        	var op = new Option(name , val);
				    objDropdown.options[objDropdown.length] = op;	
		        	
		        }//for
		        
		    },
		     failure: function() {
		         Ext.Msg.alert('Status', 'Please try again later.');
		     }
		});	
   } 

   function getDisctricts() {
	   var index = document.getElementById('stateCodes').selectedIndex;
		var sid = document.getElementById('stateCodes')[index].value;
		//alert(sid);	   
		var conn = new Ext.data.Connection();
				conn.request({
				    url: 'getUser?sid='+sid,
				    method: 'GET',
				    success: function(responseObject) {
				        var ss = responseObject.responseText;
				        var objDropdown = document.getElementById('districtCodes');
				        var arr1 = ss.split(',');
				        objDropdown.length=0;		        
				        for(i=0;i<arr1.length;i++) {
				        	var state = arr1[i];
				        	var pos = state.indexOf(':');
				        	var name = state.substring(0,pos);
				        	var val = state.substring(pos+1, state.length);
				        	var op = new Option(name , val);
						    objDropdown.options[objDropdown.length] = op;	
				        	
				        }//for
				        
				    },
				     failure: function() {
				         alert('Failed to get Info.');
				     }
		});	
		
		
		
   }
 
</script>

</html>