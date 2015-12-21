<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/style/style.css" var="cssUrl" />
<c:url value="/js/basic.js" var="jsbasicUrl" />
<c:set var="title"
	value="${action == 'add' ? 'Add New Customer' : 'Edit Customer'}" />
<html>
<head>
<title><c:out value="${title}" /></title>
<link href="${cssUrl}" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${jsbasicUrl}"></script>
</head>
<body>
	<div class="head1"></div>
	<div align="center">
		<p>
			<c:out value="${title}" />
		</p>
		<div class="head1"></div>
		<form action="<c:url value="/admin/customerPost.do"></c:url>"
			method="POST" class="focusableForm">
			<c:if test="${action=='edit'}">
				<div class="formElement">
					<p>Customer ID:</p>
					<input type="text" name="id" value="${customer.customerId}"
						readonly />
					<div class="hidden">
						Id of the customer
					</div>
				</div>
			</c:if>
			<div class="formElement">
				<p>First Name:</p>
				<input type="text" name="first_name"
					value="${customer.firstName == null ? '' : customer.firstName}" />
				<div class="hidden">
					First name of the customer
				</div>
			</div>
			<div class="formElement">
				<p>Last Name:</p>
				<input type="text" name="last_name"
					value="${customer.lastName == null ? '' : customer.lastName}" />
				<div class="hidden">
					Last name of the customer
				</div>
			</div>
			<div class="formElement">
			<p>Phone:</p>
			<input type="text" name="phone"
				value="${customer.phone == null ? '' : customer.phone}" />
			<div class="hidden">
				Phone of the customer
			</div>
			</div>
			<div class="formElement">
			<p>User Login:</p>
			<select name="user_id">
				<c:forEach var="item" items="${users}">
					<option value="${item.userId}"
						<c:if test="${customer.user.userId eq item.userId}"> selected</c:if>><c:out
							value="${item.userLogin}" /></option>
				</c:forEach>
			</select>
			</div>
			<div class="formElement">
			<input type="hidden" name="target" value="Customers" /> <input
				type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<input type="submit" value="Submit" class="input_add" />
			</div>
		</form>
	</div>
</body>
</html>