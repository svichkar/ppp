<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Results</title>
</head>
<body>
	<p style="text-align: center;">
		<span style="font-size: 36px;">&nbsp;Search Results</span>
	</p>

	<jsp:include page="/WEB-INF/jsp/Header.jsp"></jsp:include>

	<table align="center" border="1" cellpadding="1" cellspacing="1"
		style="width: 500px;">
		<caption>
			<font size="6">Books</font>
		</caption>
		<tbody>
			<tr>
				<th>Name</th>
				<th>Author</th>
				<th>Publisher</th>
				<th>Category</th>
			<tr>
			<tr>
			</tr>
			<c:forEach items="${books}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.author}</td>
					<td>${book.publisher}</td>
					<td>${book.category_name}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<br>
	<button onclick="goBack()">Go Back</button>

	<script>
		function goBack() {
			window.history.back();
		}
	</script>
	
</body>
</html>