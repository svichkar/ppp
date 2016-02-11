<%-- 
    Document   : administration
    Created on : Jan 28, 2016, Jan 28, 2016 5:41:08 PM
    Author     : mednorcom
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="Car Orders">
    <jsp:attribute name="content_area">
        <form method='post'>
            <table border='1'>
                <tr>
                    <th><button formaction='add-car-order' formmethod='get'>add</button></th>
                    <th>Order ID</th>
                    <th>Car</th>
                    <th>Status</th> 
                    <th>Start time</th>
                    <th>End time</th>
                </tr>
                <c:forEach var="carOrderBean" items="${requestScope.carOrderBeans}">
                    <tr>
                        <c:choose>
                            <c:when test="${param.edit eq carOrderBean.carOrderId}">
                                <td>
                                    <button formaction='car-orders' formmethod='post' name='action' value='edit'>ok</button>
                                    <button formaction='car-orders' formmethod='get' name='action' value='cancel' >cancel</button>
                                </td>   
                                <td><input type='hidden' name='car-order-id' value='${carOrderBean.carOrderId}'/><c:out value="${carOrderBean.carOrderId}"/></td>
                                <td>
                                    <select name="new-car-id">
                                        <c:forEach var="carBean" items="${requestScope.carBeans}">
                                            <option ${carBean.carId eq carBean.carId?"selected":""} value="${carBean.carId}">${carBean.carType.brand}:${carBean.carType.model}-[${carBean.serialId}]</option>
                                        </c:forEach>
                                    </select>
                                </td> 
                                <td>
                                    <select name="new-car-order-status-id">
                                        <c:forEach var="carOrderStatus" items="${requestScope.carOrderStatuses}">
                                            <option ${carOrderStatus.carOrderStatusId eq carBean.carOrderStatus.carOrderStatusId?"selected":""} value="${carOrderStatus.carOrderStatusId}">${carOrderStatus.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <input size='4' type="text" name="start-year" pattern="\d{4}" placeholder="yyyy" value="<fmt:formatDate pattern="yyyy" value="${carOrderBean.startDate}"/>"/>
                                    <input size='2' type="text" name="start-month" pattern="\d\d" placeholder="MM" value="<fmt:formatDate pattern="MM" value="${carOrderBean.startDate}"/>"/>
                                    <input size='2' type="text" name="start-day" pattern="\d\d" placeholder="dd" value="<fmt:formatDate pattern="dd" value="${carOrderBean.startDate}"/>"/>
                                    <input size='2' type="text" name="start-hour" pattern="\d\d" placeholder="HH" value="<fmt:formatDate pattern="HH" value="${carOrderBean.startDate}"/>"/>
                                    <input size='2' type="text" name="start-minute" pattern="\d\d" placeholder="mm" value="<fmt:formatDate pattern="mm" value="${carOrderBean.startDate}"/>"/>                            
                                </td>
                                <td>
                                    <input size='4' type="text" name="end-year" pattern="\d{4}" placeholder="yyyy" value="<fmt:formatDate pattern="yyyy" value="${carOrderBean.endDate}"/>"/>
                                    <input size='2' type="text" name="end-month" pattern="\d\d" placeholder="MM" value="<fmt:formatDate pattern="MM" value="${carOrderBean.endDate}"/>"/>                           
                                    <input size='2' type="text" name="end-day" pattern="\d\d" placeholder="dd" value="<fmt:formatDate pattern="dd" value="${carOrderBean.endDate}"/>"/>
                                    <input size='2' type="text" name="end-hour" pattern="\d\d" placeholder="HH" value="<fmt:formatDate pattern="HH" value="${carOrderBean.endDate}"/>"/>
                                    <input size='2' type="text" name="end-minute" pattern="\d\d" placeholder="mm" value="<fmt:formatDate pattern="mm" value="${carOrderBean.endDate}"/>"/>                            
                                </td>
                            </c:when>
                            <c:when test="${param.delete eq carOrderBean.carOrderId}">
                                <td>
                                    <button formaction='car-orders' formmethod='post' name='action' value='delete' >ok</button>
                                    <button formaction='car-orders' formmethod='get' name='action' value='cancel"/>' >cancel</button>
                                </td>
                                <td><input type='hidden' name='car-order-id' value='${carOrderBean.carOrderId}'/><c:out value="${carOrderBean.carOrderId}"/></td>
                                <td><c:out value="${carOrderBean.carBean.carType.brand}:${carOrderBean.carBean.carType.model}-[${carOrderBean.carBean.serialId}]"/></td> 
                                <td><c:out value="${carOrderBean.carOrderStatus.name}"/></td>
                                <td><fmt:formatDate type="both"  value="${carOrderBean.startDate}"/></td>
                                <td><fmt:formatDate type="both"  value="${carOrderBean.endDate}"/></td>
                            </c:when>
                            <c:otherwise>
                                <td>
                                    <button formaction='car-orders' formmethod='get' name='edit' value='<c:out value="${carOrderBean.carOrderId}"/>' >edit</button>
                                    <button formaction='car-orders' formmethod='get' name='delete' value='<c:out value="${carOrderBean.carOrderId}"/>' >delete</button>
                                </td>
                                <td><c:out value="${carOrderBean.carOrderId}"/></td>
                                <td><c:out value="${carOrderBean.carBean.carType.brand}:${carOrderBean.carBean.carType.model}-[${carOrderBean.carBean.serialId}]"/></td> 
                                <td><c:out value="${carOrderBean.carOrderStatus.name}"/></td>
                                <td><fmt:formatDate type="both"  value="${carOrderBean.startDate}"/></td>
                                <td><fmt:formatDate type="both"  value="${carOrderBean.endDate}"/></td>
                            </c:otherwise>
                        </c:choose>
                    </tr>                 
                </c:forEach>
            </table>
        </form>
    </jsp:attribute>
    <jsp:attribute name="message">
        <c:if test="${not empty requestScope.message}">
            <c:out value="${requestScope.message}"/>
        </c:if>
    </jsp:attribute>
</t:general_template>