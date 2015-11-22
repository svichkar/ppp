<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/style/style.css" var="cssUrl"/>
<c:set var="title" value="${action == 'add' ? 'Add New Car' : 'Edit Car'}" />
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
		<form action="carPost.do" method="POST">
			<c:if test="${action=='edit'}">
			<p>Car ID: </p><input type="text" name="id" value="${car.id}" /><br>			
			<div class="buffer"></div>
			</c:if>
			<p>Car Description: </p><input type="text" name="description" value="${car.description == null ? '' : car.description}" /><br>
			<div class="buffer"></div>
			<p>Car Model: </p><input type="text" name="model" value="${car.model == null ? '' : car.model}" /><br>
			<div class="buffer"></div>
			<p>Car VIN: </p><input type="text" name="vin" value="${car.vin == null ? '' : car.vin}" /><br>
			<div class="buffer"></div>
			<p>Customer ID: </p><input type="text" name="customer_id" value="${car.customerId == null ? '' : car.customerId}" /><br>
			<div class="buffer"></div>
			<input type="hidden" name="target" value="Cars" />
			<input type="submit" value="Submit" class="input_add"/>
		</form>
	</div>
</body>
</html>