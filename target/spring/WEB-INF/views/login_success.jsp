<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head> 
	<title>Task-Tracker</title>
	<link href="<c:url value="/css/body.css" />" rel="stylesheet"> 
</head>
<body class="bodybuilder">
	<%@include file="header.jsp" %>
	<h1><spring:message code="label.grat" /> : ${url}</h1>
	<h1><spring:message code="label.message" /> : ${message}</h1>
 
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
	
	<%@include file="footer.jsp" %> 
</body>
</html>