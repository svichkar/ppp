<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general title="All Users"> 
	<jsp:attribute name="content_area">
	<script src="<c:url value="/resources/js/table.js"/>"></script>
	<h1>All Users</h1>
	<table border="1">
		<tr>
			<th width=10%>User Id</th>
			<th width=20%>User Name</th>
			<th width=20%>User Role</th>
			<th width=10%>Action</th>
		</tr>
		<c:forEach var="user" items="${users}">
			<tr>
				<td>${user.id}</td>
				<td>${user.userName}</td>
				<td>${user.role.roleName}</td>
				<td>
						<form action="edit-user" method="get">
							<input type="hidden" name="userId" value="${user.id}">
							<input id="edit" type=submit name="edit" value="Edit"	style="width: 100%; height: 50%;">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						</form>
						<form action="delete-user" method="post">
							<input type="hidden" name="userId" value="${user.id}"> 
							<input id="selete" type=submit name="delete" value="Delete" style="width: 100%; height: 50%;">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						</form>
					</td>
			</tr>
		</c:forEach>
	</table>
	
	<form action="add-new-user" method="get">
	<input id="addNewButton" type=submit name="create" value="Add new" style="width: 100px;">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	</jsp:attribute> 
</t:general>