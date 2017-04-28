<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head lang="fr">

    <title>
        <%
            String s = request.getParameter("title");
            if(s != null)
                out.print(s);
        %>
    </title>

    <link type="text/css" href="<c:url value="/lib/bootstrap-3.3.7/css/bootstrap.min.css" />">
    <link type="text/css" href="<c:url value="/lib/bootstrap-3.3.7/css/bootstrap-theme.min.css" />">
</head>
<body>
