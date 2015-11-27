<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
<form action="logout" enctype="text/plain" id="logout" method="get"
		name="logout" target="_self">
		<input style="float:right; margin-right:12px;" name="logout" type="submit" value="LOGOUT" />&nbsp;
	</form>
	<form action="search" enctype="text/plain" id="search" method="get"
		name="search" target="_self">
		<p style="text-align: center;">
			<input maxlength="100" name="search_bar" size="50" type="text" /><input
				name="performsearch" type="submit" value="Go" /><br> <input
				type="radio" name="group1" value="All" checked> All <input
				type="radio" name="group1" value="ByName"> By Name <input
				type="radio" name="group1" value="ByAuthor"> By Author <input
				type="radio" name="group1" value="ByPublisher"> By Publisher<input
				type="radio" name="group1" value="ByCategory"> By Category<br>
		<hr>


	</form>
</body>
</html>