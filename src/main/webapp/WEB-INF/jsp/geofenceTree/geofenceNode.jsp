<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<br/>
<c:choose>
	<c:when test="${fenceable}">
		<div class="success">${parent} can fence ${geofence}.</div>
		<br/>
		<form:form method="post" modelAttribute="geofenceTreeNode" action="${contextPath}/geofence/fence">
			<form:input path="code" type="hidden" value="${geofence}"/>
			<form:input path="parent" type="hidden" value="${parent}"/>
			<c:if test="${!empty fenceableChildren}">
				<h2>MAP CHILDREN</h2>
				<div style="margin:20px">
					<form:checkboxes items="${fenceableChildren}" path="children" />
				</div>
			</c:if>
			<c:if test="${empty fenceableChildren}">
				<h2>NO CHILDREN TO MAP</h2>
			</c:if>
			<br/>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<input type="submit" value="Fence ${geofence} in ${parent}"/>
		</form:form>
	</c:when>
	<c:otherwise>
		<div class="info">${parent} cannot fence ${geofence}.</div>
	</c:otherwise>
</c:choose>
<script>enableCheckParent();</script>