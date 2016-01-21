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
                <c:forEach var="CarOrder" items="${CarOrders}">
                    <tr>
                        <td><input type="text" value ="<c:out value="${CarOrder.car.client.user.login}"/>" readonly></td>
                        <td><input type="text" value ="<c:out value="${CarOrder.carOrderId}"/>" readonly></td>
                        <td><input type="text" value ="<c:out value="${CarOrder.car.serialVIN}"/>" readonly></td>
                        <td><input type="text" value ="<c:out value="${CarOrder.car.carType.getFullName()}"/>" readonly></td>
                        <td><input type="text" value ="<c:out value="${CarOrder.carOrderStatus.carOrderStatusName}"/>" readonly></td>
                    </tr>
                </c:forEach>
</table>
    </jsp:attribute>
</t:general>