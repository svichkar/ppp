
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="loan book">
	<jsp:attribute name="content_area">
<h2>Loans</h2>

<form id="find" action=readersearch method="post">
				<input type="text" name="search input" placeholder="Reader name" /> <%-- add search all readers / by name / combinations  --%>
				<input type=submit value="search" name="button">								

		<c:if test="${not empty toBeloaned}">
		<br/>
		<p>The books were chosen to loan:</p>
						<c:forEach var="book" items="${toBeloaned}">
							<p>${book.name}</p>
							<input type="hidden" name="loaned" value="${book.bookId}">
						</c:forEach>				
	</c:if>	
</form>

		<c:if test="${not empty readers}"> <%-- if readers list are not empty --%>
		<input type="hidden" name="current client" value="${reader.clientId}">
		<table>
			<tr>
				<th>reader_name</th>
				<th>email</th>
				<th>phone</th>
				<th>action</th>
			</tr>	
					
			<c:forEach var="reader" items="${readers}">				
				<tr>
					<td>${reader}</td>
					<td>${reader.email}</td>
					<td>${reader.phone}</td>
					<td><form id="${reader.clientId}" action=loansmanage method="post">
								<input type="hidden" name="current client"
									value="${reader.clientId}" /><input type=submit value="manage"
									name="button">
							<c:if test="${not empty toBeloaned}">
						<c:forEach var="book" items="${toBeloaned}">
							<input type="hidden" name="loaned" value="${book.bookId}"
											form="${reader.clientId}" />
						</c:forEach>		
							</c:if>	
							</form></td>
				</tr>										
			</c:forEach>
			
		</table>
</c:if>
			
		</jsp:attribute>
</t:general_template>