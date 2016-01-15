
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
				<input type="text" name="search input" />
				<input type=submit value="search" name="button">			
		</form>
	<jsp:useBean id="book" class="com.nixsolutions.model.BookBean" />
		<c:if test="${not empty allBooks}">
		<table>
			<tr>
				<td>book_id</td>
				<td>book_name</td>
				<td>cell</td>
				<td>category</td>
				<td>author</td>
			</tr>
			
			<c:forEach var="book" items="${allBooks}">
				<tr>
					<td>${book.book.bookId}</td>
					<td>${book.book.name}</td>
					<td>${book.cell.name}</td>
					<td>${book.category.name}</td>
					<td>${book.authors}</td>
				</tr>
			</c:forEach>
		</table>
</c:if>
		</jsp:attribute>
</t:general_template>