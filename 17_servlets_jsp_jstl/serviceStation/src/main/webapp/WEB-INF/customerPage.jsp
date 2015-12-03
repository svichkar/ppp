<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:mainTemplate title="Customers Page">
	<jsp:attribute name="content_area">
		<div align="center">
		<h1>All Customers</h1>
	</div>
<table class="data">
	<tr>
		<th># Customer</th>
		<th>Full name</th>
		<th>Phone number</th>
		<th>Login</th>
		<th>Password</th>
		<th>Action</th>
	</tr>
	<c:forEach var="customer" items="${customerList}">
		<tr>
			<td><c:out value="${customer.customer_id}" /></td>
			<td><c:out value="${customer.fullName}" /></td>
			<td><c:out value="${customer.phoneNumber}" /></td>
			<td><c:out value="${customer.userName}" /></td>
			<td><c:out value="${customer.userPassword}" /></td>
			<td>
				<form method="post">
					<input type="hidden" name="customer_id" value="${customer.customer_id}"></input>
					<input hidden="hidden" name="fieldName" value="customer"/>
					<input type="submit" name="action" formaction="deleteField" value="Delete"/>
					<input type="submit" name="target" formaction="updateField" value="Edit Customer"/>
				</form>
			</td>
		</tr>
	</c:forEach>
</table>
<form action="addNew" method="get"><div style="width: 50%; margin: 0 auto; text-align: center;">
			<input hidden="hidden" name="homePage" value ="customerPage">
			<button type="submit" class="btn btn-primary btn-block btn-large"
				name="target" value="customer">Add new customer</button></div>
		</form>

		</jsp:attribute>
</t:mainTemplate>
