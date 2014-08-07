<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page session="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value="/js/javascript.js" />" /></script>
<link href="<c:url value="/css/body.css" />" rel="stylesheet">
<title>Registration</title>
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
	
	<h3><spring:message code="label.registration" /></h3>
	
	<c:if test="${not empty msg}">
			<div class="error">${msg}</div>
		</c:if>
		
	<form:form method="post" action="registration" modelAttribute="userFormRegistration">

	<table>
		<tr>
			<td><spring:message code="label.name" /></td>
			<td><form:input path="username" /></td>
			<td><form:errors path="username" class="err" /></td>
		</tr>
		<tr>
			<td><spring:message code="label.password" /></td>
			<td><form:password path="password" /></td>
			<td><form:errors path="password" class="err" /></td>
		</tr>
		<tr>
			<td><spring:message code="label.passwordConfirm" /></td>
			<td><form:password path="passwordConfirm" /></td>
			<td><form:errors path="valid" class="err" /></td>
		</tr>
		<tr>
			<td><spring:message code="label.language" /></td>
			<td><form:select path="language" items="${languageList}" /></td>
	
		</tr>
		<tr>
			<td colspan="2"><input type="submit"
				value="<spring:message code="label.registration"/>" /></td>
		</tr>
	</table>
	</form:form>
	</div>

<%@include file="footer.jsp" %>

</body>