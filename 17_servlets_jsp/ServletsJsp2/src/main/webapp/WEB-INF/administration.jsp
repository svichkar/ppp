<%-- 
    Document   : administration
    Created on : Jan 28, 2016, Jan 28, 2016 5:41:08 PM
    Author     : mednorcom
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="Administration">
    <jsp:attribute name="content_area">

        <form method='post'>
            <table border='1px'>
                <tr>
                    <th><button formaction='create-user' formmethod='get'>add</button></th>
                    <th>ID</th>
                    <th>Login</th> 
                    <th>Password</th>
                    <th>Role</th>
                </tr>
                <c:forEach var="webUserBean" items="${requestScope.webUserBeans}">
                    <tr>
                        <c:choose>
                            <c:when test="${param.edit eq webUserBean.webUserId}">
                                <td>
                                    <button formaction='administration' formmethod='post' name='action' value='edit'>ok</button>
                                    <button formaction='administration' formmethod='get' name='action' value='cancel' >cancel</button>
                                </td>   
                                <td><input type='hidden' name='user-id' value='${webUserBean.webUserId}'/><c:out value="${webUserBean.webUserId}"/></td>
                                <td><input type='text' size='20' name='new-login' value="${webUserBean.webUserLogin}"/></td> 
                                <td><input type='password' size='20' name='new-password' value="${webUserBean.webUserPassword}"/></td>
                                <td>
                                    <select name='new-role'>
                                        <c:forEach var="webRole" items="${requestScope.webRoles}">
                                            <option ${webRole.webRoleId eq webUserBean.role.webRoleId?"selected":""} value="${webRole.webRoleId}">${webRole.webRoleName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </c:when>
                            <c:when test="${param.delete eq webUserBean.webUserId}">
                                <td>
                                    <button formaction='administration' formmethod='post' name='action' value='delete' >ok</button>
                                    <button formaction='administration' formmethod='get' name='action' value='cancel"/>' >cancel</button>
                                </td>
                                <td><input type='hidden' name='user-id' value='${webUserBean.webUserId}'/><c:out value="${webUserBean.webUserId}"/></td>
                                <td><c:out value="${webUserBean.webUserLogin}"/></td> 
                                <td><c:out value="${webUserBean.webUserPassword}"/></td>
                                <td><c:out value="${webUserBean.role.webRoleName}"/></td>
                            </c:when>
                            <c:otherwise>
                                <td>
                                    <button ${sessionScope.name eq webUserBean.webUserLogin?"disabled":""} formaction='administration' formmethod='get' name='edit' value='<c:out value="${webUserBean.webUserId}"/>' >edit</button>
                                    <button ${sessionScope.name eq webUserBean.webUserLogin?"disabled":""} formaction='administration' formmethod='get' name='delete' value='<c:out value="${webUserBean.webUserId}"/>' >delete</button>
                                </td>
                                <td><c:out value="${webUserBean.webUserId}"/></td>
                                <td><c:out value="${webUserBean.webUserLogin}"/></td> 
                                <td><c:out value="${webUserBean.webUserPassword}"/></td>
                                <td><c:out value="${webUserBean.role.webRoleName}"/></td>
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