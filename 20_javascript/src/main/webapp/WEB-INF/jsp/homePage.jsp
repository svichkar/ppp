<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<t:template title="Main page">
    <jsp:attribute name="content_area">
        <table border="1" class="table">
            <thead>
                <tr><th>ticketId</th><th>clientName</th><th>clientPhone</th><th>clientEmail</th><th>bookName</th><th>expiredDate</th></tr>
            </thead>
            <tbody>
            <c:forEach var="ticket" items="${overdueTickets}">
                <tr>
                    <td><c:out value="${ticket.ticketId}"/></td>
                    <td><c:out value="${ticket.client.clientFullName()}"/></td>
                    <td><c:out value="${ticket.client.clientPhone}"/></td>
                    <td><c:out value="${ticket.client.clientEmail}"/></td>
                    <td><c:out value="${ticket.book.bookName}"/></td>
                    <td><c:out value="${ticket.expiredDate}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </jsp:attribute>
    <jsp:attribute name="message_area">
        <c:if test="${not empty msg}">
            <p align="center"><c:out value="${msg}"/></p>
        </c:if>
    </jsp:attribute>
</t:template>
