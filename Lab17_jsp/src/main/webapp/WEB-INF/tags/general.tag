<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="title"%>
<%@attribute name="content_area" fragment="true"%>
<html>
<head>
<title>${title}</title>
</head>
<body>
	<a href = "logout.do">Logout</a>
	<div id="content">
		<jsp:invoke fragment="content_area" />
	</div>
</body>
</html>
