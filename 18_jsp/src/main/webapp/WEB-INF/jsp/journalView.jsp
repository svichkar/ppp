<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>
<head>
<title>Library</title>
</head>
<body>
	<p style="text-align: center;">
		<span style="font-size: 36px;">&nbsp;Journal</span>
	</p>

	<jsp:include page="/WEB-INF/jsp/Header.jsp"></jsp:include>

	<form action="addNewRecrd" method=post>
		<input type="submit" value="Add new record"
			style="background-color: green; margin-left: auto; margin-right: auto; display: block;">
	</form>
	<table align="center" border="1" cellpadding="1" cellspacing="1"
		style="width: 500px;">
		<caption>
			<font size="6">Books in use</font>
		</caption>
		<tbody>
			<tr>

				<th>Book name</th>
				<th>Author</th>
				<th>Category</th>
				<th>Inventory number</th>
				<th>Reader name</th>
				<th>Start date</th>
				<th>End date</th>
			<tr>
			<tr>
			</tr>
			<c:forEach items="${journal}" var="record">
				<tr>
					<form action="deleteJournalRecord" method="get">
						<td><input maxlength="20" name="bookName" type="text"
							value="${record.bookName}" readonly="readonly" /></td>
						<td><input maxlength="20" name="bookAuthor" type="text"
							value="${record.bookAuthor}" readonly="readonly" /></td>
						<td><input maxlength="20" name="bookCategory" type="text"
							value="${record.bookCategory}" readonly="readonly" /></td>
						<td><input maxlength="8" name="bookInventoryNumber"
							type="text" value="${record.bookInventoryNumber}"
							readonly="readonly" /></td>
						<td><input maxlength="20" name="readerName" type="text"
							value="${record.readerName}" readonly="readonly" /></td>
						<td><input maxlength="20" name="start_date" type="text"
							value="${record.start_date}" readonly="readonly" /></td>
						<td><input maxlength="20" name="end_date" type="text"
							value="${record.end_date}" readonly="readonly" /></td>
						<td><input type="submit" value="Delete" style="width: 100%"></td>
						<input name="id" type="hidden" value="${record.id}" />

					</form>
				</tr>

			</c:forEach>

			</tr>
		</tbody>
	</table>


</body>
</html>