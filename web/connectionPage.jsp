<jsp:include page="/WEB-INF/header.jsp">
    <jsp:param name="title" value="Page de connexion" />
</jsp:include>



<div class="center-block" style="max-width: 1000px; margin-top: 100px;">
    <div class="panel panel-primary">
        <div class="panel-heading">Options de connexion</div>
        <div class="panel-body">
            <form>
                <div class="form-group">
                    <label for="pseudo">Entrer votre pseudo</label>
                    <input type="text" class="form-control" id="pseudo" placeholder="Pseudo" name="pseudo">
                </div>
                <div class="form-group" id="selectionGroup">
                    <div class="col-md-6 col-xs-12">
                        <div class="panel panel-default">
                            <div class="panel-body">

                                <div class="center-block p-s">
                                    <label>
                                        <input type="radio" name="selectAction" value="new" id="radioSelectNew" checked>
                                        Créer un nouveau tableau
                                    </label>
                                </div>

                                <div class="p-s" id="createNewBoard">

                                    <div class="form-group">
                                        <label for="boardName">
                                            Entrer le nom que vous désirez pour ce tableau
                                        </label>
                                        <input type="text" class="form-control" id="boardName" placeholder="Nom du tableau" name="boardName">
                                    </div>

                                    <div class="text-center submitDiv">
                                        <input type="submit" value="Créer" class="btn btn-default">
                                    </div>

                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-xs-12">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="center-block p-s">
                                    <label>
                                        <input type="radio" name="selectAction" id="radioSelectSession" value="session">
                                        Rejoindre un tableau
                                    </label>
                                </div>

                                <div class="p-s" id="joinSession">

                                    <select class="selectpicker" data-live-search="true" name="sessionName">
                                        <option>Test</option>
                                        <option>Truc</option>
                                    </select>

                                    <div class="text-center submitDiv">
                                        <input type="submit" value="Rejoindre" class="btn btn-default">
                                    </div>

                                </div>

                            </div>
                        </div>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>





<script src="/lib/scripts/connectionPage.js"></script>

<%@ include file="WEB-INF/footer.jsp" %>