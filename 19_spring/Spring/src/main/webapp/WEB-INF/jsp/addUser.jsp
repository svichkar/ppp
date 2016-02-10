<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<t:template title="Find book">
    <jsp:attribute name="content_area">
        <c:if test="${not empty users}">
                        <table border="1" class="table">
                             <thead>
                                 <tr><th>userId</th><th>userName</th><th>userRole</th><th>edit</th><th>delete</th></tr>
                             </thead>
                             <tbody>
                                    <c:forEach var="user" items="${users}">
                                       <tr>
                                            <form action="<c:url value="/userManagement"></c:url>" onsubmit="return validateForm(this,optionsForUserForm);" method="POST"/>
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                <td><input type="text" name="userId" value ="<c:out value="${user.userId}"/>" readonly></td>
                                                <td>
                                                    <input type="text" name="userLogin" value ="<c:out value="${user.login}"/>" required title="enter username here">
                                                    <input type="hidden" name="userPassword" value="<c:out value="${user.password}"/>" title="enter password here">
                                                </td>
                                                <td>
                                                    <select name="userRole" size="1" required title="choose user role here">
                                                        <option selected value="<c:out value="${user.role.roleId}"/>"><c:out value="${user.role.roleName}"/></option>
                                                        <c:forEach var="role" items="${roles}">
                                                            <option value="<c:out value="${role.roleId}"/>"><c:out value="${role.roleName}"/></option>
                                                        </c:forEach>
                                                    </select>
                                                </td>
                                                <td><input type="submit" value="edit" name="edit"></td>
                                                <td>
                                                    <c:if test="${sessionScope.currentUserId != user.userId}">
                                                        <input type="submit" value="delete" name="delete">
                                                    </c:if>
                                                </td>
                                            </form>
                                       </tr>
                                   </c:forEach>
                             </tbody>
                        </table>
                    </c:if>
                    <form action="userManagement" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                 User login : <input type="text" name="userLogin" title="enter username here">
                                 User password : <input type="text" name="userPassword" title="enter username here">
                                 User role:  <select name="userRole" size="1" required title="enter username here">
                                               <c:forEach var="role" items="${roles}">
                                                   <option value="<c:out value="${role.roleId}"/>"><c:out value="${role.roleName}"/></option>
                                               </c:forEach>
                                             </select>
                                 <input type="submit" value="add" name="add">
                    </form>
    </jsp:attribute>
    <jsp:attribute name="message_area">
        <c:if test="${not empty msg}">
            <p align="center"><c:out value="${msg}"/></p>
        </c:if>
        <div id="errorMsg"></div>
    </jsp:attribute>
</t:template>