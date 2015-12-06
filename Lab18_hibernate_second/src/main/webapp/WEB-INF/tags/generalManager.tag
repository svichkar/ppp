<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="title"%>
<%@attribute name="content_area" fragment="true"%>
<html>
<head>
<title>${title}</title>
<link rel="stylesheet" href="css/style.css">
<script>
$(function() {$( "#datepicker" ).datepicker();});
</script>
</head>
<body>
	<a href="logout.do">Logout</a>
	<div id="hmenu">
		<ul>
			<li><a href="login.do">Home</a></li>
			<li><a href="Students.do">Students</a></li>		
			<li><a href="StudentGroups.do">Student Groups</a></li>		
			<li><a href="Terms.do">Terms</a></li>
			<li><a href="Subjects.do">Subjects</a></li>
			<li><a href="Journal.do">Journal</a></li>
		</ul>
	</div>
	<div id="content">
		<jsp:invoke fragment="content_area" />
	</div>
</body>
</html>
