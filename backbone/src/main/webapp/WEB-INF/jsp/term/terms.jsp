<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:generalManager title="Terms">
<jsp:attribute name="content_area">
<script src="<c:url value="/resources/js/table.js"/>"></script>
<h1>Terms</h1>
<table style="width: 40%" border="1">
		<tr>
			<th width=20%>Term Id</th>
			<th width=50%>Alias</th>
			<th width=30%>Action</th>
		</tr>
		<c:forEach var="term" items="${terms}">
			<tr>
				<td>${term.termId}</td>
				<td>${term.alias}</td>				
				<td>
					<form action="<c:url value="/terms/edit-term"></c:url>" method="get">
						<input type="hidden" name="termId" value="${term.termId}">
						<input type=submit name="edit" value="Edit"	style="width: 100%; height: 50%;">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					</form>
					<form action="<c:url value="/terms/delete-term"></c:url>" method="post">
						<input type="hidden" name="termId" value="${term.termId}"> 
						<input type=submit name="delete" value="Delete" style="width: 100%; height: 50%;">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>	
	<form action="<c:url value="/terms/add-new-term"></c:url>" method="get">
	<input type=submit name="create" value="Add new" style="width: 100px;">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
</jsp:attribute>
</t:generalManager>