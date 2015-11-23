<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/style/style.css" var="cssUrl" />
<c:set var="title"
	value="${action == 'add' ? 'Add New Order' : 'Edit Order'}" />
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
		<div class="third" id="left"></div>
		<div class="third" id="center">
			<form action="orderPost.do" method="POST">
				<c:if test="${action=='edit'}">
					<p>Order ID:</p>
					<input type="text" name="id" value="${order.id}" />
					<br>
					<div class="buffer"></div>
				</c:if>
				<p>Car Description:</p>
				<input type="text" name="description"
					value="${car.description == null ? '' : car.description}" /><br>
				<div class="buffer"></div>
				<p>Car Model:</p>
				<input type="text" name="model"
					value="${car.model == null ? '' : car.model}" /><br>
				<div class="buffer"></div>
				<p>Car VIN:</p>
				<input type="text" name="vin"
					value="${car.vin == null ? '' : car.vin}" /><br>
				<div class="buffer"></div>
				<p>Customer:</p>
				<select name="customer_id">
					<c:forEach var="item" items="${customers}">
						<option value="${item.id}"
							<c:if test="${car.customerId eq item.id}"> selected</c:if>><c:out
								value="${item.firstName}" />
							<c:out value="${item.lastName}" /></option>
					</c:forEach>
				</select><br>
				<div class="buffer"></div>
				<input type="hidden" name="target" value="Cars" /> <input
					type="submit" value="Submit" class="input_add" />
			</form>
		</div>
		<div class="third" id="right"></div>
	</div>
</body>
</html>