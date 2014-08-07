<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page session="true"%>
<html>
<head>
<title>Login Page</title>
<link href="<c:url value="/css/login.css" />" rel="stylesheet">
<link href="<c:url value="/css/body.css" />" rel="stylesheet">
</head>
<body class="bodybuilder" onload='document.loginForm.username.focus();'>
	<%@include file="header.jsp" %>
 
	<div>
 
		<h3><spring:message code="label.login_title" /></h3>
 
		<c:if test="${not empty error}">
			<div class="error"><spring:message code="${error}" /></div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg"><spring:message code="${msg}" /></div>
		</c:if>
 		
 		<c:if test="${pageContext.request.userPrincipal.name == null}">
		<form name='loginForm'
		  action="<c:url value='/j_spring_security_check' />" method='POST'> 
 		
		<table>
			<tr>
				<td><spring:message code="label.user" />:</td>
				<td><input type='text' name='username'></td>
			</tr>
			<tr>
				<td><spring:message code="label.password" />:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
				  value=<spring:message code="label.submit" /> /></td>
			</tr>
		  </table>		  
 
		  <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
 
		</form>
		</c:if>
	</div>
	
	<%@include file="footer.jsp" %>
 
</body>
</html>