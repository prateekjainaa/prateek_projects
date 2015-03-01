<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="org.djjs.model.MemberVO"%>
<%@page import="java.util.List"%><html
	xmlns="http://www.w3.org/1999/xhtml">
<head>
<style type="text/css" media="all">
      @import url("css/maven-base.css");
      @import url("css/maven-theme.css");
      @import url("css/site.css");
      @import url("css/screen.css");
  </style>
  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ taglib uri="/tags/display" prefix="display"%>
<%
    if (null == session.getAttribute("members")) {
		session
			.setAttribute("members", request
				.getAttribute("members"));
    } else {
		request
			.setAttribute("members", session
				.getAttribute("members"));
    }
%>
<script type="text/javascript" src="/jmjk/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="/jmjk/ext-all-debug.js"></script>
<link rel="stylesheet" type="text/css" href="/jmjk/resources/css/ext-all.css" />
<script type="text/javascript">

function smsLength() {
   var area = document.getElementById('smsText');
   var text = area.value;	   	
   var textLength = text.length;
   var statusDiv = document.getElementById('smslengthstatus');
   statusDiv.innerHTML = ' '+textLength+' characters SMS';  
}

function selectALL() {
	var theForm = document.forms[0];
	var z = 0;
	for(z=0; z<theForm.length;z++){
	      if(theForm[z].type == 'checkbox'){
	    	  theForm[z].checked = true;		  
		  }
	     }
}

function selectNONE() {
	var theForm = document.forms[0];
	var z = 0;
	for(z=0; z<theForm.length;z++){
	      if(theForm[z].type == 'checkbox'){
	    	  theForm[z].checked = false;		  
		  }
	     }
    }

function send(command, text) {
var theForm = document.forms[0];
var z = 0;
var idString = '';
for(z=0; z<theForm.length;z++){
      if(theForm[z].type == 'checkbox' && theForm[z].checked){
	  idString = idString + theForm[z].value +',';
	  
	  }
     }
     
     if(idString.length === 0) {
			Ext.Msg.alert('Error!','select atleast one recipient');
			return;
     }
     
     idString = idString.substring(0,idString.length-1);

var conn = new Ext.data.Connection();
conn.request({
    url: 'command?command='+command+'&target='+idString,
    method: 'POST',
    params: {'text': text},
    success: function(responseObject) {
        var ss = responseObject.responseText;
        Ext.Msg.alert('Status', ' sent');
    },
     failure: function() {
         Ext.Msg.alert('Status', 'Request Failed. Please try again.');
     }
});	


}

function sendSMS() {
var area = document.getElementById('smsText');
   var text = escape(area.value);
	send('sms', text);
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
<%
    org.displaytag.decorator.CheckboxTableDecorator decorator = new org.displaytag.decorator.CheckboxTableDecorator();
    decorator.setId("memberID");
    decorator.setFieldName("_chk");
    pageContext.setAttribute("checkboxDecorator", decorator);
%>

<form name="checkboxselectform">

<display:table name="members" id="thiswilgetdisplayed"
	pagesize="10" defaultorder="ascending" decorator="checkboxDecorator"
	excludedParams="_chk" class="isis">
	<display:column property="checkbox" media="html"/>
	<display:column property="memberID" title="MID" sortable="true" />
	<display:column property="firstNameText" title="First Name" />
	<display:column property="lastNameText" title="Last Name" />
	<display:column property="phoneMobileText" title="Mobile" />
	<display:column property="fullAddText" title="Address" />
	<display:column property="sex" title="Sex" />
	<display:column title="Edit" href="getUser" paramId="mid"
		paramProperty="memberID">details</display:column>

</display:table>

</form>
<%
 List lst0 = (List)session.getAttribute("members");
 int size  = lst0.size();
%>
<display:table name="members" id="thiswilnotgetdisplayed"
	pagesize="<%= size %>" defaultorder="ascending" export="true">
	
	<display:column property="memberID" title="MID" sortable="true" />
	<display:column property="firstNameText" title="First Name" />
	<display:column property="lastNameText" title="Last Name" />
	<display:column property="phoneMobileText" title="Mobile" />
	<display:column property="fullAddText" title="Address" />
	<display:column property="sex" title="Sex" />	

</display:table>
select &nbsp;|<a href="javascript:void(0);" onclick="javascript:selectALL();">
ALL </a>&nbsp;|&nbsp;
<a href="javascript:void(0);" onclick="javascript:selectNONE();">NONE </a>&nbsp;|&nbsp; 
<a href="javascript:void(0);" onclick="javascript:sendSMS();"> SMS </a>

<p/>
<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea rows="7" cols="24" id="smsText" name="smsText" onkeyup="javascipt:smsLength()">Enter SMS text here!
</textarea>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<div id="smslengthstatus"></div>

</body>
<script type="text/javascript">
var tt = document.getElementById('thiswilnotgetdisplayed');
tt.style.display="none";
</script>
</html>