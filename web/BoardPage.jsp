<%-- Include header --%>
<jsp:include page="/WEB-INF/header.jsp">
    <jsp:param name="title" value="Tableau partagé" />
</jsp:include>




<canvas id="canvas" style="border: 1px solid gray;">
</canvas>





<%-- Include the script of the page --%>
<script src="/lib/scripts/BoardPage.js"></script>

<%-- Include the footer --%>
<%@ include file="WEB-INF/footer.jsp" %>