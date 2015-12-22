<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:mainTemplate title="Customer Page">
	<jsp:attribute name="content_area">
		<div align="center">
			<h1>
				<c:out value="Edit existing customer" />
			</h1>
		</div>
		<form id="customer" action="updateCustomer" method="post">
				<input hidden="hidden" name="customer_id" value="${customer.customer_id}" />
				<input hidden="hidden" name="user_id" value="${customer.user.user_id}" />
				<input hidden="hidden" name="action" value="edit" />
<table>
				<tr>
					<th class="nav-menu" colspan="2"><h3>Edit general info</h3></th>
				</tr>
				<tr>
					<td class="nav-menu"><b>customer ID</b></td>
					<td class="nav-menu"><input disabled="disabled" value="${customer.customer_id}" />
					</td>
				</tr>
				<tr>
					<td class="nav-menu"><b>Last Name</b></td>
					<td class="nav-menu"><input name="last_name" value="${customer.last_name}" /></td>
				</tr>
				<tr>
					<td class="nav-menu"><b>First name</b></td>
					<td class="nav-menu"><input name="first_name" value="${customer.first_name}" /></td>
				</tr>
				<tr>
					<td class="nav-menu"><b>Phone number</b></td>
					<td class="nav-menu"><input name="phone" value="${customer.phone}" /></td>
				</tr>
				<tr>
					<td class="nav-menu"><b>User login</b></td>
					<td class="nav-menu"><input name="user_login" value="${customer.user.user_login}" /></td>
				</tr>
				<tr>
					<td class="nav-menu"><b>User password</b></td>
					<td class="nav-menu"><input name="user_password" value="${customer.user.user_password}" /></td>
				</tr>
				<tr>
				<td class="nav-menu" colspan="2">
				<button type="submit" class="btn btn-primary btn-block btn-large" name="target" value="updateCustomer">Update</button>
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