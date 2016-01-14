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
					<td>
						<c:out value="${car.carId}" />
					</td>
					<td>
						<c:out value="${car.carModel}" />
					</td>
					<td>
						<c:out value="${car.regNumber}" />
					</td>
					<td>
						<c:out value="${car.vinNumber}" />
					</td>
					<td>
						<c:out value="${car.customer.lastName}" />
						<c:out value=" ${car.customer.firstName}" />
					</td>
					<td>
						<c:out value="${car.customer.phone}" />
					</td>
					<td>
						<form method="post">
							<input type="hidden" name="car_id" value="${car.carId}"></input>
							<input hidden="hidden" name="homePage" value="carPage">
							<input type="submit" formaction="<c:url value="/admin/deleteCar"></c:url>" value="Delete"/>
							<input type="submit" formaction="<c:url value="/admin/updateExistingCar"></c:url>" value="Update"/>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		
		<form action="<c:url value="/admin/addNewCar"></c:url>" method="get">
			<div style="width: 50%; margin: 0 auto; text-align: center;">
				<input hidden="hidden" name="homePage" value="carPage">
				<button type="submit" class="btn btn-primary btn-block btn-large">Add new car</button>
			</div>
		</form>
	
	</jsp:attribute>
</t:mainTemplate>
