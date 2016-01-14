<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:mainTemplate title="Edit Customer Page">
	<jsp:attribute name="content_area">
		<div align="center">
			<h1>
				<c:out value="Edit existing customer" />
			</h1>
		</div>
		
		<form id="customer" action="<c:url value="/admin/updateCustomer"></c:url>" method="post">
			<input hidden="hidden" name="customer_id" value="${customer.customerId}" />
			<input hidden="hidden" name="user_id" value="${customer.user.userId}" />
			<table>
				<tr>
					<th class="nav-menu" colspan="2">
						<h3>Edit general info</h3>
					</th>
				</tr>
				<tr>
					<td class="nav-menu">
						<b>Customer ID</b>
					</td>
					<td class="nav-menu">
						<input disabled="disabled" value="${customer.customerId}" />
					</td>
				</tr>
				<tr>
					<td class="nav-menu">
						<b>Last Name</b>
					</td>
					<td class="nav-menu">
						<input name="last_name" value="${customer.lastName}" />
					</td>
				</tr>
				<tr>
					<td class="nav-menu">
						<b>First name</b>
					</td>
					<td class="nav-menu">
						<input name="first_name" value="${customer.firstName}" />
					</td>
				</tr>
				<tr>
					<td class="nav-menu">
						<b>Phone number</b>
					</td>
					<td class="nav-menu">
						<input name="phone" value="${customer.phone}" />
					</td>
				</tr>
				<tr>
					<td class="nav-menu">
						<b>User login</b>
					</td>
					<td class="nav-menu">
						<input name="user_login" value="${customer.user.userLogin}" />
					</td>
				</tr>
				<tr>
					<td class="nav-menu">
						<b>User password</b>
					</td>
					<td class="nav-menu">
						<input type="password" name="user_password" value="${customer.user.userPassword}" />
					</td>
				</tr>
				<tr>
					<td class="nav-menu" colspan="2">
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