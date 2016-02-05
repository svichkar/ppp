<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template>
	<jsp:attribute name="head_area">
	</jsp:attribute>
	<jsp:attribute name="content_area">
		<table>
		<form action="carEdit" method="POST">
		<tr>
			<td>Model:</td>
			<td>
				<input type="text" name="model" value="${car.model}" />
			</td>
		</tr>
		<tr>
			<td>Vin:</td>
			<td>
				<input type="text" name="vin" value="${car.vin}" />
			</td>
		</tr>
		<tr>
			<td>Description:</td>
			<td>
				<input type="text" name="description" value="${car.description}" />
			</td>
		</tr>
		<tr>
			<td>Customer:</td>
			<td>
				<select name="customer_id">
					<c:forEach var="item" items="${customers}">
						<option value="${item.customerId}"
										<c:if test="${item.customerId eq car.customer.customerId}">selected</c:if>>
										<c:out value="${item.f_name} ${item.l_name}"/>
						</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan=2 align="right">
				<input type="hidden" name="action" value="Save">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<input type="hidden" name="car_id" value="${car.carId}">
				<input type="submit" value="Save">
			</td>
		</tr>
		</form>
		</table>
	</jsp:attribute>
</t:general_template>