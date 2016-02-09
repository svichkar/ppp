<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
                <c:forEach var="Car" items="${CarOrders}">
                    <tr>
                        <td><input title="This is client-user login. READ ONLY" name="userLogin" type="text" value ="<c:out value="${Car.client.user.login}"/>" readonly></td>
                        <td><input title="This is car order id. READ ONLY" name="carOrderId" type="text" value ="<c:out value="${Car.carOrder.carOrderId}"/>" readonly></td>
                        <td><input title="This is car VIN number. READ ONLY" name="carVIN" type="text" value ="<c:out value="${Car.serialVIN}"/>" readonly></td>
                        <td><input title="This is full car name. READ ONLY" name="carName" type="text" value ="<c:out value="${Car.carType.getFullName()}"/>" readonly></td>
                        <td><input title="This is status of order. READ ONLY" name="status" type="text" value ="<c:out value="${Car.carOrder.carOrderStatus.carOrderStatusName}"/>" readonly></td>
                    </tr>
                </c:forEach>
</table>
    </jsp:attribute>
</t:general>