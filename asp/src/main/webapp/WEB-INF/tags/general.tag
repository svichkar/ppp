<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="title"%>
<%@attribute name="content_area" fragment="true"%>
<html>
<head>
<title>${title}</title>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
</head>
<body>
	<a href="<c:url value="j_spring_security_logout" />" >Logout</a>
	<div id="content">
		<jsp:invoke fragment="content_area" />
	</div>
</body>
</html>