<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:common_template>
	<jsp:attribute name="head_area">
	</jsp:attribute>
	<jsp:attribute name="content_area">
			<h2>Exception details</h2>
			<ul>
				<li>Code: <c:out value="${code}" /></li>
				<li><h2>Exception message:  <c:out value="${ex}" /></h2></li>
			</ul>
	</jsp:attribute>
</t:common_template>