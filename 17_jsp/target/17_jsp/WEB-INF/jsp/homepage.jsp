<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general title="Home Page">
    <jsp:attribute name="content_area">
        <table border="1">
            <tr>
                <th>userLogin</th>
                <th>carOrderId</th>
                <th>carVIN</th>
                <th>carName</th>
                <th>status</th>
            </tr>
                <c:forEach var="userCarOrder" items="${userCarOrders}">
                    <tr>
                        <td><c:out value="${userCarOrder.userLogin}"/></td>
                        <td><c:out value="${userCarOrder.carOrderId}"/></td>
                        <td><c:out value="${userCarOrder.serialId}"/></td>
                        <td><c:out value="${userCarOrder.carModel}"/></td>
                        <td><c:out value="${userCarOrder.carOrderStatus}"/></td>
                    </tr>
                </c:forEach>
</table>
    </jsp:attribute>
</t:general>