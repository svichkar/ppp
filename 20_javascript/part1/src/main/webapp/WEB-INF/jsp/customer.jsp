<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template>
	<jsp:attribute name="head_area">
	</jsp:attribute>
	<jsp:attribute name="content_area">
		<table>
		<form action="customerEdit" name="formForValidation" method="POST">
		<tr>
			<td>Login:</td>
			<td>
			<select name="user_id">
					<option value=""/>
					<c:forEach var="item" items="${users}">
						<option value="${item.userId}"
										<c:if test="${item.userId eq customer.user.userId}">selected</c:if>><c:out value="${item.username}"/>
						</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>First name:</td>
			<td>
				<input type="text" name="f_name" value="${customer.f_name}" />
			</td>
		</tr>
		<tr>
			<td>Last name:</td>
			<td>
				<input type="text" name="l_name" value="${customer.l_name}" />
			</td>
		</tr>
		<tr>
			<td>Phone:</td>
			<td>
				<input type="text" name="phone" value="${customer.phone}" />
			</td>
		</tr>
		<tr>
			<td colspan=2 align="right">
				<input type="hidden" name="action" value="Save">
				<input type="hidden" name="customer_id" value="${customer.customerId}">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<input type="submit" value="Save">
			</td>
		</tr>
		</form>
		</table>
		<div id="hint" />
	</jsp:attribute>
</t:general_template>