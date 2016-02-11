<%-- 
    Document   : create_user
    Created on : Feb 1, 2016, Feb 1, 2016 4:59:06 PM
    Author     : mednorcom
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="Add Car Order">
    <jsp:attribute name="content_area">
        <form action="add-car-order" method="post">
            <table>
                <tbody>               
                    <tr><td>Car</td>
                        <td>
                            <select name="new-car-id">
                                <c:forEach var="carBean" items="${requestScope.carBeans}">
                                    <option value="${carBean.carId}">${carBean.carType.brand}:${carBean.carType.model}-[${carBean.serialId}]</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Status: </td>
                        <td>
                            <select name="new-car-order-status-id">
                                <c:forEach var="carOrderStatus" items="${requestScope.carOrderStatuses}">
                                    <option value="${carOrderStatus.carOrderStatusId}">${carOrderStatus.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Start Date</td>
                        <td>
                            <input size='4' type="text" name="start-year" pattern="\d{4}" placeholder="yyyy" value="<fmt:formatDate pattern="yyyy" value="${dateNow}"/>" />
                            <input size='2' type="text" name="start-month" pattern="\d\d" placeholder="MM" value="<fmt:formatDate pattern="MM" value="${dateNow}"/>"/>
                            <input size='2' type="text" name="start-day" pattern="\d\d" placeholder="dd" value="<fmt:formatDate pattern="dd" value="${dateNow}"/>"/>
                            <input size='2' type="text" name="start-hour" pattern="\d\d" placeholder="HH" value="<fmt:formatDate pattern="HH" value="${dateNow}"/>"/>
                            <input size='2' type="text" name="start-minute" pattern="\d\d" placeholder="mm" value="<fmt:formatDate pattern="mm" value="${dateNow}"/>"/>                             
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" name="status" value="create">
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
    </jsp:attribute>
    <jsp:attribute name="message">
        <c:if test="${not empty requestScope.message}">
            <c:out value="${requestScope.message}"/>
        </c:if>
    </jsp:attribute>
</t:general_template>
