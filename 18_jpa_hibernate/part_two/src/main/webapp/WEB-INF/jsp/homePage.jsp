<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<t:template title="Main page">
    <jsp:attribute name="content_area">
        <table border="1">
            <thead>
                <tr><th>ticketId</th><th>clientName</th><th>clientPhone</th><th>clientEmail</th><th>bookName</th><th>expiredDate</th></tr>
            </thead>
            <tbody>
            <c:forEach var="book" items="${overdueBooks}">
                <tr>
                    <td><c:out value="${book.ticket.ticketId}"/></td>
                    <td><c:out value="${book.client.firstName} "/><c:out value="${book.client.lastName}"/></td>
                    <td><c:out value="${book.client.phone}"/></td>
                    <td><c:out value="${book.client.email}"/></td>
                    <td><c:out value="${book.book.name}"/></td>
                    <td><c:out value="${book.ticket.expiredDate}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </jsp:attribute>
    <jsp:attribute name="message_area">
        <c:if test="${not empty param.message}">
            <p align="center"><c:out value="${param.message}"/></p>
        </c:if>
    </jsp:attribute>
</t:template>
