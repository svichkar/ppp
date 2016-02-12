<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template>
	<jsp:attribute name="head_area">
	</jsp:attribute>
	<jsp:attribute name="content_area">
			<c:if test="${code != '500'}">
			<h2>Exception details</h2>
			<ul>
				<li>Code: <c:out value="${code}" /></li>
				<li>URI: <c:out value="${requestUri}" /></li>
				<li>Exception message:  <c:out value="${ex.message}" /></li>
			</ul>
			</c:if>
			<c:if test="${code == '500'}">
			<h2>Servlet exception details</h2>
			<ul>
				<li>Code: <c:out value="${code}" /></li>
				<li>URI: <c:out value="${requestUri}" /></li>
				<li>Name: <c:out value="${servletName}" /></li>
				<li>Exception message:  <c:out value="${ex.message}" /></li>
			</ul>
			</c:if>
	</jsp:attribute>
</t:general_template>