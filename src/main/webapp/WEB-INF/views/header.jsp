<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<html>
<head> 
	<link href="<c:url value="/css/header.css" />" rel="stylesheet"> 
	<script> 
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
	<style type="text/css">
   A {
    text-decoration: none;
   } 
  </style>
</head>
<body link="black" vlink="black" alink="black">

<table class="table">
	<tr>
		<td width=90px>
			<a class="logo" href="/spring/"><img src="<c:url value="/images/haus.jpg" />" /></a> 
			
		</td>
		
		<td width=300px>
			<h2><a href="/spring/">Example Spring Application </a></h2>
		</td>
	
		<td>

<div class="btn-group">	
	
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
				<form action="${logoutUrl}" method="post" id="logoutForm">
				<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
				</form>
 
				<c:if test="${pageContext.request.userPrincipal.name != null}">
			 		
			 		<a class="log"	href="javascript:formSubmit()"><button>
			 			<spring:message code="label.logout" /></button>
			 		</a>
			 		
					</c:if>
				<c:if test="${pageContext.request.userPrincipal.name == null}">
					<a class="log" href="/spring/login">
						<button><spring:message code="label.login" /></button>
					</a>
				</c:if>
	
	<sec:authorize access="hasRole('ROLE_DEVELOPER')">
	<a class="log" href="/spring/index" >
		<button><spring:message code="label.users" /></button>
	</a>

	<a class="log" href="/spring/projects" >
		<button><spring:message code="label.projects" /></button>
	</a>
	</sec:authorize>	
	
	<sec:authorize access="hasRole('ROLE_MANAGER')">
		
		<a class="log" href="/spring/admin">
			<button><spring:message code="label.admin" /></button>
		</a>					
	</sec:authorize>
	 			
	<span style="float: right;">  
    				<a class="language"  href="/spring/welcome?language=en">en</a>  
    				|  
    				<a class="language" href="/spring/welcome?language=ru">ru</a>  
				</span> 
</div>	 
		</td>

	</tr>
</table>
</body>
</html>