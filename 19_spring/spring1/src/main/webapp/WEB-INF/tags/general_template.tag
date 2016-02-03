<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@attribute name="title"%>
<%@attribute name="head_area" fragment="true"%>
<%@attribute name="content_area" fragment="true"%>
<%@attribute name="sidebar_area" fragment="true"%>

<c:url value="/style/style.css" var="cssUrl" />
<html>

<head>
<title>${title}</title>
<meta name="description" content="website description" />
<meta name="keywords" content="website keywords, website keywords" />
<meta http-equiv="content-type"
	content="text/html; charset=windows-1252" />
<link href="${cssUrl}" type="text/css" rel="stylesheet" />

</head>

<body>
<div id="header">
<jsp:invoke fragment="head_area" />
</div>

	
		<a href="logout"><spring:message/></a>
<div id="nav">

	<ul>
		<li><a class="active">Hi, <spring:message /><sec:authentication property="name"/> <sec:authentication property="authorities"/>!</a></li>
	 
		<li><a href="homepage">Home</a></li>
		<li><a href="findbook">Find book</a></li>
		<li><a href="addbook">Add book</a></li>
		<li><a href="addreader">Add reader</a></li>
		<li><a href="findreader">Loan book</a></li>
		<c:if test="${usrRole == 'admin'}">
			<li><a href="manageusers">Add user</a></li>
			<li><a href="addcategory">Add category</a></li>
		</c:if>
	</ul>

</div>

	<div id = "section" style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
		<jsp:invoke fragment="content_area" />
	</div> 

	

</body>


</html>