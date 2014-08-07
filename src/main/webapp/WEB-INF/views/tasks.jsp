<%@page contentType="text/html" pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8">
	<title><spring:message code="label.title" /></title>
	
<title>Spring pagination using data tables</title>
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.0/css/jquery.dataTables.css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jtable.js"></script>
<script type="text/javascript">

$(document).ready(function() {
     
    $("#tasks").dataTable( {
        "bProcessing": false,
        "bServerSide": false,
        "sort": "task_id",
        "sAjaxSource": "springTaskPaginationDataTables/" + "${projectname}",
        
        "aoColumns": [
            { "mData": "task_id", "mRender": function(data, type, full) { 
        			return '<a href="tasks/' + data + '">' + 'Link</a>'
   				}            	
            },
            { "mData": "description" }, 
            { "mData": "username" },
            { "mData": "status", "mRender": function(data, type, full) { 
            	if(!status) {
            		return '<spring:message code="label.active" />' }
            	else {
            		return '<spring:message code="label.complete" />' }
       			}
            },
        ]
    } );

} );

</script>
	
</head>
<body class="bodybuilder">

<%@include file="header.jsp" %>

<form:form action="" method="GET">
<h2><spring:message code="label.tasks" /></h2>
<table width="100%" style="border: 3px;background: rgb(243, 244, 248);"><tr><td>
    <table id="tasks" class="display" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th><spring:message code="label.taskName" /></th>
                <th><spring:message code="label.description" /></th> 
                <th><spring:message code="label.developer" /></th>
                <th><spring:message code="label.status" /></th>
            </tr>
        </thead>       
    </table>
    </td></tr></table>
</form:form>

<%@include file="footer.jsp" %>
</body>
</html>