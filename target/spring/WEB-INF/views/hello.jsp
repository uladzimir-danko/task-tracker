<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<html>
<head>
	<title>Task-Tracker</title>
	<%@ page isELIgnored="false" %>
	<link href="<c:url value="/css/body.css" />" rel="stylesheet">
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
    function doAjax() {
      $.ajax({
        url: 'time.html',
        data: ({name : "me"}),
        success: function(data) {
          $('#time').html(data);
        }
      });
    }
  </script>
</head>
<body class="bodybuilder">
	<%@include file="header.jsp" %>
	<h1>${title}</h1>
 
	<sec:authorize access="hasRole('ROLE_DEVELOPER')">
		<!-- For login user -->
 
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>
				${pageContext.request.userPrincipal.name} , <spring:message code="label.hello"/>  				
			</h2>
		</c:if> 
	</sec:authorize>
	
	<button id="demo" onclick="doAjax()" title="Button">Get the time!</button>
	<div id="time">
	</div>
	
	<%@include file="footer.jsp" %>
</body>
</html>