<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general title="Clients">
    <jsp:attribute name="internal_content_area">
        <c:if test="${not empty ucSet}">
            <table border="1">
                <caption>Clients</caption>
                    <tr>
                        <th>first_name</th>
                        <th>last_name</th>
                        <th>user_login</th>
                        <th>user_password</th>
                        <th>user_role</th>
                    </tr>
                    <c:forEach var="uc" items="${ucSet}" varStatus="status">
                        <tr>
                            <form action="clients" name="upDel" method="post">
                                <input type="hidden" name="client_id" value ="<c:out value="${uc.clientId}"/>">
                                <input type="hidden" name="user_id" value ="<c:out value="${uc.user.userId}"/>">
                                <td><input type="text" name="first_name" value ="<c:out value="${uc.firstName}"/>"></td>
                                <td><input type="text" name="last_name" value ="<c:out value="${uc.lastName}"/>"></td>
                                <td><input type="text" name="user_login" value ="<c:out value="${uc.user.login}"/>" readonly></td>
                                <td><input type="text" name="user_password" value ="<c:out value="${uc.user.password}"/>"></td>
                                <td><select name="roles" required>
                                        <option selected value="<c:out value="${uc.user.role.roleId}"/>"><c:out value="${uc.user.role.roleName}"/></option>
                                            <c:forEach var="role" items="${roleSet}">
                                                <c:if test="${role.roleName != uc.user.role.roleName}">
                                                    <option value="<c:out value="${role.roleId}"/>"><c:out value="${role.roleName}"/></option>
                                                </c:if>
                                            </c:forEach>
                                    </select></td>
                                <td><input type="submit" value="edit" name="edit"></td>
                                <td><input type="submit" value="delete" name="delete"></td>
                            </form>
                        </tr>
                    </c:forEach>
            </table>
        </c:if>
            <table border="1">
                        <caption>Add client</caption>
                            <tr>
                                <th>first_name</th>
                                <th>last_name</th>
                                <th>user_login</th>
                                <th>user_password</th>
                                <th>user_role</th>
                            </tr>
                    <tr>
                        <form action="clients" name="add" method="post">
                            <td><input type="text" name="first_name" required/></td>
                            <td><input type="text" name="last_name" required/></td>
                            <td><input type="text" name="user_login" required/></td>
                            <td><input type="text" name="user_password" required/></td>
                            <td><select name="roles" required>
                                    <c:forEach var="role" items="${roleSet}">
                                        <option value="<c:out value="${role.roleId}"/>"><c:out value="${role.roleName}"/></option>
                                    </c:forEach>
                                </select></td>
                            <td><input type="submit" value="add" name="add"></td>
                        </form>
                    </tr>
            </table>
            <c:out value="${param.message}"/>
    </jsp:attribute>
</t:general>