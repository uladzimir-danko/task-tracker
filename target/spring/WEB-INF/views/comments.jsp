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
     
    $("#comments").dataTable( {
        "bProcessing": false,
        "bServerSide": false,
        "sort": "comment_id",
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

<a class="log" href="/spring/addComment/${task_id}" >
		<button><spring:message code="label.addComment" /></button>
	</a>

<form:form action="" method="GET">
<h2><spring:message code="label.comments" /></h2>
<table width="100%" style="border: 3px;background: rgb(243, 244, 248);"><tr><td>
    <table id="comments" class="display" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th><spring:message code="label.comment" /></th> 
                <th></th>
                <th></th>
            </tr>
        </thead>       
    </table>
    </td></tr></table>
</form:form>

<%@include file="footer.jsp" %>
</body>
</html>