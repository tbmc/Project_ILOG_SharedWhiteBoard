
<%-- Include header --%>
<jsp:include page="WEB-INF/header.jsp">
    <jsp:param name="title" value="Page de connexion" />
</jsp:include>



<%-- Center the block --%>
<div class="center-block" style="max-width: 1000px; margin-top: 100px;">
    <%-- Define a panel with the primary color --%>
    <div class="panel panel-primary">
        <%-- Panel title --%>
        <div class="panel-heading">Options de connexion</div>

        <%-- Panel body --%>
        <div class="panel-body">

            <%-- Beginning of the form --%>
            <form>

                <%-- Input text with feedback and glyphicon --%>
                <div class="form-group has-feedback" id="pseudoFeedback">
                    <label for="pseudo">Entrer votre pseudo</label>

                    <%-- Group necessary to show feedback and glyphicon --%>
                    <div class="input-group" style="width: 100%;">
                        <input type="text" class="form-control" id="pseudo" placeholder="Pseudo" name="pseudo">
                    </div>
                    <%-- Glyphicon success, hidden by default --%>
                    <span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" id="pseudoFeedbackGlyphiconOk" style="visibility: hidden;"></span>
                    <%-- Glyphicon fail, hidden by default --%>
                    <span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" id="pseudoFeedbackGlyphiconError" style="visibility: hidden;"></span>
                </div>


                <div class="form-group" id="selectionGroup">
                    <%-- Half size on medium and large devices and full size for small devices --%>
                    <div class="col-md-6 col-xs-12">
                        <div class="panel panel-default">
                            <div class="panel-body">

                                <%-- Selector of the creating session panel --%>
                                <div class="center-block p-s">
                                    <label>
                                        <input type="radio" name="selectAction" value="new" id="radioSelectNew" checked>
                                        Créer un nouveau tableau
                                    </label>
                                </div> <%-- Center block --%>

                                <div class="p-s" id="createNewBoard">

                                    <%-- Alert to show when there is an error --%>
                                    <div class="alert alert-danger" style="display: none;" id="alertErrorCreateBoard">
                                        <strong>Erreur : </strong> Impossible de créer le salon
                                    </div>

                                    <%-- Group for the input of the session name --%>
                                    <div class="form-group has-feedback" id="nameFeedback">
                                        <label for="boardName" class="control-label">
                                            Entrer le nom que vous désirez pour ce tableau
                                        </label>
                                        <%-- Group necessary to show feedback and glyphicon --%>
                                        <div class="input-group" style="width: 100%;">
                                            <input type="text" class="form-control" id="boardName" placeholder="Nom du tableau" name="boardName">
                                        </div>
                                        <%-- Glyphicon success --%>
                                        <span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true" id="nameFeedbackGlyphiconOk" style="visibility: hidden;"></span>
                                        <%-- Glyphicon error --%>
                                        <span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" id="nameFeedbackGlyphiconError" style="visibility: hidden"></span>
                                    </div> <%-- Group nameFeedback --%>

                                    <%-- Submit button --%>
                                    <div class="text-center submitDiv">
                                        <input type="button" value="Créer" class="btn btn-default" id="createBoardButton">
                                    </div>

                                </div> <%-- Panel create new boards --%>

                            </div> <%-- Panel body --%>
                        </div> <%-- Panel --%>
                    </div> <%-- Block defining width --%>

                    <div class="col-md-6 col-xs-12">
                        <div class="panel panel-default">
                            <div class="panel-body">

                                <%-- Alert to show when there is an error --%>
                                <div class="alert alert-danger" style="display: none;" id="alertErrorJoinBoard">
                                    <strong>Erreur : </strong> Impossible de rejoindre le salon
                                </div>

                                <%-- Selector of the joining session --%>
                                <div class="center-block p-s">
                                    <label>
                                        <input type="radio" name="selectAction" id="radioSelectSession" value="session">
                                        Rejoindre un tableau
                                    </label>
                                </div> <%-- Center block  --%>

                                <div class="p-s" id="joinSession">
                                    <div>
                                        <%-- List of sessions available --%>
                                        <select
                                            <%-- Use Bootstrap-Select --%>
                                            class="selectpicker" data-live-search="true"
                                            name="sessionName" id="sessionName" style="width: 100%;"
                                        >
                                        </select>
                                    </div>

                                    <%-- Submit button --%>
                                    <div class="text-center submitDiv">
                                        <input type="button" value="Rejoindre" class="btn btn-default" id="joinSessionButton">
                                    </div>

                                </div> <%-- Panel join session --%>

                            </div> <%-- Panel body --%>
                        </div> <%-- Panel --%>
                    </div> <%-- Block defining width  --%>

                </div> <%-- Form group containing the 2 panels --%>

            </form> <%-- Form --%>
        </div> <%-- Main panel body --%>
    </div> <%-- Main panel --%>
</div> <%-- Center block --%>




<%-- Include the script of the page --%>
<script src="lib/scripts/ConnectionPage.js"></script>

<%-- Include the footer --%>
<%@ include file="WEB-INF/footer.jsp" %>