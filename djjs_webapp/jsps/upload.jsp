<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript">

</script>
<title>Upload Image</title>
</head>


<body>

<form action="uploadDoc" enctype="multipart/form-data" method="post">
<input  type="file" name="doc"/>
<p> Select Document to Upload.</p>
<input type="hidden" name="mid" value="<%= request.getParameter("mid") %>"/>
<input type="submit" value="submit"/>
</form>


</body>
</html>
