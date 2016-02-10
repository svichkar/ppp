<%-- 
    Document   : administration
    Created on : Jan 28, 2016, Jan 28, 2016 5:41:08 PM
    Author     : mednorcom
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="Car Models">
    <jsp:attribute name="content_area">

        <form method='post'>
            <table border='1px'>
                <tr>
                    <th><button formaction='add-car-model' formmethod='get'>add</button></th>
                    <th>ID</th>
                    <th>Brand</th> 
                    <th>Model</th>
                </tr>
                <c:forEach var="carModel" items="${requestScope.carModels}">
                    <tr>
                        <c:choose>
                            <c:when test="${param.edit eq carModel.carTypeId}">
                                <td>
                                    <button formaction='car-models' formmethod='post' name='action' value='edit'>ok</button>
                                    <button formaction='car-models' formmethod='post' name='action' value='cancel' >cancel</button>
                                </td>   
                                <td><input type='hidden' name='car-model-id' value='${carModel.carTypeId}'/><c:out value="${carModel.carTypeId}"/></td>
                                <td><input type='text' size='20' name='new-brand' value="${carModel.brand}"/></td> 
                                <td><input type='text' size='20' name='new-model' value="${carModel.model}"/></td>
                                </c:when>
                                <c:when test="${param.delete eq carModel.carTypeId}">
                                <td>
                                    <button formaction='car-models' formmethod='post' name='action' value='delete'>ok</button>
                                    <button formaction='car-models' formmethod='post' name='action' value='cancel'>cancel</button>
                                </td>
                                <td><input type='hidden' name='car-model-id' value='${carModel.carTypeId}'/><c:out value="${carModel.carTypeId}"/></td>
                                <td><c:out value="${carModel.brand}"/></td> 
                                <td><c:out value="${carModel.model}"/></td>
                            </c:when>
                            <c:otherwise>
                                <td>
                                    <button formaction='car-models' formmethod='get' name='edit' value='<c:out value="${carModel.carTypeId}"/>' >edit</button>
                                    <button formaction='car-models' formmethod='get' name='delete' value='<c:out value="${carModel.carTypeId}"/>' >delete</button>
                                </td>
                                <td><c:out value="${carModel.carTypeId}"/></td>
                                <td><c:out value="${carModel.brand}"/></td> 
                                <td><c:out value="${carModel.model}"/></td>                                
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