<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general title="Home Page">
    <jsp:attribute name="content_area">
        <table border="1">
        <caption>Current car orders</caption>
            <tr>
                <th>userLogin</th>
                <th>carOrderId</th>
                <th>carVIN</th>
                <th>carName</th>
                <th>status</th>
            </tr>
                <c:forEach var="userCarOrder" items="${userCarOrders}">
                    <tr>
                        <td><input type="text" value ="<c:out value="${userCarOrder.userLogin}"/>" readonly></td>
                        <td><input type="text" value ="<c:out value="${userCarOrder.carOrderId}"/>" readonly></td>
                        <td><input type="text" value ="<c:out value="${userCarOrder.serialId}"/>" readonly></td>
                        <td><input type="text" value ="<c:out value="${userCarOrder.carModel}"/>" readonly></td>
                        <td><input type="text" value ="<c:out value="${userCarOrder.carOrderStatus}"/>" readonly></td>
                    </tr>
                </c:forEach>
</table>
    </jsp:attribute>
</t:general>