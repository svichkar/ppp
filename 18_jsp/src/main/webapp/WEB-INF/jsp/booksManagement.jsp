<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!doctype html>
<html>
<head>
<title>Library</title>
</head>
<body>
	<p style="text-align: center;">
		<span style="font-size: 36px;">&nbsp;Books management page</span>
	</p>

	<jsp:include page="/WEB-INF/jsp/Header.jsp"></jsp:include>

	${error}
	<table align="center" border="1" cellpadding="1" cellspacing="1"
		style="width: 500px;">
		<caption>
			<font size="6">Books list</font>
		</caption>
		<tbody>
			<tr>
				<th>Delete?</th>
				<th>Name</th>
				<th>Author</th>
				<th>Publisher</th>
				<th>Category</th>
			<tr>
			<tr>
			</tr>
			<c:forEach items="${books}" var="book">
				<tr>
					<form action="updateBook" method="post">
						<td><input type="checkbox" name="checkboxstate" /></td>
						<td><input maxlength="20" name="bookName" type="text"
							value="${book.name}" /></td>
						<td><input maxlength="20" name="bookAuthor" type="text"
							value="${book.author}" /></td>
						<td><input maxlength="20" name="bookPublisher" type="text"
							value="${book.publisher}" /></td>
						<td><input maxlength="20" name="bookCategory" type="text"
							value="${book.category_name}" readonly="readonly" /></td>

						<td><input type="submit" value="Update" style="width: 100%"></td>
						<input name="bookId" type="hidden" value="${book.id}" />

					</form>
				</tr>

			</c:forEach>

			<form action="addBook" method="post">
				<td></td>
				<td><input maxlength="20" name="bookName" type="text" value="" /></td>
				<td><input maxlength="20" name="bookAuthor" type="text"
					value="" /></td>
				<td><input maxlength="20" name="bookPublisher" type="text"
					value="" /></td>
				<td><select name="select">
						<c:forEach items="${categories}" var="category">
							<option value="${category.name}">${category.name}</option>
						</c:forEach>

				</select></td>
				<td><input type="submit" value="ADD NEW" style="width: 100%"></td>
			</form>
			</tr>
		</tbody>
	</table>
	<br>
	<button onclick="goBack()">Go Back</button>

	<script>
		function goBack() {
			window.history.back();
		}
	</script>


</body>
</html>