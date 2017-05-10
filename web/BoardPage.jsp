<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- Include header --%>
<jsp:include page="/WEB-INF/header.jsp">
    <jsp:param name="title" value="Tableau partagé" />
</jsp:include>




<canvas
    id="canvas"
    style="border: 1px solid gray;"
    width="1000"
    height="500"
>
</canvas>
<button id="buttonLine">
    Line
</button>
<button id="buttonCircle">
    Circle
</button>
<button id="buttonBlack">
    Black
</button>
<button id="buttonRed">
    Red
</button>
<button id="buttonClear">
    Clear
</button>


<%-- Include the script of the page --%>
<script src="/lib/scripts/BoardPage.js"></script>

<%-- Include the footer --%>
<%@ include file="WEB-INF/footer.jsp" %>