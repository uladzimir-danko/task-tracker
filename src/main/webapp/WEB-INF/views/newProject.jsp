<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page session="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value="/js/javascript.js" />" /></script>
<link href="<c:url value="/css/body.css" />" rel="stylesheet">
<title>New Project</title>
<style>
	.err {
  	  color: #ff0000;
  	  background-color: none;
	}
</style>
</head>
<body class="bodybuilder">

	<%@include file="header.jsp" %>

	<div >	
	
	<h3><spring:message code="label.newProject" /></h3>
	
	<c:if test="${not empty msg}">
			<div class="error">${msg}</div>
		</c:if>
		
	<c:if test="${pageContext.request.userPrincipal.name != null}">
	<form:form method="post" action="newProject" modelAttribute="project">

	<table>
		<tr>
			<td><spring:message code="label.projectsName" /></td>
			<td><form:input path="projectname" /></td>
		</tr>
		<tr>
			<td><spring:message code="label.description" /></td>
			<td><form:textarea path="description" /></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit"
				value="<spring:message code="label.add"/>" /></td>
		</tr>
	</table>
	</form:form>
	</c:if>
	</div>

<%@include file="footer.jsp" %>

</body>