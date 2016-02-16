<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:generalManager title="Add Term">
	<jsp:attribute name="content_area">
	<h1>Add Term</h1>
		<h2>Term:</h2>
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:url var="createUrl" value="/terms/create-term"/>
		<form:form action="${createUrl}" method="post" commandName="TermModel">
			<form:input type="hidden" path="termId"/>
			<form:label path="alias">Alias:</form:label><br>
			<form:input type="text" path="alias"/><br>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />	
			<p><input type="submit" value="Save" style="width: 100px;"></p>
		</form:form>		
	</jsp:attribute>
</t:generalManager>