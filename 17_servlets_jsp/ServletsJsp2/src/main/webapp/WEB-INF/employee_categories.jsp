<%-- 
    Document   : administration
    Created on : Jan 28, 2016, Jan 28, 2016 5:41:08 PM
    Author     : mednorcom
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="Employee Categories">
    <jsp:attribute name="content_area">

        <form method='post'>
            <table border='1px'>
                <tr>
                    <th><button formaction='add-employee-category' formmethod='get'>add</button></th>
                    <th>ID</th>
                    <th>Category Name</th> 
                </tr>
                <c:forEach var="employeeCategory" items="${requestScope.employeeCategories}">
                    <tr>
                        <c:choose>
                            <c:when test="${param.edit eq employeeCategory.employeeCategoryId}">
                                <td>
                                    <button formaction='employee-categories' formmethod='post' name='action' value='edit'>ok</button>
                                    <button formaction='employee-categories' formmethod='get' name='action' value='cancel' >cancel</button>
                                </td>   
                                <td><input type='hidden' name='employee-category-id' value='${employeeCategory.employeeCategoryId}'/><c:out value="${employeeCategory.employeeCategoryId}"/></td>
                                <td><input type='text' size='20' name='new-category-name' value="${employeeCategory.name}"/></td> 
                                </c:when>
                                <c:when test="${param.delete eq employeeCategory.employeeCategoryId}">
                                <td>
                                    <button formaction='employee-categories' formmethod='post' name='action' value='delete'>ok</button>
                                    <button formaction='employee-categories' formmethod='get' name='action' value='cancel'>cancel</button>
                                </td>
                                <td><input type='hidden' name='employee-category-id' value='${employeeCategory.employeeCategoryId}'/><c:out value="${employeeCategory.employeeCategoryId}"/></td>
                                <td><c:out value="${employeeCategory.name}"/></td> 
                            </c:when>
                            <c:otherwise>
                                <td>
                                    <button formaction='employee-categories' formmethod='get' name='edit' value='<c:out value="${employeeCategory.employeeCategoryId}"/>' >edit</button>
                                    <button formaction='employee-categories' formmethod='get' name='delete' value='<c:out value="${employeeCategory.employeeCategoryId}"/>' >delete</button>
                                </td>
                                <td><c:out value="${employeeCategory.employeeCategoryId}"/></td>
                                <td><c:out value="${employeeCategory.name}"/></td>                              
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