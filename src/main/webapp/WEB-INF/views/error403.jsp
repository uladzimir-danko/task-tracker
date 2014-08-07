<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head> <link href="<c:url value="/css/body.css" />" rel="stylesheet"> </head>
<body class="bodybuilder">

	<%@include file="header.jsp" %>
	
	<h1>HTTP Status 403 - Access is denied</h1>
 
	<c:choose>
		<c:when test="${empty username}">
		  <h2>You do not have permission to access this page!</h2>
		</c:when>
		<c:otherwise>
		  <h2>Username : ${username} <br/>
                    You do not have permission to access this page!</h2>
		</c:otherwise>
	</c:choose>
 
 	<%@include file="footer.jsp" %>
</body>
</html>