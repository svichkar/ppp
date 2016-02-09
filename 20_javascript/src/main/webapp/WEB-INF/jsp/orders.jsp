<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general title="Orders">
    <jsp:attribute name="internal_content_area">
        <c:if test="${not empty coSet}">
            <table border="1">
                <caption>Orders</caption>
                    <tr>
                        <th>orderId</th>
                        <th>carName</th>
                        <th>carVIN</th>
                        <th>status</th>
                        <th>start_date</th>
                        <th>end_date</th>
                    </tr>
                    <c:forEach var="co" items="${coSet}" varStatus="status">
                        <tr>
                            <form action="orders" name="upDel" method="post">
                                <td><input title="This is car order id. READ ONLY" type="text" name="carOrderId" value="<c:out value="${co.carOrderId}"/>" readonly></td>
                                <input type="hidden" name="carId" value="<c:out value="${co.car.carId}"/>">
                                <td><input title="This is car full name. READ ONLY" name="carName" type="text" value ="<c:out value="${co.car.carType.getFullName()}"/>" readonly></td>
                                <td><input title="This is car serial VIN number. READ ONLY" name="carVIN" type="text" value ="<c:out value="${co.car.serialVIN}"/>" readonly></td>
                                <td><select title="Select status of order here" name="statuses" required>
                                    <option selected value="<c:out value="${co.carOrderStatus.carOrderStatusId}"/>"><c:out value="${co.carOrderStatus.carOrderStatusName}"/></option>
                                        <c:forEach var="status" items="${cosSet}">
                                            <c:if test="${status.carOrderStatusName != co.carOrderStatus.carOrderStatusName}">
                                                <option value="<c:out value="${status.carOrderStatusId}"/>"><c:out value="${status.carOrderStatusName}"/></option>
                                            </c:if>
                                        </c:forEach>
                                    </select></td>
                                <td><input title="This is start date of order. READ ONLY" name="start_date" type="text" name="startDate" value ="<c:out value="${co.startDate}"/>"readonly></td>
                                <td><input title="This is end date of order. READ ONLY" name="end_date" type="text" value ="<c:out value="${co.endDate}"/>"readonly></td>
                                <td><input type="submit" value="edit" name="submitButton"></td>
                                <td><input type="submit" value="delete" name="submitButton"></td>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            </form>
                        </tr>
                    </c:forEach>
            </table>
        </c:if>
            <table border="1">
                <caption>Car without order</caption>
                    <tr>
                        <th>carName</th>
                        <th>carVIN</th>
                    </tr>
                    <tr>
                        <form action="orders" name="add" method="post">
                            <td><select title="Select car here" name="cars" required>
                                    <c:forEach var="c" items="${cSet}">
                                        <option value="<c:out value="${c.carId}"/>"><c:out value="${c.carType.brand} ${c.carType.modelName} ${c.serialVIN}"/></option>
                                    </c:forEach>
                                </select></td>
                            <td><select title="Select status of order here" name="statuses2" required>
                                    <c:forEach var="status" items="${cosSet}">
                                        <option value="<c:out value="${status.carOrderStatusId}"/>"><c:out value="${status.carOrderStatusName}"/></option>
                                    </c:forEach>
                                </select></td>
                            <td><input type="submit" value="add" name="submitButton"></td>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        </form>
                    </tr>
            </table>
                        <table border="1">
                            <caption>Attach employee to order</caption>
                                <tr>
                                    <th>Employee</th>
                                    <th>Car in order</th>
                                </tr>
                                <tr>
                                    <form action="orders" name="reOrder" method="post">
                                        <input type="hidden" name="login" value="<c:out value="${param.login}"/>">
                                        <td><select title="Select employee here" name="employees" required>
                                                <c:forEach var="employee" items="${employeeSet}">
                                                    <option value="<c:out value="${employee.employeeId}"/>"><c:out value="${employee.firstName} ${employee.lastName}"/></option>
                                                </c:forEach>
                                            </select></td>
                                        <td><select title="Select car that already in order here" name="orders" required>
                                                <c:forEach var="order" items="${coSet}">
                                                    <option value="<c:out value="${order.carOrderId}"/>"><c:out value="${order.car.carType.getFullName()} ${order.car.serialVIN}"/></option>
                                                </c:forEach>
                                            </select></td>
                                        <td><input type="submit" value="reOrder" name="submitButton"></td>
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                    </form>
                                </tr>
                        </table>
            <c:out value="${msg}"/>
    </jsp:attribute>
</t:general>