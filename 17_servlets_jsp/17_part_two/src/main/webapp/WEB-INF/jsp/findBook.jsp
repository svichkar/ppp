<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<t:template title="Main page">
    <jsp:attribute name="content_area">
    <table>

        <c:forEach var="book" items="${books}">
            <tr>
                <td><c:out value="${book.bookId}"/></td>
                <td><c:out value="${book.name}"/></td>
            </tr>
        </c:forEach>
    </table>
    </jsp:attribute>
    <jsp:attribute name="message_area">
        <c:if test="${not empty param.message}">
            <p align="center"><c:out value="${param.message}"/></p>
        </c:if>
    </jsp:attribute>
</t:template>
