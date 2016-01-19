
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
		
		<c:if test="${not empty toBeloaned}">
				<select name="selectbook" multiple required>
						<option selected disabled value="">choose</option>
						<c:forEach var="book" items="${toBeloaned}">
							<option value="${book.bookId}">${book.name}</option>
						</c:forEach>
						<c:forEach var="book" items="${toBeloaned}">
							<input type="hidden" name="loaned" value="${book.bookId}">
						</c:forEach>	
				</select>  
	</c:if>	
		<c:if test="${not empty reader}">
		<input type="hidden" name="current client" value="${reader.clientId}">
		<p>${reader}</p>
		<table>
			<tr>
				<th>book_name</th>
				<th>loan date</th>
				<th>check if book is returned</th>
			</tr>			
			<c:forEach var="loan" items="${loans}">			
				<tr>
					<td>${loan.book.name}</td>
					<td>${loan.loan.rentDate}</td>
					<td><input type="checkbox" name="book returned" value="${loan.loan.rentId}"/></td>
				</tr>				
			</c:forEach>
			
		</table>
	<input type=submit value="submit changes" name="button">
</c:if>
	</form>		
		</jsp:attribute>
</t:general_template>