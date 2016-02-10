<%-- 
    Document   : clients
    Created on : Jan 28, 2016, Jan 28, 2016 5:41:08 PM
    Author     : mednorcom
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="Clients">
    <jsp:attribute name="content_area">

        <form method='post'>
            <table border='1'>
                <tr>
                    <th><button formaction='add-client' formmethod='get'>add</button></th>
                    <th>ID</th>
                    <th>First Name</th> 
                    <th>Last Name</th>
                    <th>Account</th>
                </tr>
                <c:forEach var="clientBean" items="${requestScope.clientBeans}">
                    <tr>
                        <c:choose>
                            <c:when test="${param.edit eq clientBean.clientId}">
                                <td>
                                    <button formaction='clients' formmethod='post' name='action' value='edit'>ok</button>
                                    <button formaction='clients' formmethod='get' name='action' value='cancel' >cancel</button>
                                </td>   
                                <td><input type='hidden' name='client-id' value='${clientBean.clientId}'/><c:out value="${clientBean.clientId}"/></td>
                                <td><input type='text' size='20' name='new-fname' value="${clientBean.firstName}"/></td> 
                                <td><input type='text' size='20' name='new-lname' value="${clientBean.lastName}"/></td>
                                <td>
                                    <select disabled >
                                        <c:forEach var="webUserBean" items="${requestScope.webUserBeans}">
                                            <option ${webUserBean.webUserId eq clientBean.webUserBean.webUserId?"selected":""} value="${webUserBean.webUserId}">${webUserBean.webUserLogin}</option>
                                        </c:forEach>
                                    </select>
                                    <input type="hidden" name='new-web-user' value="${clientBean.webUserBean.webUserId}"/>
                                </td>
                            </c:when>
                            <c:when test="${param.delete eq clientBean.clientId}">
                                <td>
                                    <button formaction='clients' formmethod='post' name='action' value='delete' >ok</button>
                                    <button formaction='clients' formmethod='get' name='action' value='cancel"/>' >cancel</button>
                                </td>
                                <td><input type='hidden' name='client-id' value='${clientBean.clientId}'/><c:out value="${clientBean.clientId}"/></td>
                                <td><c:out value="${clientBean.firstName}"/></td> 
                                <td><c:out value="${clientBean.lastName}"/></td>
                                <td><c:out value="${clientBean.webUserBean.webUserLogin}"/></td>
                            </c:when>
                            <c:otherwise>
                                <td>
                                    <button  formaction='clients' formmethod='get' name='edit' value='<c:out value="${clientBean.clientId}"/>' >edit</button>
                                    <button  formaction='clients' formmethod='get' name='delete' value='<c:out value="${clientBean.clientId}"/>' >delete</button>
                                </td>
                                <td><c:out value="${clientBean.clientId}"/></td>
                                <td><c:out value="${clientBean.firstName}"/></td> 
                                <td><c:out value="${clientBean.lastName}"/></td>
                                <td><c:out value="${clientBean.webUserBean.webUserLogin}"/></td>
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