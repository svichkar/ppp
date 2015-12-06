<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:generalManager title="Add Subject">
	<jsp:attribute name="content_area">
	<h1>Add Subject</h1>
		<h2>Subject:</h2>
		<form action="addNewSubject.do" method="post">
		<b>Subject Name:</b><br>
		<input type="text" name="subject"><br>		
		<b>Term:</b><br>
		<select name="term">
		<c:forEach var="item" items="${terms}">
		<option value="${item.alias}">${item.alias}</option>		
		</c:forEach>
		</select><br>
		<p>
				<input type="submit" value="Save">
			</p>
		</form>	
</jsp:attribute>
</t:generalManager>