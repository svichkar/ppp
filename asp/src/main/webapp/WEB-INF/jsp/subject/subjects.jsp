<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:generalManager title="Subjects">
	<jsp:attribute name="content_area">
<h1>Subjects</h1>
<h2>Search</h2>
<b>Search strategy:</b>
		<br>
<form action="<c:url value="/subjects/subjects"></c:url>" method="post">
  <input type="radio" name="searchType" value="subject" <c:if test="${radioSearch == 'subjects'}">checked</c:if>> Subject Name
  <input type="radio" name="searchType" value="term" <c:if test="${radioSearch == 'term'}">checked</c:if>> Term Name
  <input type="text" name="searchQuery" tooltip="Enter name of subject or term">
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
  <input type="submit" value="Search" style="width: 100px;">
</form> 
<h2>Subjects</h2>
<table style="width: 50%" border="1">
	<tr>
		<th width=15%>Subject Id</th>
		<th width=30%>Subject Name</th>
		<th width=30%>Term Name</th>
		<th width=15%>Action</th>
	</tr>
	<c:forEach var="subject" items="${subjects}">
		<tr>
			<td>${subject.subjectId}</td>
			<td>${subject.name}</td>
			<td>${subject.term.alias}</td>
			<td>
				<form action="<c:url value="/subjects/update-subject"></c:url>" method="get">
					<input type=submit name="edit" value="Edit"	style="width: 100%; height: 50%;">
					<input type="hidden" name="subjectId" value="${subject.subjectId}">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
				<form action="<c:url value="/subjects/delete-subject"></c:url>" method="post">
					<input type=submit name="delete" value="Delete" style="width: 100%; height: 50%;">
					<input type="hidden" name="subjectId" value="${subject.subjectId}">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
			</td>
		</tr>
	</c:forEach>
</table>
	
<form action="<c:url value="/subjects/add-new-subject"></c:url>" method="get">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<input type=submit name="create" value="Add new" style="width: 100px;">
</form>
</jsp:attribute>
</t:generalManager>