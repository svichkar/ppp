<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:generalManager title="Student Groups"> 
<jsp:attribute name="content_area">
<script src="<c:url value="/resources/js/table.js"/>"></script>
<h1>Student Groups</h1>
<table style="width: 40%" border="1">
		<tr>
			<th width=20%>Group Id</th>
			<th width=50%>Group Name</th>
			<th width=30%>Action</th>
		</tr>
		<c:forEach var="item" items="${studentGroups}">
			<tr>
				<td>${item.studentGroupId}</td>
				<td>${item.studentGroupName}</td>				
				<td>
						<form action="<c:url value="/studentGroups/edit-student-group"></c:url>" method="get">
							<input type="hidden" name="studentGroupId" value="${item.studentGroupId}">
							<input type=submit name="edit" value="Edit"	style="width: 100%; height: 50%;">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						</form>
						<form action="<c:url value="/studentGroups/delete-student-group"></c:url>" method="post">
							<input type="hidden" name="studentGroupId" value="${item.studentGroupId}"> 
							<input type=submit name="delete" value="Delete" style="width: 100%; height: 50%;">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						</form>
					</td>
			</tr>
		</c:forEach>
	</table>
	
	<form action="<c:url value="/studentGroups/add-new-student-group"></c:url>" method="get">
	<input type=submit name="create" value="Add new" style="width: 100px;">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
</jsp:attribute>
</t:generalManager>