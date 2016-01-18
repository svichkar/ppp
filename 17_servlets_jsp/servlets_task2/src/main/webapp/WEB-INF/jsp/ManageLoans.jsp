
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
		</form>
		
	<%--	<form id="find" action=loans method="post">
				<select name="selectbook" multiple required>
						<option selected disabled value="">choose</option>
						<c:forEach var="book" items="${toBeloaned}">
							<option value="${book.name}">${book.name}</option>
						</c:forEach>
				</select>  --%>
		
		<form id="find" action=loans method="post">
		<c:if test="${not empty reader}">
		<p>${reader}</p>
		
		<table>
			<tr>
				<th>book_name</th>
				<th>loan date</th>
				<th>action</th>
			</tr>
			
			<c:if test="${not empty toBeloaned}"> 
			<tr>
			<td><select name="selectbook" multiple required>
						<option selected disabled value="">choose</option>
						<c:forEach var="book" items="${toBeloaned}">
							<option value="${book.name}">${book.name}</option>
						</c:forEach>
				</select></td>
				<td>today</td>
				<td><input type=submit value="add books" name="button"></td>
			</tr>
			</c:if>
			<c:forEach var="loan" items="${loans}">
				<tr>
					<td>${loan.book.name}</td>
					<td>${loan.loan.rentDate}</td>
					<td><input type=submit value="book returned" name="button"></td>
				</tr>
			</c:forEach>
			
		</table>
	
</c:if>
	</form>
<!--<table>
		<tr>
			<td>Reader name</td>
			<td>book in loan</td>
			<td>loan date</td>
			<td>return date</td>
		</tr>
						<c:forEach var="loan" items="${loans}">
						<tr>
							<td>${loan.reader}</td>
							<td>${loan.book.name}</td>
							<td>${fn:escapeXml(loan.loan.rentDate)}</td>
							<td>${loan.loan.returnDate}</td>
							</tr>
						</c:forEach>			
		</table> -->
		
		</jsp:attribute>
</t:general_template>