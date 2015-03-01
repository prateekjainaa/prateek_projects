<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<%@page import="java.util.List"%>
<%@page import="org.djjs.model.Container"%><html
	xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<script src='/jmjk/ext-core.js' type='text/javascript'></script>
<script type="text/javascript" src="/jmjk/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="/jmjk/ext-all-debug.js"></script>
<body>

<table align="center">
	<tr>
		<td><font color="red">Country</font></td><td><select id="countryCodes" name="countryCodes" size="1">

			<%
			    StringBuffer buff = new StringBuffer();
						List countries = (List) request.getAttribute("allCountries");
						for (int i = 0; i < countries.size(); i++) {
							Container c = (Container) countries.get(i);
							buff.append("<option value=\"" + c.getValue()
									+ "\" onclick=\"getStates()\">" + c.getName()
									+ "</option>");
						}
			%>
			<%=buff.toString()%>
		</select></td>
	</tr>
<tr>
		<td><font color="red">State</font></td>
		<td><select id="stateCodes" name="stateCodes" size="1"
			onclick="getAashrams()" style="visibility:hidden">
			<%=request.getAttribute("stateCodes")%>
		</select></td>
	</tr>
</table>

<div id="aashrams">

<table id="aashramTable" border="1">


</table>

</div>


</body>
<script type="text/javascript">
	function getStates() {
		var index = document.getElementById('countryCodes').selectedIndex;
		var cid = document.getElementById('countryCodes')[index].value;
		//alert('cid='+cid);
		var conn = new Ext.data.Connection();
		conn.request({
		    url: '/djjs_webapp/getUser?cid='+cid,
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
		        document.getElementById("stateCodes").style.visibility="visible";
		    },
		     failure: function() {
		         Ext.Msg.alert('Status', 'Please try again later.');
		     }
		});	
   } 

   function getAashrams() {
	   var index = document.getElementById('stateCodes').selectedIndex;
		var sid = document.getElementById('stateCodes')[index].value;
		//alert(sid);	   
		var conn = new Ext.data.Connection();
				conn.request({
				    url: 'ashrams?sid='+sid,
				    method: 'POST',
				    success: function(responseObject) {
				        var ss = responseObject.responseText;
				        var aashramTable = document.getElementById('aashramTable');
				        aashramTable.innerHTML = ss;
				       
				    },
				     failure: function() {
				         alert('Failed to get Info.');
				     }
		});	
		
		
		
   }

</script>
</html>