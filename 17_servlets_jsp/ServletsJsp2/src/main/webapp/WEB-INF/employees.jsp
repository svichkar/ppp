<%-- 
    Document   : employees
    Created on : Jan 28, 2016, Jan 28, 2016 5:41:08 PM
    Author     : mednorcom
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="Employees">
    <jsp:attribute name="content_area">

        <form method='post'>
            <table border='1'>
                <tr>
                    <th><button formaction='add-employee' formmethod='get'>add</button></th>
                    <th>ID</th>
                    <th>First Name</th> 
                    <th>Last Name</th>
                    <th>Category</th>
                    <th>Account</th>
                </tr>
                <c:forEach var="employeeBean" items="${requestScope.employeeBeans}">
                    <tr>
                        <c:choose>
                            <c:when test="${param.edit eq employeeBean.employeeId}">
                                <td>
                                    <button formaction='employees' formmethod='post' name='action' value='edit'>ok</button>
                                    <button formaction='employees' formmethod='get' name='action' value='cancel' >cancel</button>
                                </td>   
                                <td><input type='hidden' name='employee-id' value='${employeeBean.employeeId}'/><c:out value="${employeeBean.employeeId}"/></td>
                                <td><input type='text' size='20' name='new-fname' value="${employeeBean.firstName}"/></td> 
                                <td><input type='text' size='20' name='new-lname' value="${employeeBean.lastName}"/></td>
                                <td>
                                    <select name="new-employee-category-id">
                                        <c:forEach var="employeeCategory" items="${requestScope.employeeCategories}">
                                            <option ${employeeCategory.employeeCategoryId eq employeeBean.employeeCategory.employeeCategoryId?"selected":""} value="${employeeCategory.employeeCategoryId}">${employeeCategory.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>                                
                                <td>
                                    <select disabled >
                                        <c:forEach var="webUserBean" items="${requestScope.webUserBeans}">
                                            <option ${webUserBean.webUserId eq employeeBean.webUserBean.webUserId?"selected":""} value="${webUserBean.webUserId}">${webUserBean.webUserLogin}</option>
                                        </c:forEach>
                                    </select>
                                    <input type="hidden" name='new-web-user' value="${employeeBean.webUserBean.webUserId}"/>
                                </td>
                            </c:when>
                            <c:when test="${param.delete eq employeeBean.employeeId}">
                                <td>
                                    <button formaction='employees' formmethod='post' name='action' value='delete' >ok</button>
                                    <button formaction='employees' formmethod='get' name='action' value='cancel"/>' >cancel</button>
                                </td>
                                <td><input type='hidden' name='employee-id' value='${employeeBean.employeeId}'/><c:out value="${employeeBean.employeeId}"/></td>
                                <td><c:out value="${employeeBean.firstName}"/></td> 
                                <td><c:out value="${employeeBean.lastName}"/></td>
                                <td><c:out value="${employeeBean.employeeCategory.name}"/></td>
                                <td><c:out value="${employeeBean.webUserBean.webUserLogin}"/></td>
                            </c:when>
                            <c:otherwise>
                                <td>
                                    <button  formaction='employees' formmethod='get' name='edit' value='<c:out value="${employeeBean.employeeId}"/>' >edit</button>
                                    <button  formaction='employees' formmethod='get' name='delete' value='<c:out value="${employeeBean.employeeId}"/>' >delete</button>
                                </td>
                                <td><c:out value="${employeeBean.employeeId}"/></td>
                                <td><c:out value="${employeeBean.firstName}"/></td> 
                                <td><c:out value="${employeeBean.lastName}"/></td>
                                <td><c:out value="${employeeBean.employeeCategory.name}"/></td>
                                <td><c:out value="${employeeBean.webUserBean.webUserLogin}"/></td>
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