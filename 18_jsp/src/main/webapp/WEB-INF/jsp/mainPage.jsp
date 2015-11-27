<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>
<head>
<title>Library</title>
</head>
<body>
	<p style="text-align: center;">
		<span style="font-size: 36px;">&nbsp;Welcome to the library
			control center</span>
	</p>

	<jsp:include page="/WEB-INF/jsp/Header.jsp"></jsp:include>

	<c:choose>
		<c:when test='${role =="admin"}'>
			<form action="admin_tool" enctype="text/plain" id="admin_tool"
				method="get" name="Admin tool" target="_self">
				<input name="AdminTool" type="submit" value="Admin tool"
					style="width: 95px" />&nbsp; <br>
			</form>
		</c:when>
		<c:otherwise>
			<br>
			<form action="getBooks" enctype="text/plain" method="get"
				name="all_books" target="_self">
				<input name="book_list" type="submit" value="Books"
					style="width: 95px" />&nbsp;
			</form>
			<br>
			<form action="getReaders" enctype="text/plain" method="get"
				name="getReaders" target="_self">
				<input name="getReaders" type="submit" value="Readers"
					style="width: 95px" />&nbsp;
			</form>
			<br>
			<form action="getLoans" enctype="text/plain" method="get"
				name="getLoans" target="_self">
				<input name="getLoans" type="submit" value="Journal"
					style="width: 95px" />&nbsp;
			</form>

		</c:otherwise>
	</c:choose>
	
	
</body>
</html>
