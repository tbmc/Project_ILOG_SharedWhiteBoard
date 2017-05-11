<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- Include header --%>
<jsp:include page="/WEB-INF/header.jsp">
    <jsp:param name="title" value="Tableau partagé" />
</jsp:include>

<div class="container">
    <div class="row row-margin">
        <canvas
            id="canvas"
            style="border: 1px solid gray;"
            width="1000"
            height="500"
        >
        </canvas>
    </div>
    <div class="row row-margin">
        <input type="button" class="btn btn-primary" id="buttonLine" value="Line">
        <input type="button" class="btn btn-primary" id="buttonCircle" value="Circle">
        <input type="button" class="btn btn-primary" id="buttonRect" value="Rectangle">
    </div>
    <div class="row row-margin">
        <div class="btn-group" data-toggle="buttons">
            <label class="btn btn-primary">
                <input type="checkbox" id="buttonFill">
                <span>Fill</span>
            </label>
        </div>
        <input type="button" class="btn btn-primary" id="buttonClear" value="Clear">
    </div>
    <div class="row row-margin">
        <input type="color" id="colorPicker" value="#000000">
    </div>
</div>

<script src="/lib/ColorCanvas/colorcanvas.min.js"></script>
<script src="/lib/ColorCanvas/colorcanvas.input.min.js"></script>
<%-- Include the script of the page --%>
<script src="/lib/scripts/BoardPage.js"></script>

<%-- Include the footer --%>
<%@ include file="WEB-INF/footer.jsp" %>