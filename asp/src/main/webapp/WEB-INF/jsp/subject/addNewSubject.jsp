<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:generalManager title="Add Subject">
	<jsp:attribute name="content_area">
	<h1>Add Subject</h1>
		<h2>Subject:</h2>
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
        <c:url var="createUrl" value="/subjects/add-new-subject"/>
		<form:form action="${createUrl}" method="post" commandName="SubjectModel">
            <form:label path="name">Subject Name:</form:label><br>
            <form:input path="name" type="text" tooltip="Name of subject"/><br>
            <form:label path="term.alias">Term:</form:label><br>
            <form:select path="term.alias" items="${termList}" />
            <input type="submit" value="Save" style="width: 100px;"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form:form>
	</jsp:attribute>
</t:generalManager>