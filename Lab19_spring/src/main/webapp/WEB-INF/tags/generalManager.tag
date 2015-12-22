<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="title"%>
<%@attribute name="content_area" fragment="true"%>
<html>
<head>
<title>${title}</title>
<link rel="stylesheet" href="<c:url value="css/style.css"/>">
</head>
<body>
	<a href="logout.do">Logout</a>
	<div id="hmenu">
		<ul>
			<li><a href="<c:url value="/login.do"/>">Home</a></li>
			<li><a href="<c:url value="/students/students.do"/>">Students</a></li>		
			<li><a href="<c:url value="/studentGroups/studentGroups.do"/>">Student Groups</a></li>		
			<li><a href="<c:url value="/terms/terms.do"/>">Terms</a></li>
			<li><a href="<c:url value="/subjects/subjects.do"/>">Subjects</a></li>
			<li><a href="<c:url value="/journal/journal.do"/>">Journal</a></li>
		</ul>
	</div>
	<div id="content">
		<jsp:invoke fragment="content_area" />
	</div>
</body>
</html>
