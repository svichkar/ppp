<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:mainTemplate title="Cars Page">
	<jsp:attribute name="content_area">
		<div align="center">
			<h1>
				<c:out value="Edit existing car" />
			</h1>
		</div>
		<form id="partInOrder" action="updateField" method="post">
				<input hidden="hidden" name="car_id" value="${car.car_ID}" />
				<input hidden="hidden" name="action" value="edit" />
<table>
				<tr>
					<th class="nav-menu" colspan="2"><h3>Edit general info</h3></th>
				</tr>
				<tr>
					<td class="nav-menu"><b>Car ID</b></td>
					<td class="nav-menu"><input disabled="disabled" value="${car.car_ID}" />
					</td>
				</tr>
				<tr>
					<td class="nav-menu"><b>Car model</b></td>
					<td class="nav-menu"><input name="carModel" value="${car.carModel}" /></td>
				</tr>
				<tr>
					<td class="nav-menu"><b>Car registration number</b></td>
					<td class="nav-menu"><input name="regNumber" value="${car.regNumber}" /></td>
				</tr>
				<tr>
					<td class="nav-menu"><b>Car VIN number</b></td>
					<td class="nav-menu"><input name="vinNumber" value="${car.vinNumber}" /></td>
				</tr>
				<tr>
					<td class="nav-menu"><b>Car description</b></td>
					<td class="nav-menu"><input name="car_description" value="${car.car_description}" /></td>
				</tr>
				<tr>
					<td class="nav-menu"><b>Actual owner</b></td>
					<td class="nav-menu"><input disabled="disabled" name="carOwner" value="${car.carOwner}" />
					<br/>
					<b>Actual owner</b>
					<br/>
					<select name="customer_id">
					<c:forEach var="customer" items="${customers}">
					<c:choose>
				    <c:when test="${car.carOwner== customer.fullName}">
					<option selected="selected" value="${customer.customer_id }"><c:out value="${customer.fullName }"></c:out></option>
    				</c:when>    
    				<c:otherwise>
					<option value="${customer.customer_id }"><c:out value="${customer.fullName }"></c:out></option>
    				</c:otherwise>
					</c:choose>
					</c:forEach>
					</select>
					</td>
				</tr>
				<tr>
				<td class="nav-menu" colspan="2">
				<button type="submit" class="btn btn-primary btn-block btn-large" name="target" value="updateCar">Update</button>
				</td>
				</tr>
			</table>
						<input hidden="hidden" name="action" value="edit" />
		</form>
		<br />
		<br />
		<form action="adminPage" method="get">
			<div style="width: 50%; margin: 0 auto; text-align: center;">
			<button type="submit" class="btn btn-primary btn-block btn-large"
					name="target" value="car">Cancel</button>
			</div>
		</form>
	</jsp:attribute></t:mainTemplate>