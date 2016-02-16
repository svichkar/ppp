<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="title"%>
<%@attribute name="content_area" fragment="true"%>
<html>
<head>
<title>${title}</title>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
<script src="jquery-2.2.0.js"></script>
<script src="underscore.js"></script>
<script src="backbone.js"></script>
<script src="onePage.js"></script>
</head>
<body>
	<c:url var="logoutUrl" value="/logout" />
	<form action="${logoutUrl}" method="post">
		<input type="submit" value="Log out" /> 
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	<div id="hmenu">
		<ul>
			<li><a id="home" href="<c:url value="/home"/>">Home</a></li>
			<li><a id="addNew" href="<c:url value="/admin/add-new-user"/>">Add new user</a></li>	
		</ul>
	</div>
	<div id="content">
		<jsp:invoke fragment="content_area" />
	</div>
	<div id="myAlt" style="visibility: hidden;"></div>
</body>
</html>