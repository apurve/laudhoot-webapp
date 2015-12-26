<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:if test="${geofence.id eq null }">
	<div>
		<h1>CREATE GEOFENCE</h1>
	</div>
	<form:form method="post" modelAttribute="geofence">
			CODE : <form:input path="code" />
		<form:errors path="code" cssClass="error" />
		<br />
			LATITUDE : <form:input path="center.latitude" />
		<form:errors path="center.latitude" cssClass="error" />
		<br />
			LONGITUDE : <form:input path="center.longitude" />
		<form:errors path="center.longitude" cssClass="error" />
		<br />
			EXPIRES IN (HRS) : <form:input path="expiresInHours" />
		<form:errors path="expiresInHours" cssClass="error" />
		<br />
			RADIUS (MTR) : <form:input path="radiusInMeters" />
		<form:errors path="radiusInMeters" cssClass="error" />
		<br />
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="submit" value="Add Geofence" />
	</form:form>
</c:if>