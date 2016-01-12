
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="admin page">
	<jsp:attribute name="content_area">
<h2>Find book</h2>
<form id="create" action="findbook" method="post">
				<select name="search criteria" required>
						<option selected disabled value="">search by</option>
						<option value="all">all</option>
						<option value="name">name</option>
						<option value="author">author</option>
						<option value="category">category</option>
				</select>
				<input type="text" name="search criteria" required />
				<input type=submit value="search" name="button">			
		</form>
	<c:if test="results not null">
		<table>
			<tr>
				<td>book_id</td>
				<td>book_name</td>
				<td>cell</td>
				<td>category</td>
			</tr>
			<c:forEach var="user" items="${users}">
				<tr>
					<td><input type="text" name="userid" value="${user.userId}"
							readonly /></td>
					<td><input type="text" name="username"
							value="${user.userName}" /></td>
					<td><input type="text" name="password"
							value="${user.userPassword}" /></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
		</jsp:attribute>
</t:general_template>