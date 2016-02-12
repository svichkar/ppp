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
            <input type='hidden' name='car-order-id' value='${carOrderBean.carOrderId}'/>
            <table border='1'>
                <span><caption>Order&nbsp;id:[${carOrderBean.carOrderId}] ${carOrderBean.carBean.carType.brand}-${carOrderBean.carBean.carType.model} SID:[${carOrderBean.carBean.serialId}] date:&nbsp;<fmt:formatDate type="both"  value="${carOrderBean.startDate}"/></caption></span>
                <tr>
                    <th><button formaction='add-assignment' formmethod='get'>add</button></th>
                    <th>ID</th>
                    <th>First Name</th> 
                    <th>Last Name</th>
                    <th>Category</th>
                </tr>
                <c:forEach var="assignmentBean" items="${requestScope.assignmentBeans}">
                    <tr>
                        <c:choose>
                            <c:when test="${param.delete eq assignmentBean.employeeBean.employeeId}">

                                <td>
                                    <button formaction='assignments' formmethod='post' name='action' value='delete' >ok</button>
                                    <button formaction='assignments' formmethod='get' name='action' value='cancel"/>' >cancel</button>
                                </td>
                                <td><input type='hidden' name='employee-id' value='${assignmentBean.employeeBean.employeeId}'/><c:out value="${assignmentBean.employeeBean.employeeId}"/></td>
                                <td><c:out value="${assignmentBean.employeeBean.firstName}"/></td> 
                                <td><c:out value="${assignmentBean.employeeBean.lastName}"/></td>
                                <td><c:out value="${assignmentBean.employeeBean.employeeCategory.name}"/></td>                                
                            </c:when>
                            <c:otherwise>
                                <td>                                    
                                    <button  formaction='assignments' formmethod='get' name='delete' value='<c:out value="${assignmentBean.employeeBean.employeeId}"/>' >delete</button>
                                </td>
                                <td><c:out value="${assignmentBean.employeeBean.employeeId}"/></td>
                                <td><c:out value="${assignmentBean.employeeBean.firstName}"/></td> 
                                <td><c:out value="${assignmentBean.employeeBean.lastName}"/></td>
                                <td><c:out value="${assignmentBean.employeeBean.employeeCategory.name}"/></td>                              
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