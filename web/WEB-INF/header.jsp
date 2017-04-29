<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head lang="fr">

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">

    <title>
        <%
            String s = request.getParameter("title");
            if(s != null)
                out.print(s);
        %>
    </title>

    <link rel="stylesheet" href="<c:url value="/lib/styles/all.css" />">
    <link rel="stylesheet" href="<c:url value="/lib/bootstrap-3.3.7/css/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/lib/bootstrap-3.3.7/css/bootstrap-theme.min.css" />">
    <link rel="stylesheet" href="<c:url value="/lib/bootstrap-3.3.7/bootstrapSelect/css/bootstrap-select.min.css" />">

    <script src="<c:url value="/lib/jquery/jquery-3.2.1.min.js" />"></script>

</head>
<body>
