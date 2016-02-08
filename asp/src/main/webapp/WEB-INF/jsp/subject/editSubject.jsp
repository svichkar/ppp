<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:generalManager title="Edit Subject">
	<jsp:attribute name="content_area">
	<h1>Edit Subject</h1>
		<h2>Subject:</h2>
		<c:url var="updateUEL" value="/subjects/update-subject"/>
		<form:form action="${updateUEL}" method="post" commandName="SubjectModel">
			<form:label path="name">Subject Name:</form:label><br>
			<form:input path="name" type="text" tooltip="Name of subject" value="${subject.name}"/><br>
			<form:label path="term.alias">Term:</form:label><br>
			<form:select path="term.alias" items="${termList}" />
			<input type="submit" value="Save" style="width: 100px;"/>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form:form>
    </jsp:attribute>
</t:generalManager>