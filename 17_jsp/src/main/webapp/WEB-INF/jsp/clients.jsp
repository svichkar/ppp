<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general title="Clients">
    <jsp:attribute name="internal_content_area">
        <c:if test="${not empty ucbList}">
            <table border="1">
                <caption>Clients</caption>
                    <tr>
                        <th>first_name</th>
                        <th>last_name</th>
                        <th>user_login</th>
                        <th>user_password</th>
                        <th>user_role</th>
                    </tr>
                    <c:forEach var="ucb" items="${ucbList}" varStatus="status">
                        <tr>
                            <form action="clients" name="upDel" method="post">
                                <input type="hidden" name="login" value="<c:out value="${param.login}"/>">
                                <input type="hidden" name="client_id" value ="<c:out value="${ucb.clientId}"/>">
                                <input type="hidden" name="user_id" value ="<c:out value="${ucb.userId}"/>">
                                <td><input type="text" name="first_name" value ="<c:out value="${ucb.clientFName}"/>"></td>
                                <td><input type="text" name="last_name" value ="<c:out value="${ucb.clientLName}"/>"></td>
                                <td><input type="text" name="user_login" value ="<c:out value="${ucb.login}"/>" readonly></td>
                                <td><input type="text" name="user_password" value ="<c:out value="${ucb.password}"/>"></td>
                                <td><select name="roles" required>
                                        <option selected value="<c:out value="${ucb.roleId}"/>"><c:out value="${ucb.role}"/></option>
                                            <c:forEach var="role" items="${roleList}">
                                                <c:if test="${role.roleName != ucb.role}">
                                                    <option value="<c:out value="${role.roleId}"/>"><c:out value="${role.roleName}"/></option>
                                                </c:if>
                                            </c:forEach>
                                    </select></td>
                                <td><input type="submit" value="edit" name="edit"></td>
                                <td><input type="submit" value="delete" name="delete"></td>
                            </form>
                        </tr>
                    </c:forEach>
                    <tr>
                        <form action="clients" name="add" method="post">
                            <input type="hidden" name="login" value="<c:out value="${param.login}"/>">
                            <td><input type="text" name="first_name"/></td>
                            <td><input type="text" name="last_name"/></td>
                            <td><input type="text" name="user_login"/></td>
                            <td><input type="text" name="user_password"/></td>
                            <td><select name="roles" required>
                                    <c:forEach var="role" items="${roleList}">
                                        <option value="<c:out value="${role.roleId}"/>"><c:out value="${role.roleName}"/></option>
                                    </c:forEach>
                                </select></td>
                            <td><input type="submit" value="add" name="add"></td>
                        </form>
                    </tr>
            </table>
            <c:out value="${param.message}"/>
       </c:if>
    </jsp:attribute>
</t:general>