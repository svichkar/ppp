<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>
<head>
<title>Library</title>
</head>
<body>
	<p style="text-align: center;">
		<span style="font-size: 36px;">&nbsp;Admin tool</span>
	</p>

	<jsp:include page="/WEB-INF/jsp/Header.jsp"></jsp:include>

	<table align="center" border="1" cellpadding="1" cellspacing="1"
		style="width: 500px;">
		<caption>
			<font size="6">Users</font>
		</caption>
		<tbody>
			<tr>
				<th>Delete?</th>
				<th>LOGIN</th>
				<th>PASSWORD</th>
				<th>ROLE</th>
			<tr>
			<tr>
			</tr>
			<c:forEach items="${users}" var="user">
				<tr>
					<form action="updateUser" method="post">
						<td><input type="checkbox" name="checkboxstate" /></td>
						<td><input maxlength="20" name="login" type="text"
							value="${user.login}" /></td>
						<td><input maxlength="20" name="password" type="text"
							value="${user.password}" /></td>
						<c:choose>
							<c:when test='${user.role =="admin"}'>
								<td><select name="select"><option value="admin">admin</option>
										<option value="user">user</option></select></td>
							</c:when>
							<c:otherwise>
								<td><select name="select"><option value="user">user</option>
										<option value="admin">admin</option></select></td>

							</c:otherwise>
						</c:choose>
						<td><input type="submit" value="Update" style="width: 100%"></td>
						<input name="userid" type="hidden" value="${user.id}" />

					</form>
				</tr>

			</c:forEach>
			<form action="addUser" method="post">
				<td></td>
				<td><input maxlength="20" name="login" type="text" value="" /></td>
				<td><input maxlength="20" name="password" type="text" value="" /></td>
				<td><select name="select"><option value="user">user</option>
						<option value="admin">admin</option></select></td>
				<td><input type="submit" value="ADD NEW" style="width: 100%"></td>
			</form>
			</tr>
		</tbody>
	</table>
	<br>
	<br>
	<table align="center" border="1" cellpadding="1" cellspacing="1"
		style="width: 500px;">
		<caption>
			<font size="6">Book categories</font>
		</caption>
		<tbody>
			<tr>
				<th>ID</th>
				<th>Category</th>
			<tr>
			<tr>
			</tr>
			<c:forEach items="${books}" var="book">
				<tr>
					<form action="updateCategory" method="post">

						<td><input maxlength="10" name="Id" type="text"
							value="${book.id}" readonly="readonly" /></td>
						<td><input maxlength="25" name="Category" type="text"
							value="${book.name}" /></td>
						<td><input type="submit" value="Update" style="width: 100%"></td>

					</form>
				</tr>
			</c:forEach>
			<form action="addCategory" method="post">
				<td></td>
				<td><input maxlength="20" name="Category" type="text" value="" /></td>

				<td><input type="submit" value="ADD NEW" style="width: 100%"></td>
			</form>
		</tbody>
	</table>
	<br>
	<button onclick="goBack()">Go Back</button>

	<script>
		function goBack() {
			window.history.back();
		}
	</script>
	<p>&nbsp;</p>
	
</body>
</html>
