<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<t:template title="Error Page">
	<jsp:attribute name="content_area">
		<div class="error-page-wrap">
			<article class="error-page gradient">
				<hgroup>
					<h1>${errorCode}</h1>
					<h2>Oops! Error is here ${errorMessage}</h2>
				</hgroup>
				<a href="<c:url value="/students"/>" title="Back to site"
					class="error-back">Back</a>
			</article>
		</div>
    </jsp:attribute>
</t:template>