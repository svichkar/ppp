<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="title"%>
<%@attribute name="head_area" fragment="true"%>
<%@attribute name="content_area" fragment="true"%>
<%@attribute name="message_area" fragment="true"%>
<%@attribute name="menu_area" fragment="true"%>
<c:url value="/style/style.css" var="cssUrl" />
<html>
<head>
<title>${title}</title>
<link href="${cssUrl}" type="text/css" rel="stylesheet" />
<link rel="shortcut icon" href="image/favicon.png" type="image/png" />
<meta http-equiv="content-type"
	content="text/html; charset=windows-1252" />
<jsp:invoke fragment="head_area" />
</head>
<body>
	<div id="menubar">
		<jsp:invoke fragment="menu_area" />
	</div>
	<div id="content">
		<jsp:invoke fragment="content_area" />
	</div>
	<div id="message">
		<jsp:invoke fragment="message_area" />
	</div>
	<br />
	<div id="footer">Copyright &copy; 2016 | All Rights Reserved</div>
</body>
</html>
