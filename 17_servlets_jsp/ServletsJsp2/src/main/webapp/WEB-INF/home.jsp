<%-- 
    Document   : index
    Created on : Jan 26, 2016, Jan 26, 2016 3:13:30 PM
    Author     : mednorcom
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="Home Page">
    <jsp:attribute name="content_area">        
        <c:if test="${empty sessionScope.name}">
            <p>Login to gain more rights</p>
        </c:if>
        <c:if test="${not empty sessionScope.name}">
            <div class="user-info">
                <p>Your account is: ${sessionScope.name}</p>
                <p>Your role is: ${webUserBean.role.webRoleName}</p>
            </div>
        </c:if>
        <c:choose>
            <c:when test="${webUserBean.role.webRoleName eq 'user'}">
                <h3>Check your orders status</h3>
            </c:when>

            <c:when test="${webUserBean.role.webRoleName eq 'manager'}">
                <h3>Check unassigned orders</h3>
            </c:when>

            <c:when test="${webUserBean.role.webRoleName eq 'employee'}">
                <h3>Check your in-progress orders</h3>
            </c:when>
        </c:choose>

        <form method='post'>
            <table border='1'>
                <tr>
                    <th>Actions</th>
                    <th>Order ID</th>
                    <th>Car</th>
                    <th>Status</th> 
                    <th>Start time</th>
                    <th>End time</th>
                </tr>
                <c:forEach var="carOrderBean" items="${requestScope.carOrderBeans}">
                    <tr>
                        <td>
                            <span>
                                <button ${webUserBean.role.webRoleName eq "user" ?"disabled":""} formaction='car-orders' formmethod='get' name='edit' value='<c:out value="${carOrderBean.carOrderId}"/>' >edit</button>
                                <button ${webUserBean.role.webRoleName eq "user" ?"disabled":""} formaction='car-orders' formmethod='get' name='delete' value='<c:out value="${carOrderBean.carOrderId}"/>' >delete</button>
                                <button ${webUserBean.role.webRoleName eq "user" ?"disabled":""} formaction='assignments' formmethod='get' name='car-order-id' value='<c:out value="${carOrderBean.carOrderId}"/>' >assignments</button>
                            </span>
                        </td>
                        <td><c:out value="${carOrderBean.carOrderId}"/></td>
                        <td><c:out value="${carOrderBean.carBean.carType.brand}:${carOrderBean.carBean.carType.model}-[${carOrderBean.carBean.serialId}]"/></td> 
                        <td><c:out value="${carOrderBean.carOrderStatus.name}"/></td>
                        <td><fmt:formatDate type="both"  value="${carOrderBean.startDate}"/></td>
                        <td><fmt:formatDate type="both"  value="${carOrderBean.endDate}"/></td>
                    </tr>                 
                </c:forEach>
            </table>
        </form>
    </jsp:attribute>
    <jsp:attribute name="message">        
    </jsp:attribute>
</t:general_template>
