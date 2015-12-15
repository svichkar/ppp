<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:mainTemplate title="Cars Page">
	<jsp:attribute name="content_area">
		<div align="center">
		<h1>All Cars</h1>
	</div>
<table class="data">
	<tr>
		<th># Car</th>
		<th>Car model</th>
		<th>Car registration number</th>
		<th>Car VIN number</th>
		<th>Car owner</th>
		<th>Car owner phone number</th>
		<th>Action</th>
	</tr>
	<c:forEach var="car" items="${carList}">
		<tr>
			<td><c:out value="${car.car_id}" /></td>
			<td><c:out value="${car.car_model}" /></td>
			<td><c:out value="${car.reg_number}" /></td>
			<td><c:out value="${car.vin_number}" /></td>
			<td><c:out value="${car.customer.last_name}" /><c:out value=" ${car.customer.first_name}" /></td>
			<td><c:out value="${car.customer.phone}" /></td>
			<td>
				<form method="post">
					<input type="hidden" name="car_id" value="${car.car_id}"></input>
					<input hidden="hidden" name="fieldName" value="car"/>
					<input type="submit" name="action" formaction="deleteField" value="Delete"/>
					<input type="submit" name="target" formaction="updateField" value="Edit Car"/>
				</form>
			</td>
		</tr>
	</c:forEach>
</table>
<form action="addNew" method="get"><div style="width: 50%; margin: 0 auto; text-align: center;">
			<input hidden="hidden" name="homePage" value ="carPage">
			<button type="submit" class="btn btn-primary btn-block btn-large"
				name="target" value="car">Add new car</button></div>
		</form>

		</jsp:attribute>
</t:mainTemplate>
