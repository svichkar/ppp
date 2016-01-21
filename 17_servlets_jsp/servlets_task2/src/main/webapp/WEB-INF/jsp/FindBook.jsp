
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="find book">
	<jsp:attribute name="content_area">
<h2 class="fixed">Find book</h2>
<form id="create" action="findbook" method="post">
				<select name="search criteria" required>
						<option selected value="all">all</option>
						<option value="name">name</option>
						<option value="author">author</option>
						<option value="category">category</option>
				</select>
				<input type="text" name="search input" />
				<input type=submit value="search" name="button">			
		</form>
	<jsp:useBean id="book" class="com.nixsolutions.model.BookBean" />
		<c:if test="${not empty allBooks}">
		<form id="to loan" action=readersearch method="post">
		<input type="submit" value="Submit loans">
		<table class="present">
			<tr>
				<th class = "present">book_id</th>
				<th class = "present">book_name</th>
				<th class = "present">cell</th>
				<th class = "present">category</th>
				<th class = "present">author</th>
				<th class = "present">count</th>
				<th class = "present">loan</th>
			</tr>
			
			<c:forEach var="book" items="${allBooks}">
				<tr>
					<td class = "present">${book.book.bookId}</td>
					<td class = "present">${book.book.name}</td>
					<td class = "present">${book.cell.name}</td>
					<td class = "present">${book.category.name}</td>
					<td class = "present">${book.authors}</td>
					<td class = "present">${book.book.count}</td>
					<c:choose>
											<c:when test="${book.book.count == 0}">
												<td class = "present"><input type="checkbox" name="loaned"
										value="${book.book.bookId}" disabled /></td>
											</c:when>
											<c:otherwise>
												<td class = "present"><input type="checkbox" name="loaned"
										value="${book.book.bookId}" /></td>
											</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
		</table>
		</form>
</c:if>
		</jsp:attribute>
</t:general_template>