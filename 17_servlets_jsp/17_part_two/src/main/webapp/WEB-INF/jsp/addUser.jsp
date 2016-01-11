<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<t:template title="Find book">
    <jsp:attribute name="content_area">
        <c:if test="${not empty users}">
                        <table border="1">
                             <thead>
                                 <tr><th>userId</th><th>userName</th><th>userRole</th><th>edit</th><th>delete</th></tr>
                             </thead>
                             <tbody>
                                    <c:forEach var="user" items="${users}">
                                       <tr>
                                            <form action="userManagement" name="editDelete" method="post">
                                                <td><input type="text" name="userId" value ="<c:out value="${user.user.userId}"/>" readonly></td>
                                                <td>
                                                    <input type="text" name="userLogin" value ="<c:out value="${user.user.login}"/>" required>
                                                    <input type="hidden" name="userPassword" value="<c:out value="${user.user.password}"/>">
                                                </td>
                                                <td>
                                                    <select name="userRole" size="1" required>
                                                        <option selected value="<c:out value="${user.role.roleId}"/>"><c:out value="${user.role.name}"/></option>
                                                        <c:forEach var="role" items="${roles}">
                                                            <option value="<c:out value="${role.roleId}"/>"><c:out value="${role.name}"/></option>
                                                        </c:forEach>
                                                    </select>
                                                </td>
                                                <td><input type="submit" value="edit" name="edit"></td>
                                                <td><input type="submit" value="delete" name="delete"></td>
                                            </form>
                                       </tr>
                                   </c:forEach>
                             </tbody>
                        </table>
                    </c:if>
                    <form action="userManagement" method="post">
                                 User login : <input type="text" name="newUserLogin">
                                 User password : <input type="text" name="newUserPassword">
                                 User role:  <select name="newUserRole" size="1" required>
                                               <c:forEach var="role" items="${roles}">
                                                   <option value="<c:out value="${role.roleId}"/>"><c:out value="${role.name}"/></option>
                                               </c:forEach>
                                             </select>
                                 <input type="submit" value="add" name="add">
                    </form>
    </jsp:attribute>
    <jsp:attribute name="message_area">
        <c:if test="${not empty param.message}">
            <p align="center"><c:out value="${param.message}"/></p>
        </c:if>
    </jsp:attribute>
</t:template>