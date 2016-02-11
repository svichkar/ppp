<%-- 
    Document   : administration
    Created on : Jan 28, 2016, Jan 28, 2016 5:41:08 PM
    Author     : mednorcom
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="Assignments">
    <jsp:attribute name="content_area">
        <form method='post'>
            <table border='1'>
                <tr>
                    <th><button formaction='add-car-order' formmethod='get'>add</button></th>
                    <th>Car Order</th>
                    <th>Assigned Person</th>
                </tr>
                <c:forEach var="assignmentBean" items="${requestScope.assignmentBeans}">
                    <tr>
                        <c:choose>
                            <c:when test="${param.delete eq assignmentBean.carOrderBean.carOrderId}">
                                <td>
                                    <button formaction='car-orders' formmethod='post' name='action' value='delete' >ok</button>
                                    <button formaction='car-orders' formmethod='get' name='action' value='cancel"/>' >cancel</button>
                                </td>
                                <td><input type='hidden' name='car-order-id' value='${assignmentBean.carOrderBean.carOrderId}'/><c:out value="${assignmentBean.carOrderBean.carOrderId}"/></td>
                                <td><c:out value="${assignmentBean.carOrderBean.carBean.carType.brand}:${assignmentBean.carOrderBean.carBean.carType.model}-[${assignmentBean.carOrderBean.carBean.serialId}]"/></td> 
                                <td><c:out value="${assignmentBean.carOrderBean.carOrderStatus.name}"/></td>
                                <td><fmt:formatDate type="both"  value="${assignmentBean.carOrderBean.startDate}"/></td>
                                <td><fmt:formatDate type="both"  value="${assignmentBean.carOrderBean.endDate}"/></td>
                            </c:when>
                            <c:otherwise>
                                <td>
                                    <button formaction='car-orders' formmethod='get' name='add' value='<c:out value="${assignmentBean.carOrderBean.carOrderId}"/>' >edit</button>
                                    <button formaction='car-orders' formmethod='get' name='delete' value='<c:out value="${assignmentBean.carOrderBean.carOrderId}"/>' >delete</button>
                                </td>
                                <td><c:out value="${assignmentBean.carOrderBean.carOrderId}"/></td>
                                <td><c:out value="${assignmentBean.carOrderBean.carBean.carType.brand}:${assignmentBean.carOrderBean.carBean.carType.model}-[${assignmentBean.carOrderBean.carBean.serialId}]"/></td> 
                                <td><c:out value="${assignmentBean.carOrderBean.carOrderStatus.name}"/></td>
                                <td><fmt:formatDate type="both"  value="${assignmentBean.carOrderBean.startDate}"/></td>
                                <td><fmt:formatDate type="both"  value="${assignmentBean.carOrderBean.endDate}"/></td>
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