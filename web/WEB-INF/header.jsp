<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="fr">

    <%-- By default, width of the page is the width of the device --%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%-- Set the content-type and the charset --%>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">

    <%-- Show the title if title is passed in parameters --%>
    <title>
        <%
            String s = request.getParameter("title");
            if(s != null)
                out.print(s);
        %>
    </title>

    <%-- Add custom stylesheet --%>
    <link rel="stylesheet" href="/lib/styles/all.css">


    <%-- Add the stylesheets form Bootstrap --%>
    <link rel="stylesheet" href="/lib/bootstrap-3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/lib/bootstrap-3.3.7/css/bootstrap-theme.min.css">

    <%-- Add the stylesheet from Bootstrap-Select --%>
    <link rel="stylesheet" href="/lib/bootstrap-3.3.7/bootstrapSelect/css/bootstrap-select.min.css">

    <%--
    Add JQuery script
    It has to be included here because JQuery is heavily used
    in the rest of the page and JQuery has to be defined
    --%>
    <script src="/lib/jquery/jquery-3.2.1.min.js"></script>

    <%-- Add ColorCanvas stylesheet --%>
    <link rel="stylesheet" href="/lib/ColorCanvas/colorcanvas.css">

</head>
<%--
Beginning of the body
Body is closed in "footer.jsp" file
--%>
<body>
