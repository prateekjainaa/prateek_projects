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
function getStates() {
	var index = document.getElementById('countryList').selectedIndex;
	var cid = document.getElementById('countryList')[index].value;
	var id = document.getElementById('aid').value;
	var conn = new Ext.data.Connection();
	conn.request({
	    url: 'assignAreas?module=getAllowedStates&cid='+cid+'&aid='+id,
	    method: 'GET',
	    success: function(responseObject) {
	        var ss = responseObject.responseText;
	        var objDropdown = document.getElementById('stateList');
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
function getDistricts() {
	var index = document.getElementById('countryList').selectedIndex;
	var cid = document.getElementById('countryList')[index].value;
	var index1 = document.getElementById('stateList').selectedIndex;
	var sid = document.getElementById('stateList')[index1].value;
	var id = document.getElementById('aid').value;
	var conn = new Ext.data.Connection();
	var tempurl = 'assignAreas?module=getAllowedDistricts&sid='+sid+'&aid='+id+'&cid='+cid; 
	conn.request({
	    url: tempurl,
	    method: 'GET',
	    success: function(responseObject) {
	        var ss = responseObject.responseText;
	        var objDropdown = document.getElementById('districtList');
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


function resetForm(){
	document.forms[0].reset();
}

function remove(){
	var mod = document.getElementById('module');
	mod.value='removeAreas';
	document.forms[0].submit();
}


</script>

</head>
<%@page import="java.util.Iterator"%>
<%@page import="org.djjs.model.State"%>
<%@page import="org.djjs.model.District"%>
<%@page import="org.djjs.model.Country"%>
<%@page import="java.util.Set"%>
<%@page import="org.djjs.model.AdminVO"%>
<body>
<font color="red" size="3">
<p>Remove Areas</p>
</font>
<table align="right">
<tr>
 <td><a href="logout">logout</a>
 </td>
</tr>

</table>
<form action="assignAreas" method="post">
<%
	AdminVO vo = (AdminVO) request.getAttribute("adminDetail");
	Set count = vo.getCountries();
	String countryHTML = "";
	int aid = vo.getId();
	if (count != null) {
		Iterator itr = count.iterator();
		while (itr.hasNext()) {
			Country c = (Country) itr.next();
			countryHTML += "<option value=" + c.getId() + ">"
					+ c.getName() + "</option>";
		}
	}
%>
<table align="center">
	<tr>
		<td>Country:</td>
		<td><select name="countryList" id="countryList"
			onclick="getStates()">
			<%=countryHTML%>
		</select></td>
	</tr>
	<tr>
		<td>State:</td>
		<td><select name="stateList" id="stateList"
			onclick="getDistricts()"></select></td>
	</tr>
	<tr>
		<td>Districts:</td>
		<td><select name="districtList" id="districtList"
			multiple="multiple"></select></td>
	</tr>
	<tr>
		<td><input type="hidden" name="aid" id="aid" value="<%=aid%>" />
		</td>
		<td><input type="hidden" name="module" id="module" /></td>
	</tr>
	<tr>
		<td><a href="javascript:void(0);" onclick="remove()">remove</a></td>
		<td><a href="javascript:void(0);" onclick="resetForm()">reset</a>
		</td>
	</tr>
</table>

</form>
</body>

</html>