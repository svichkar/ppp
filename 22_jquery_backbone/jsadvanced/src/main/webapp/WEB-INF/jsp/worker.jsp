<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/style/style.css" var="cssUrl" />
<c:url value="/js/basic.js" var="jsbasicUrl" />
<c:set var="title"
	value="${action == 'add' ? 'Add New Worker' : 'Edit Worker'}" />
<html>
<head>
<title><c:out value="${title}" /></title>
<link href="${cssUrl}" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${jsbasicUrl}"></script>
</head>
<body>
	<div class="head1"></div>
	<div align="center">
		<p>
			<c:out value="${title}" />
		</p>
		<div class="head1"></div>
		<form action="<c:url value="/admin/workerPost.do"></c:url>"
			method="POST" class="focusableForm">
			<c:if test="${action=='edit'}">
				<div class="formElement">
					<p>Worker ID:</p>
					<input type="text" name="id" value="${worker.workerId}" readonly />
					<div class="hidden">
						<p>Id of the worker</p>
					</div>
				</div>
			</c:if>
			<div class="formElement">
				<p>First Name:</p>
				<input type="text" name="first_name"
					value="${worker.firstName == null ? '' : worker.firstName}" />
				<div class="hidden">First name of the worker</div>
			</div>
			<div class="formElement">
				<p>Last Name:</p>
				<input type="text" name="last_name"
					value="${worker.lastName == null ? '' : worker.lastName}" />
				<div class="hidden">Last name of the worker</div>
			</div>
			<div class="formElement">
				<p>Specialization:</p>
				<select name="specialization_id">
					<c:forEach var="item" items="${specs}">
						<option value="${item.specializationId}"
							<c:if test="${worker.workerSpecialization.specializationId eq item.specializationId}"> selected</c:if>><c:out
								value="${item.specializationName}" /></option>
					</c:forEach>
				</select>
			</div>
			<div class="formElement">
				<p>Status:</p>
				<select name="status_id">
					<c:forEach var="item" items="${statuses}">
						<option value="${item.statusId}"
							<c:if test="${worker.status.statusId eq item.statusId}"> selected</c:if>><c:out
								value="${item.statusName}" /></option>
					</c:forEach>
				</select>
			</div>
			<div class="formElement">
				<p>User:</p>
				<select name="user_id">
					<c:forEach var="item" items="${users}">
						<option value="${item.userId}"
							<c:if test="${worker.user.userId eq item.userId}"> selected</c:if>><c:out
								value="${item.userLogin}" /></option>
					</c:forEach>
				</select>
			</div>
			<div class="formElement">
				<input type="hidden" name="target" value="Workers" /> <input
					type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<input type="submit" value="Submit" class="input_add" />
			</div>
		</form>
	</div>
</body>
</html>