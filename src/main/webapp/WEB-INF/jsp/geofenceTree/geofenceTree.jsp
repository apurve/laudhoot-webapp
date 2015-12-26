<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div>
	<h1>CREATED GEOFENCE DETAILS</h1>
</div>
<br>
<h3>CODE : ${geofence.code }</h3>
<h3>LATITUDE : ${geofence.center.latitude }</h3>
<h3>LONGITUDE : ${geofence.center.longitude }</h3>
<h3>EXPIRES ON : ${geofence.expiresInHours }</h3>
<h3>Parent : ${geofenceTreeNode.parent }</h3>
<h3>Children : <c:forEach items="${geofenceTreeNode.children}" var="child">
				- ${child} -
				</c:forEach>
</h3>

<br>
<div>
	<h1>SEARCH PARENT</h1>
	<input type="hidden" id="code" value="${geofenceTreeNode.code}"/>
	<br>
	<input type="text" id="parent"/>
	<button id="checkParent" onClick="checkParent();">Check Parent</button>
</div>
<div id="childrenView" ></div>