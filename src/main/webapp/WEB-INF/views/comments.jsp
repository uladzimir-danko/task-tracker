<%@page contentType="text/html" pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8">
	<title>${currentTask.getTaskname()}</title>
	
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.0/css/jquery.dataTables.css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jtable.js"></script>
<script type="text/javascript">

$(document).ready(function() {
     
    $("#comments").dataTable( {
        "bProcessing": false,
        "bServerSide": false,
        //"sort": "comment_id",
        "sAjaxSource": "springCommentsPaginationDataTables/" + "${task_id}",        
        "aoColumns": [
            { "mData": "description" }, 
            {
                "mData": "comment_id", "mRender": function(data, type, full) { 
                  	return '<a href="editComment/' + data
                  		+ '" class="editor_remove"><spring:message code="label.edit" /></a>'
                 }
            },
            {
               "mData": "comment_id", "mRender": function(data, type, full) { 
                 	return '<a href="deleteComment/' + data
                 		+ '" class="editor_remove"><spring:message code="label.delete" /></a>'
                }
            }
        ]
    } );

} );

</script>
	
</head>
<body class="bodybuilder">

<%@include file="header.jsp" %>

<h1><spring:message code="label.task" /> : ${currentTask.getTaskname() } </h1>
<div><spring:message code="label.status" /> : ${currentTask.getStatus() } </div>
<div><spring:message code="label.developer" /> : ${currentTask.getUsername() }</div>

<form:form method="post" action="/spring/editTask/${task_id}" modelAttribute="task">
	
	<sec:authorize access="hasRole('ROLE_DEVELOPER')">
		<spring:message code="label.status" />
		<form:select path="status" items="${statusList}" />
	</sec:authorize>
	
	<sec:authorize access="hasRole('ROLE_MANAGER')">
		<spring:message code="label.developer" />
		<form:select path="username" items="${userList}" />
	</sec:authorize>
	
	<input type="submit"
				value="<spring:message code="label.edit"/>" />
</form:form>

<h2><spring:message code="label.comments" /></h2>

<a class="log" href="/spring/tasks?projectname=${currentTask.getProject().getProjectname()}" >
	<button><spring:message code="label.toProject" /></button>
</a>

<a class="log" href="/spring/addComment/${task_id}" >
	<button><spring:message code="label.addComment" /></button>
</a>

<form:form action="" method="GET">
<table width="100%" style="border: 3px;background: rgb(243, 244, 248);"><tr><td>
    <table id="comments" class="display" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th><spring:message code="label.comment" /></th> 
                <th width="80px"></th>
                <th width="80px"></th>
            </tr>
        </thead>       
    </table>
    </td></tr></table>
</form:form>

<%@include file="footer.jsp" %>
</body>
</html>