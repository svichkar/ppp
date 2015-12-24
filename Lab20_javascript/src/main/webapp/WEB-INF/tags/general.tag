<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="title"%>
<%@attribute name="content_area" fragment="true"%>
<c:url value="/js/script.js" var="jsURL"/>
<html>
<head>
<title>${title}</title>
<link rel="stylesheet" href="<c:url value="/css/style.css"/>">
<script type="text/javascript" src="<c:url value="/js/script.js"/>"></script>
</head>
<body>
	<a href="<c:url value="/logout.do"/>">Logout</a>
	<div id="content">
		<jsp:invoke fragment="content_area" />
	</div>
</body>
</html>