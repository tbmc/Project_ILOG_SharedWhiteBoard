<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- Include header --%>
<jsp:include page="/WEB-INF/header.jsp">
    <jsp:param name="title" value="Tableau partagé" />
</jsp:include>

<div style="margin-top: 15px;">
    <div class="col-xs-12 col-md-4 col-lg-2">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <div style="float: right; margin-top: -5px;">
                    <button
                            type="button"
                            class="btn btn-link"
                            data-toggle="tooltip"
                            data-placement="bottom"
                            title="Retourner à la page de connexion"
                            onclick="location.href = '/';"
                    >
                        <i class="glyphicon glyphicon-menu-hamburger" style="color: white;"></i>
                    </button>
                </div>
                <div id="boardName">
                    Aucun tableau sélectionné
                </div>
            </div>
            <div class="panel-body">
                <ul id="userList" class="list-group">
                    <li class="list-group-item">
                        Aucun utilisateur connecté
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="col-xs-12 col-md-8 col-lg-10">
    <div class="panel panel-primary">
        <div class="panel-body">

            <div class="">
                <div class="">
                    <canvas
                            id="canvas"
                            style="border: 1px solid gray; width: 100%; height: 80vh;"

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

        </div>
    </div>
</div>







<div class="modal fade" tabindex="-1" role="dialog" id="modalErrorNotConnected">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                Erreur !
            </div>
            <div class="modal-body">
                Il semblerait que vous ne soyez pas connecté.
                Pour remédier à ce problème vous devez
                vous connecter depuis la page de connexion.
            </div>
            <div class="modal-footer">
                <div style="text-align: center;">
                    <button type="button" class="btn btn-primary" id="btnModalRedirectToConnectionPage">
                        Rediriger vers la page de connexion
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="/lib/ColorCanvas/colorcanvas.min.js"></script>
<script src="/lib/ColorCanvas/colorcanvas.input.min.js"></script>
<%-- Include the script of the page --%>
<script src="/lib/scripts/BoardPage.js"></script>

<%-- Include the footer --%>
<%@ include file="WEB-INF/footer.jsp" %>