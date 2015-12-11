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
	<jsp:include page="/WEB-INF/jspPages/terms/viewTerms.jsp">
		<jsp:param name="currentUser" value="${currentUser}" />
		<jsp:param name="terms" value="${terms}" />
	</jsp:include>
	<div align="center">
		<br> 
		<br>
		<c:if test="${createOrEdit == 'create'}">
		<form action="create_term" method="post">
			<label>Term Alias:</label> <br> 
			<input type="text" name="termAlias" required> 
			<br><br>
			<input type="submit" name="createNewTerm" value="Create term">
			<input type="hidden" name="currentUser" value="${currentUser}">
			<p style="color:red">${message}</p>
		</form>
		</c:if>
		<c:if test="${createOrEdit == 'edit'}">
		<form action="edit_term" method="post">
			<label>Term Alias:</label> <br> 
			<input type="text" name="termAlias" value="${termToEdit.termAlias}" required> 
			<br> 
			<br>
			<input type="hidden" name="termId" value="${termToEdit.termId}">
			<input type="submit" name="saveTermUpdates" value="Save">
			<input type="hidden" name="currentUser" value="${currentUser}">
			<p style="color:red">${message}</p>
		</form>
		</c:if>
	</div>
</body>
</html>