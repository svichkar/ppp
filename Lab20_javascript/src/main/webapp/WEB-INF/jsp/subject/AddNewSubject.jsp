<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:generalManager title="Add Subject">
	<jsp:attribute name="content_area">
	<h1>Add Subject</h1>
		<h2>Subject:</h2>
		<form action="<c:url value="/subjects/addNewSubject.do"></c:url>" method="post">
		<b>Subject Name:</b><br>
		<input type="text" name="subject" tooltip="Name of subject"><br>		
		<b>Term:</b><br>
		<select name="term" tooltip="Choose a term">
		<c:forEach var="item" items="${terms}">
		<option tooltip="Choose term for subject" value="${item.alias}">${item.alias}</option>		
		</c:forEach>
		</select><br>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<p><input type="submit" value="Save"></p>
		</form>	
</jsp:attribute>
</t:generalManager>