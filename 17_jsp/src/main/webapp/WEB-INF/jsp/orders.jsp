<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general title="Orders">
    <jsp:attribute name="internal_content_area">
        <c:if test="${not empty ucobList}">
            <table border="1">
                <caption>Orders</caption>
                    <tr>
                        <th>carName</th>
                        <th>carVIN</th>
                        <th>status</th>
                        <th>start_date</th>
                        <th>end_date</th>
                    </tr>
                    <c:forEach var="ucob" items="${ucobList}" varStatus="status">
                        <tr>
                            <form action="orders" name="upDel" method="post">
                                <input type="hidden" name="login" value="<c:out value="${param.login}"/>">
                                <input type="hidden" name="carOrderId" value="<c:out value="${ucob.carOrderId}"/>">
                                <input type="hidden" name="carId" value="<c:out value="${ucob.carId}"/>">
                                <td><input type="text" value ="<c:out value="${ucob.carModel}"/>" readonly></td>
                                <td><input type="text" value ="<c:out value="${ucob.serialId}"/>" readonly></td>
                                <td><select name="statuses" required>
                                    <option selected value="<c:out value="${ucob.carOrderStatusId}"/>"><c:out value="${ucob.carOrderStatus}"/></option>
                                        <c:forEach var="status" items="${cosList}">
                                            <c:if test="${status.name != ucob.carOrderStatus}">
                                                <option value="<c:out value="${status.carOrderStatusId}"/>"><c:out value="${status.name}"/></option>
                                            </c:if>
                                        </c:forEach>
                                    </select></td>
                                <td><input type="text" name="startDate" value ="<c:out value="${ucob.startDate}"/>"readonly></td>
                                <td><input type="text" value ="<c:out value="${ucob.endDate}"/>"readonly></td>
                                <td><input type="submit" value="edit" name="edit"></td>
                                <td><input type="submit" value="delete" name="delete"></td>
                            </form>
                        </tr>
                    </c:forEach>
            </table>
            <table border="1">
                <caption>Orders</caption>
                    <tr>
                        <th>carName</th>
                        <th>carVIN</th>
                    </tr>
                    <tr>
                        <form action="orders" name="add" method="post">
                            <input type="hidden" name="login" value="<c:out value="${param.login}"/>">
                            <td><select name="cars" required>
                                    <c:forEach var="cb" items="${cbList}">
                                        <option value="<c:out value="${cb.carId}"/>"><c:out value="${cb.carBrand} ${cb.carModel} ${cb.carVIN}"/></option>
                                    </c:forEach>
                                </select></td>
                            <td><select name="statuses" required>
                                    <c:forEach var="status" items="${cosList}">
                                        <option value="<c:out value="${status.carOrderStatusId}"/>"><c:out value="${status.name}"/></option>
                                    </c:forEach>
                                </select></td>
                            <td><input type="submit" value="add" name="add"></td>
                        </form>
                    </tr>
            </table>
            <c:out value="${param.message}"/>
       </c:if>
    </jsp:attribute>
</t:general>