
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="loan book">
	<jsp:attribute name="content_area">
<h2>Outdated Loans</h2>
<table>
		<tr>
			<th>Reader name</th>
			<th>book in loan</th>
			<th>loan date</th>
			<th>overdue</th>
		</tr>
			
				
						<c:forEach var="loan" items="${loans}">
						<tr>
							<td>${loan.reader}</td>
							<td>${loan.book.name}</td>
							<td>${loan.loan.rentDate}</td>
							<td>${loan.expirationState}</td>
							</tr>
						</c:forEach>

			
		</table>
		
		</jsp:attribute>
</t:general_template>