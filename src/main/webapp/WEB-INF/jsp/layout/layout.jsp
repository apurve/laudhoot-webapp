<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<meta charset="utf-8" />
<title>Laodhoot Application</title>
<!--[if IE]>
		<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
<link rel="stylesheet"
	href="<c:url value="/static/css/layout/layout.css" />" type="text/css"
	media="all" />
<tiles:insertAttribute name="additionalCss" ignore="true" />
<c:forEach var="style" items="${additionalCss}">
	<link rel="stylesheet" href="<c:url value="/static/css/${style}" />"
		type="text/css" media="all" />
</c:forEach>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<tiles:insertAttribute name="additionalJs" ignore="true" />
<c:forEach var="script" items="${additionalJs}">
	<script type="text/javascript"
		src="<c:url value="/static/js/${script}" />"></script>
</c:forEach>
</head>
<body>
	<div class="siteBody">
		<!-- Header -->
		<tiles:insertAttribute name="header" />
		<!-- Menu Page -->
		<tiles:insertAttribute name="menu" />
		<!-- Body Page -->
		<tiles:insertAttribute name="body" />
		<!-- Footer Page -->
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>
