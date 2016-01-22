
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="welocme">
	<jsp:attribute name="content_area">
<h2>Outdated Loans</h2>
<table class="present">
		<tr>
			<th class="present">Reader name</th>
			<th class="present">book in loan</th>
			<th class="present">loan date</th>
			<th class="present">overdue</th>
		</tr>
			
				
						<c:forEach var="loan" items="${loans}">
						<tr>
							<td class="present">${loan.reader}</td>
							<td class="present">${loan.book.name}</td>
							<td class="present">${loan.loan.rentDate}</td>
							<td class="present">${loan.expirationState}</td>
							</tr>
						</c:forEach>

			
		</table>
		
		</jsp:attribute>
</t:general_template>