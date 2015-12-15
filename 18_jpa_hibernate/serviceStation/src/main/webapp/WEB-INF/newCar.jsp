<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:mainTemplate title="new Car Page">
	<jsp:attribute name="content_area">
		<div align="center">
		<h1>
						<c:out value="Add new car" />
			</h1>
	</div>
<table>
<form id="newCar" action="addNew" method="post">
<tr>
					<td class="nav-menu"><p>Select customer</p></td>
					<td class="nav-menu"><select name="customer"><c:forEach
								var="customer" items="${customers}">
								<option value="${customer.customer_id}"><c:out
										value="${customer.last_name}" /> <c:out
										value="${customer.first_name}" /></option>
							</c:forEach></select></td>
</tr>
<tr> 
					<td class="nav-menu"><p>Enter registration number</p></td>	
					<td class="nav-menu" colspan="2"><input type="text" name="regNumber"
					placeholder="registration number" form="newCar" required ></input></td>
</tr>
<tr> 
					<td class="nav-menu"><p>Enter car model</p></td>	
					<td class="nav-menu" colspan="2"><input type="text" name="carModel"
					placeholder="car model" form="newCar" required ></input></td>
</tr>
<tr> 
					<td class="nav-menu" ><p>Enter VIN number</p></td>	
					<td class="nav-menu" colspan="2"><input type="text" name="vinNumber"
					placeholder="VIN number" form="newCar" maxlength="17" required ></input></td>
</tr>
			<tr>				<td class="nav-menu" colspan="3">
			<input hidden="hidden" name="homePage" value="${homePage }">
			<input type="submit" class="btn btn-primary btn-block btn-large"
					name="target" value="Create Car" form="newCar"></input></td>			</tr>
			
	</form>
</table>
				<br />
		<br />
<form action="adminPage" method="get"><div style="width: 50%; margin: 0 auto; text-align: center;">
			<button type="submit" class="btn btn-primary btn-block btn-large"
				name="target" value="car" >Cancel</button></div>
		</form>

		</jsp:attribute>
</t:mainTemplate>
