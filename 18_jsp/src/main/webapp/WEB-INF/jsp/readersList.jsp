<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Readers List</title>
</head>
<body>
	<p style="text-align: center;">
		<span style="font-size: 36px;">&nbsp;Readers List</span>
	</p>

	<jsp:include page="/WEB-INF/jsp/Header.jsp"></jsp:include>
${error}
	<table align="center" border="1" cellpadding="1" cellspacing="1"
		style="width: 500px;">
		<caption>
			<font size="6">Readers</font>
		</caption>
		<tbody>
			<tr>
				<th>Delete?</th>
				<th>Name</th>
				<th>Adress</th>
				
			<tr>
			<tr>
			</tr>
			<c:forEach items="${readers}" var="reader">
					<tr>
					<form action="updateReader" method="post">
						<td><input type="checkbox" name="checkboxstate" /></td>
						<td><input maxlength="20" name="readerName" type="text"
							value="${reader.name}" /></td>
						<td><input maxlength="30" name="readerAdress" type="text"
							value="${reader.adress}" /></td>
						
						<td><input type="submit" value="Update" style="width: 100%"></td>
						<input name="readerId" type="hidden" value="${reader.id}" />

					</form>
				</tr>
			</c:forEach>
			<form action="addNewReader" method="post">
				<td></td>
				<td><input maxlength="20" name="readerName" type="text" value="" /></td>
				<td><input maxlength="20" name="readerAdress" type="text" value="" /></td>
				<td><input type="submit" value="ADD NEW" style="width: 100%"></td>
			</form>
			</tr>
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