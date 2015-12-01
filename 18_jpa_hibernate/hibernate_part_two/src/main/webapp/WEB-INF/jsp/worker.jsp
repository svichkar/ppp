<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/style/style.css" var="cssUrl"/>
<c:set var="title" value="${action == 'add' ? 'Add New Worker' : 'Edit Worker'}" />
<html>
<head>
<title><c:out value="${title}" /></title>
<link href="${cssUrl}" type="text/css" rel="stylesheet"/>
</head>
<body>
	<div class="head1"></div>
	<div align="center">
		<p><c:out value="${title}" /></p>
		<div class="head1"></div>
		<form action="workerPost.do" method="POST">
			<c:if test="${action=='edit'}">
			<p>Worker ID: </p><input type="text" name="id" value="${worker.workerId}" /><br>			
			<div class="buffer"></div>
			</c:if>
			<p>First Name: </p><input type="text" name="first_name" value="${worker.firstName == null ? '' : worker.firstName}" /><br>
			<div class="buffer"></div>
			<p>Last Name: </p><input type="text" name="last_name" value="${worker.lastName == null ? '' : worker.lastName}" /><br>
			<div class="buffer"></div>
			<p>Specialization: </p>
			<select name="specialization_id">
			<c:forEach var="item" items="${specs}">
			<option value="${item.specializationId}" <c:if test="${worker.workerSpecialization.specializationId eq item.specializationId}"> selected</c:if>><c:out value="${item.specializationName}" /></option>
			</c:forEach>
			</select><br>
			<div class="buffer"></div>
			<p>Status: </p>
			<select name="status_id">
			<c:forEach var="item" items="${statuses}">
			<option value="${item.statusId}"<c:if test="${worker.statusId eq item.statusId}"> selected</c:if>><c:out value="${item.statusName}" /></option>
			</c:forEach>
			</select><br>
			<div class="buffer"></div>
			<p>User: </p>
			<select name="user_id">
			<c:forEach var="item" items="${users}">
			<option value="${item.userId}"<c:if test="${worker.userId eq item.userId}"> selected</c:if>><c:out value="${item.userLogin}" /></option>
			</c:forEach>
			</select><br>
			<div class="buffer"></div>
			<input type="hidden" name="target" value="Workers" />
			<input type="submit" value="Submit" class="input_add"/>
		</form>
	</div>
</body>
</html>