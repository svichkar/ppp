<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title"
	value="${action == 'add' ? 'Add New Car' : 'Edit Car'}" />
<c:url value="/style/style.css" var="cssUrl" />
<html>
<head>
<title><c:out value="${title}" /></title>
<link href="${cssUrl}" type="text/css" rel="stylesheet" />
</head>
<body>
	<div id="global_container">
		<div class="head1" align="center">
			<div class="buffer"></div>
			<form method="POST" action="<c:url value="/logout" />">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /> <input type="submit" value="Logout"
					class="nav_element">
			</form>
		</div>
		<div id="nav_menu" align="center">
			<div class="buffer"></div>
			<div id="nav_elements">
				<form method="POST" action="<c:url value="/nav.do"></c:url>">
					<input type="submit" name="target" value="Orders"
						class="nav_element"> <input type="submit" name="target"
						value="Cars" class="nav_element"> <input type="submit"
						name="target" value="Customers" class="nav_element"> <input
						type="submit" name="target" value="Parts" class="nav_element">
					<input type="submit" name="target" value="Workers"
						class="nav_element"> <input type="hidden"
						name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
			</div>
		</div>
		<div id="content">
		</div>
	</div>
	<script id="allcars-content" type="text/template">
		<div align="center" id="allCars" class="block">
				<h1>All Cars</h1>
			</div>
			<table align="center" width=70% border=1 cellpadding=8>
				<tr height=40>
					<td width=5%><b>Car ID</b></td>
					<td width=20%><b>Car Model</b></td>
					<td width=15%><b>Car VIN</b></td>
					<td width=25%><b>Car Description</b></td>
					<td width=15%><b>Customer Name</b></td>
					<td width=20%><b>Action</b></td>
				</tr>
				<c:forEach var="item" items="<%= collection%>">
					<tr class="highlightable">
						<td><c:out value="${item.carId}" /></td>
						<td><c:out value="${item.carModel}" /></td>
						<td><c:out value="${item.carVin}" /></td>
						<td><c:out value="${item.carDescription}" /></td>
						<td><c:out value="${item.customerName}" /></td>
						<td>
							<form method="POST">
								<input type="hidden" name="car_id" value="${item.carId}">
								<input type="hidden" name="target" value="Cars" /> <input
									type="submit" name="action"
									formaction="<c:url value="/admin/editCar.do"></c:url>"
									value="Edit" class="input_edit"> <input type="submit"
									name="action"
									formaction="<c:url value="/admin/deleteCar.do"></c:url>"
									value="Delete" class="input_edit"> <input type="hidden"
									name="${_csrf.parameterName}" value="${_csrf.token}" />
							</form>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan=6>
						<div align="center">
							<form action="<c:url value="/admin/addCar.do"></c:url>"
								method="GET">
								<input type="submit" value="Add new car" class="input_add">
							</form>
						</div>
					</td>
				</tr>
			</table>
	</script>
</body>
</html>