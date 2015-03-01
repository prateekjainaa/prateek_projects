<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript">

function change() {
	var tt = document.getElementById("chPass");
	var pass1=document.getElementById("pass1").value;
	var pass=document.getElementById("pass").value;
	document.getElementById("module").value = "chgPasswd";
	if(pass1.length == 0) {
     alert("password cannot be blank");
     return;
	}
	if(pass.length == 0) {
	     alert("password cannot be blank");
	     return;
	}
	if(pass1 == pass) {
		tt.submit();
	} else {
	 alert("passwords did not match.");
	 return;
	}
	
}

function reset0(){
	var tt = document.getElementById("chPass");
	tt.reset();
}

</script>
<body>

<form id="chPass" action="consoleadminlogin" method="post">

<table align="center"> 
<tr>
<td>
New Password
</td>
<td>
<input type="password" name="pass1" id="pass1"></input>
</td>
</tr>
<tr>
<td>
Re-type Password
</td>
<td>
<input type="password" name="pass" id="pass"></input>
</td>
</tr>
<tr>
<td>
<a href="javascript:void(0);" onclick="change()">change</a>
</td>
<td>
<a href="javascript:void(0);" onclick="reset0()">reset</a>
</td>

</tr>
</table>

<input type="hidden" id="module" name="module" value=""/>
<input type="hidden" id="aid" name="aid" value="<%= request.getParameter("aid") %>"/>

</form>


</body>
</html>