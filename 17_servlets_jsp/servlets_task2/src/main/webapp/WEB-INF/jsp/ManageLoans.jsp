
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="loan book">
	<jsp:attribute name="content_area">
<h2>Loans</h2>

<form id="find" action=loans method="post">
				<input type="text" name="search input" />
				<input type=submit value="search" name="button">			
		
		
		<%-- need to pass id parameter by select--%>
				<select name="selectbook" multiple required>
						<option selected disabled value="">choose</option>
						<c:forEach var="book" items="${toBeloaned}">
							<option value="${book.bookId}">${book.name}</option>
						</c:forEach>
						<c:forEach var="book" items="${toBeloaned}">
							<input type="hidden" name="loaned" value="${book.bookId}">
						</c:forEach>	
				</select>  
				<input type=submit value="loan book" name="button">	
	
		
		<c:if test="${not empty reader}">
		<input type="hidden" name="current client" value="${reader.clientId}">
		<p>${reader}</p>
		<table>
			<tr>
				<th>book_name</th>
				<th>loan date</th>
				<th>action</th>
			</tr>
			
			<c:if test="${not empty toBeloaned}"> 
			</c:if>
			<c:forEach var="loan" items="${loans}">
			
				<tr>
				<form id="return" action=loans method="post">
					<td>${loan.book.name}</td>
					<td>${loan.loan.rentDate}</td>
					<input type="hidden" name="book returned" value="${loan.book.bookId}">
					<td><input type=submit value="book returned" name="button"></td>
				</form>
				</tr>
				
			</c:forEach>
			
		</table>
	
</c:if>

	</form>

		
		</jsp:attribute>
</t:general_template>