<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:generalManager title="Edit Term">
	<jsp:attribute name="content_area">
	<h1>Edit Term</h1>
		<h2>Term:</h2>
		<c:url var="updateUrl" value="/terms/update-term"/>
		<form:form action="${updateUrl}" method="post" commandName="TermModel">
			<form:label path="alias">Alias:</form:label><br>
			<form:input type="text" path="alias" value="${term.alias}" tooltip="Name of term"/><br>
			<input type="hidden" name="termId" value="${term.termId}"><br>	
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />	
			<p><input type="submit" value="Save"></p>
		</form:form>	
	</jsp:attribute>
</t:generalManager>