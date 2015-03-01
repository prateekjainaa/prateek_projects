<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="org.djjs.model.*, java.util.*;"%><html
	xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
   AdminVO vo = (AdminVO)session.getAttribute("Admin");
   Set<Country> cs = vo.getCountries();
	String countryOption = "";
	for(Country c1 : cs) {
		int id = c1.getId();
		String name = c1.getName();
		countryOption += "<option value=\""+id+"\">"+name+"</option>";
	}
   
   
   
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
   	  //alert('sewa ' + sewaSelection);
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
<title>Add Member Info.</title>
</head>


<body>
    <p align="center"> <font size="5" color="blue">Add User</p>
    <table align="right">
<tr>
 <td><a href="logout">logout</a>
 </td>
</tr>
<%
 
%>
</table>
<table align="center">
	<tr>
		<td>

		<div id="infoDIV">
		<form action="addUser" name="editUserForm" method="post" enctype="multipart/form-data">
		<table border="0">
			<tr>
				<td>First Name</td>
				<td><input type="text" name="firstNameText"
					value="" /></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><input type="text" name="lastNameText"
					value="" /></td>
			</tr>

			<tr>
				<td>Guardian Name</td>
				<td><input type="text" name="gaurdianNameText"
					value="" /></td>
			</tr>
			<tr>
				<td>Date Of Birth</td>
				<td><input type="text" name="dateOfBirth" value=""/> 
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
				<td><select id="country_hidden_name" name="country_hidden_name" size="1" onclick="getStates()">
					<%= countryOption %>
					</select>
				</td>
			</tr>
						
			<tr>
				<td><font color="red">State</font></td>
				<td><select id="state_hidden_name" name="state_hidden_name" size="1" onclick="getDisctricts()">
					<%= request.getAttribute("stateCodes") %>
					</select>
				</td>
			</tr>
			
			<tr>
				<td><font color="red">District</font></td>
				<td><select id="district_hidden_name" name="district_hidden_name" size="1">
					<%= request.getAttribute("districtCodes") %>
					</select>
				</td>
			</tr>
			<tr>
				<td>Town/Vilage</td>
				<td><input type="text" name="townNameText" value=""/></td>
			</tr>
			<tr>
				<td>Full Postal Address</td>
				<td><input type="text" name="fullAddText" value=""/></td>
			</tr>
			<tr>
				<td>Mobile Number</td>
				<td><input type="text" name="phoneMobileText" value=""/></td>
			</tr>
			<tr>
				<td>Phone Number</td>
				<td><input type="text" name="phoneLandText" value=""/></td>
			</tr>
			<tr>
				<td>Email ID</td>
				<td><input type="text" name="emailText" value=""/></td>
			</tr>
			<tr>
				<td>Related To Ashram(Enter ashram id)</td>
				<td><input type="text" name="relatedToText" value=""/></td>
			</tr>
 
			<tr>
				<td>Sex</td>
				<td>
				
				<input type="radio" name="sex" value="1" checked >Male
				<input type="radio" name="sex" value="2" >Female
                </td>
			</tr>

			<tr>
				<td>Category</td>
				<td>
				
				<input type="radio" name="sam" value="1" checked>General
				<input type="radio" name="sam" value="2">Vip
				<input type="radio" name="sam" value="3">V.Vip
                </td>
			</tr>
			
			<tr>
				<td>Work Category</td>
				<td>
				
				<input type="radio" name="sam2" value="1" checked>Corporate
				<input type="radio" name="sam2" value="2" >Medical
				<input type="radio" name="sam2" value="3" >Business
				<input type="radio" name="sam2" value="4" >Others
                </td>
			</tr>


			<tr>
				<td>Status</td>
				<td>
				
				<input type="radio" name="swami" value="1"  > Smarpit
				<input type="radio" name="swami" value="2" > Premi
				<input type="radio" name="swami" value="3" checked> Not Deekshit
				</td>
			</tr>
			<tr>
				<td>Deeksha Date</td>
				<td><input type="text" name="deekshaDate" value=""/>
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
				<td><input type="text" name="deekshaAshramText" value=""/></td>
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
				<td><input type="text" name="occupationText" value=""/></td>
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
				<td><input type="text" name="otherProfText" value=""/></td>
			</tr>
		</table>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onClick="editProfile()">Add</a></form>
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
	String option = "";
	while (it.hasNext()) {
		Map.Entry pairs = (Map.Entry)it.next();
		option += "<option value=\""+pairs.getKey()+"\">" + pairs.getValue()+" </option>";
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
	String option = "";
	while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        option += "<option value=\""+pairs.getKey()+"\">" + pairs.getValue()+" </option>";
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
   window.open("upload.jsp?mid=","Upoad Doc.");

 }

 function getStates() {
		var index = document.getElementById('country_hidden_name').selectedIndex;
		var cid = document.getElementById('country_hidden_name')[index].value;
		//alert('cid='+cid);
		var conn = new Ext.data.Connection();
		conn.request({
		    url: 'getAllowedAreas?cid='+cid,
		    method: 'GET',
		    success: function(responseObject) {
		        var ss = responseObject.responseText;
		        //alert(ss);
		        var objDropdown = document.getElementById('state_hidden_name');
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
	   var index = document.getElementById('state_hidden_name').selectedIndex;
		var sid = document.getElementById('state_hidden_name')[index].value;
		//alert(sid);	   
		var conn = new Ext.data.Connection();
				conn.request({
				    url: 'getAllowedAreas?sid='+sid,
				    method: 'GET',
				    success: function(responseObject) {
				        var ss = responseObject.responseText;
				        var objDropdown = document.getElementById('district_hidden_name');
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