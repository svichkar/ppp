<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/style/style.css" var="cssUrl" />
<c:set var="title"
	value="${action == 'add' ? 'Add New Worker To Order' : 'Edit Worker For Order'}" />
<html>
<head>
<title><c:out value="${title}" /></title>
<link href="${cssUrl}" type="text/css" rel="stylesheet" />
</head>
<body>
	<div class="head1"></div>
	<div align="center">
		<p>
			<c:out value="${title}" />
		</p>
		<div class="head1"></div>
		<form action="<c:url value="/admin/orderWorkerPost.do"></c:url>"
			method="POST">
			<input type="hidden" name="order_id" value="${worker.orderId}" />
			<div class="formElement">
				<p>Worker Name:</p>
				<select name="worker_id">
					<c:forEach var="item" items="${workers}">
						<option value="${item.workerId}"
							<c:if test="${worker.workerId eq item.workerId}"> selected</c:if>><c:out
								value="${item.firstName}" />
							<c:out value="${item.lastName}" /></option>
					</c:forEach>
				</select>
			</div>
			<div class="formElement">
				<p>Is completed:</p>
				<select name="is_completed">
					<option value="Y"
						<c:if test="${worker.isCompleted == 'Y'}"> selected</c:if>>Y</option>
					<option value="N"
						<c:if test="${worker.isCompleted == 'N'}"> selected</c:if>>N</option>
				</select>
			</div>
			<div class="formElement">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /> <input type="submit" value="Submit"
					class="input_add" />
			</div>
		</form>
	</div>
</body>
</html>