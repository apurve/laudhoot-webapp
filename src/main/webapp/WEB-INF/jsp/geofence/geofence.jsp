<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div><h1>CREATED GEOFENCE DETAILS</h1></div>
<h3>CODE : ${geofence.code }</h3>
<h3>LATITUDE : ${geofence.center.latitude }</h3>
<h3>LONGITUDE : ${geofence.center.longitude }</h3>
<h3>EXPIRES ON : ${geofence.expiresOn }</h3>