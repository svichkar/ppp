<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:mainTemplate title="Cars Page">
	<jsp:attribute name="content_area">
		<div align="center">
		<h1><c:out value="Edit existing car" /></h1>
		</div>
		<form id="partInOrder" action="<c:url value="/admin/updateCar"></c:url>" method="post">
			<input hidden="hidden" name="car_id" value="${car.carId}" />
			<input hidden="hidden" name="action" value="edit" />
			<table>
				<tr>
					<th class="nav-menu" colspan="2"><h3>Edit general info</h3></th>
				</tr>
				<tr>
					<td class="nav-menu">
						<b>Car ID</b>
					</td>
					<td class="nav-menu">
						<input name="carId" disabled="disabled" value="${car.carId}" />
					</td>
				</tr>
				<tr>
					<td class="nav-menu">
						<b>Car model</b>
					</td>
					<td class="nav-menu">
						<input name="carModel" value="${car.carModel}" />
					</td>
				</tr>
				<tr>
					<td class="nav-menu">
						<b>Car registration number</b>
					</td>
					<td class="nav-menu">
						<input name="regNumber" value="${car.regNumber}" />
					</td>
				</tr>
				<tr>
					<td class="nav-menu">
						<b>Car VIN number</b>
					</td>
					<td class="nav-menu">
						<input name="vinNumber" value="${car.vinNumber}" />
					</td>
				</tr>
				<tr>
					<td class="nav-menu">
						<b>Car description</b>
					</td>
					<td class="nav-menu">
						<input name="car_description" value="${car.carDescription}" />
					</td>
				</tr>
				<tr>
					<td class="nav-menu">
						<b>Actual owner</b>
					</td>
					<td class="nav-menu">
						<input disabled="disabled" name="carOwner" value="${car.customer.lastName} ${car.customer.firstName}" />
						<br/>
						<b>Actual owner</b>
						<br/>
						<select name="customer_id">
							<c:forEach var="customer" items="${customers}">
								<c:choose>
									<c:when test="${car.customer.customerId== customer.customerId}">
										<option selected="selected" value="${customer.customerId }"><c:out value="${customer.lastName }"></c:out><c:out value=" ${customer.firstName }"></c:out></option>
									</c:when>    
									<c:otherwise>
										<option value="${customer.customerId }"><c:out value="${customer.lastName }"></c:out><c:out value=" ${customer.firstName }"></c:out></option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="nav-menu" colspan="2">
						<input hidden="hidden" name="homePage" value="${homePage }">
						<button type="submit" class="btn btn-primary btn-block btn-large">Update</button>
					</td>
				</tr>
			</table>
		</form>

		<br />
		<br />
		<form action="<c:url value="/admin/adminPage"></c:url>" method="get">
			<div style="width: 50%; margin: 0 auto; text-align: center;">
				<button type="submit" class="btn btn-primary btn-block btn-large">Cancel</button>
			</div>
		</form>
	</jsp:attribute>
</t:mainTemplate>