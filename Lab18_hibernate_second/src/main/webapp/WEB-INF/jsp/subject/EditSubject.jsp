<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:generalManager title="Edit Subject">
	<jsp:attribute name="content_area">
	<h1>Edit Subject</h1>
		<h2>Subject:</h2>
		<form action="editSubject.do" method="post">
		<b>Subject Name:</b><br>
		<input type="text" name="subject" value="${subject.name}"><br>	
		<input type="hidden" name="subjectId" value="${subject.subjectId}"><br>	
		<b>Term:</b><br>
		<select name="term">
		<c:forEach var="item" items="${terms}">
<<<<<<< HEAD
		<option value="${item.alias}"<c:if test="${subject.alias eq item.alias}"> selected</c:if>>${item.alias}</option>		
=======
		<option value="${item.alias}"<c:if test="${subject.term.alias eq item.alias}"> selected</c:if>>${item.alias}</option>		
>>>>>>> 0bd4b5a686e06f5baebec57e04b4ac6b7bb7e717
		</c:forEach>
		</select><br>
		<p>
				<input type="submit" value="Save">
			</p>
		</form>	
</jsp:attribute>
</t:generalManager>