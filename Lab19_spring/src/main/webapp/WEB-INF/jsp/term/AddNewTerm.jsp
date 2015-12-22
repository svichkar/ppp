<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:generalManager title="Add Term">
	<jsp:attribute name="content_area">
	<h1>Add Term</h1>
		<h2>Term:</h2>
		<form action="<c:url value="/terms/addNewTerm.do"></c:url>" method="post">
		<b>Alias:</b><br>
		<input type="text" name="alias"><br>		
		<p><input type="submit" value="Save"></p>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>	
</jsp:attribute>
</t:generalManager>