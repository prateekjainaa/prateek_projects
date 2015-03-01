<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src='/jmjk/ext-core.js' type='text/javascript'></script>
<script type="text/javascript" src="/jmjk/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="/jmjk/ext-all-debug.js"></script>
<script type="text/javascript">

</script>


</head>

<body>

<table align="right">
<tr>
 <td><a href="logout">logout</a>
 </td>
</tr>

</table>
<table align="center">
	<form action="assignAreas" method="post">
	<tr>
		<td><font color="red">Country</font></td>
		<td><select id="countryCodes" name="countryCodes" size="1"
			onclick="getStates()">
			<%=request.getAttribute("cl")%>
		</select></td>
	</tr>

	<tr>
		<td><font color="red">State</font></td>
		<td><select id="stateCodes" name="stateCodes" size="1"
			onclick="getDisctricts()">
			<%=request.getAttribute("stateCodes")%>
		</select></td>
	</tr>

	<tr>
		<td><font color="red">District</font></td>
		<td><select id="districtCodes" name="districtCodes"
			multiple="multiple">
			<%=request.getAttribute("districtCodes")%>
		</select></td>
	</tr>


	<input type="hidden" name="module" id="module" />
	<input type="hidden" name="aid" id="aid" value="<%= request.getAttribute("id") %>"/>
	<tr>
		<td><a href="javascript:void(0);" onclick="addAreas()">add</a></td>
		<td><a href="javascript:void(0);" onclick="reset()">reset</a></td>
	</tr>
	<tr>
		<td></td>
		<td></td>
	</tr>

	</form>

</table>

</body>
<script type="text/javascript">

 function addAreas() {
    var mod = document.getElementById('module');
	mod.value='addAreas';
	document.forms[0].submit();
	
 }

 function reset() {
	 document.forms[0].reset();
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
