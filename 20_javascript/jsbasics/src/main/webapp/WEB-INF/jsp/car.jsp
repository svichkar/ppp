<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/style/style.css" var="cssUrl" />
<c:url value="/js/focus.js" var="jsfocusUrl" />
<c:url value="/js/car.js" var="jscarUrl" />
<c:set var="title"
	value="${action == 'add' ? 'Add New Car' : 'Edit Car'}" />
<html>
<head>
<title><c:out value="${title}" /></title>
<link href="${cssUrl}" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${jsfocusUrl}"></script>
<script type="text/javascript" src="${jscarUrl}"></script>
</head>
<body>
	<div class="head1"></div>
	<div align="center">
		<p>
			<c:out value="${title}" />
		</p>
		<div class="head1"></div>
		<form action="<c:url value="/admin/carPost.do"></c:url>" method="POST"
			class="focusableForm">
			<c:if test="${action=='edit'}">
				<div class="formElement">
					<p>Car ID:</p>
					<input type="text" name="id" value="${car.carId}" readonly/>
					<div class="hidden">Id of the car</div>
				</div>
			</c:if>
			<div class="formElement">
				<p>Car Description:</p>
				<input type="text" name="description"
					value="${car.description == null ? '' : car.description}" />
				<div class="hidden">Description of the car</div>
			</div>
			<div class="formElement">
				<p>Car Model:</p>
				<input type="text" name="model"
					value="${car.model == null ? '' : car.model}" />
				<div class="hidden">Model of the car</div>
			</div>
			<div class="formElement">
				<p>Car VIN:</p>
				<input type="text" name="vin"
					value="${car.vin == null ? '' : car.vin}" />
				<div class="hidden">VIN of the car</div>
			</div>
			<div class="formElement">
				<p>Customer:</p>
				<select name="customer_id">
					<c:forEach var="item" items="${customers}">
						<option value="${item.customerId}"
							<c:if test="${car.customer.customerId eq item.customerId}"> selected</c:if>><c:out
								value="${item.firstName}" />
							<c:out value="${item.lastName}" /></option>
					</c:forEach>
				</select>
			</div>
			<div class="formElement">
			<input type="hidden" name="target" value="Cars" /> <input
				type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<input type="submit" value="Submit" class="input_add" />
			</div>
		</form>
	</div>
</body>
</html>