
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="loan book">
	<jsp:attribute name="content_area">
		<h2>Loans</h2>
		<form id="find" action=loansmanage method="post">				
			<c:if test="${not empty toBeloaned}">
				<br />
				<p>Chose book(s) to add to readers loan list</p>
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
			<input type="hidden" name="current client" value="${reader.clientId}">
			<p>${reader}</p>
			<table class="present">
				<tr>
					<th class="present">book_name</th>
					<th class="present">loan date</th>
					<th class="present">check if book is returned</th>
				</tr>			
				<c:forEach var="loan" items="${loans}">			
					<tr>
						<td class="present">${loan.book.name}</td>
						<td class="present">${loan.loan.rentDate}</td>
						<td class="present">
							<input type="checkbox" name="book returned" value="${loan.loan.rentId}" />
						</td>
					</tr>				
				</c:forEach>		
			</table>
			<input type=submit value="submit changes" name="button">
		</form>		
	</jsp:attribute>
</t:general_template>