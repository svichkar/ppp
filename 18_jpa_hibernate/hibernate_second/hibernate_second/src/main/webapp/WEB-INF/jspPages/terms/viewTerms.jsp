<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
</head>
<body>
	<jsp:include page="/WEB-INF/jspPages/homePage.jsp">
		<jsp:param name="currentUser" value="${currentUser}" />
	</jsp:include>
	<br>
	<br>
	<br>
	<div align="center">
		<table border="1">
			<caption>Terms:</caption>
			<tbody>
				<tr>
					<th>ID</th>
					<th>Term Alias</th>
					<th>Action</th>
				</tr>
				<c:forEach items="${terms}" var="term">
					<tr>
						<td>${term.termId}</td>
						<td>${term.termAlias}</td>
						<td>
							<form>
								<input type="hidden" name="currentUser" value="${currentUser}">
								<button formmethod="get" formaction="edit_term" name="editTerm"
									value="${term.termId}" style="width: 100%">Edit</button>
								<br>
								<button formmethod="post" formaction="delete_term" name="delete"
									value="${term.termId}" style="width: 100%">Delete</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>