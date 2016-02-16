<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="title"%>
<%@attribute name="content_area" fragment="true"%>
<html>
<head>
<title>${title}</title>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
</head>
<body>
	<c:url var="logoutUrl" value="/logout" />
	<form action="${logoutUrl}" method="post">
		<input type="submit" value="Log out" /> 
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	<div id="hmenu">
		<ul>
			<li><a href="<c:url value="/home"/>">Home</a></li>
			<li><a href="<c:url value="/students/students"/>">Students</a></li>		
			<li><a href="<c:url value="/studentGroups/student-groups"/>">Student Groups</a></li>		
			<li><a href="<c:url value="/terms/terms"/>">Terms</a></li>
			<li><a href="<c:url value="/subjects/subjects"/>">Subjects</a></li>
			<li><a href="<c:url value="/journal/journal"/>">Journal</a></li>
		</ul>
	</div>
	<div id="content">
		<jsp:invoke fragment="content_area" />
	</div>
	
	<div id="myAlt" style="visibility: hidden;"></div>
</body>
</html>
