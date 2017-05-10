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
        <input type="button" class="btn btn-black" id="buttonBlack" value="">
        <input type="button" class="btn btn-default" id="buttonWhite" value="">
        <input type="button" class="btn btn-danger" id="buttonRed" value="">
        <input type="button" class="btn btn-primary" id="buttonBlue" value="">
        <input type="button" class="btn btn-warning" id="buttonOrange" value="">
        <input type="button" class="btn btn-success" id="buttonGreen" value="">
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
</div>

<%-- Include the script of the page --%>
<script src="/lib/scripts/BoardPage.js"></script>

<%-- Include the footer --%>
<%@ include file="WEB-INF/footer.jsp" %>