

/**
 * Disable left or right
 * @param leftPanel
 */
function disableLeftOrRightPanel(leftPanel) {
  
  // Names of the panels
  var createNewBoard = "createNewBoard", joinSession = "joinSession";
  
  // Disable all the input in the panel
  $('#' + createNewBoard + " input").prop("disabled", leftPanel);
  // Disable all the select elements in the panel
  $("#" + createNewBoard + " select").prop("disabled", leftPanel).selectpicker("refresh");
  
  // Same as for createNewBoard
  $('#' + joinSession + " input").prop("disabled", !leftPanel);
  $("#" + joinSession + " select").prop("disabled", !leftPanel).selectpicker("refresh");
  
}

function visibleTerm(visible) {
  return visible ? "visible" : "hidden";
}



// When the body of the page is fully loaded
$(function() {
  /**
   * When user clicks on radio button to select a panel
   * disable the other one
   */
  $('#radioSelectNew').click(function() {
    disableLeftOrRightPanel(false);
  });
  $('#radioSelectSession').click(function() {
    disableLeftOrRightPanel(true);
  });
  
  // Disable right panel on the beginning because it's the right part who is selected by default
  disableLeftOrRightPanel(false);
});





$(function() {
  
  var pseudoHasAlreadyChanged = false;
  var nameHasAlreadyChanged = false;
  
  // Create a debounced function that fire when the function is called at least once
  // and the function is not called for 300ms
  var fn = _.debounce(function() {
    
    // Get the session name, it depends on which radio button is selected
    var name = ($("#radioSelectNew").is(":checked") ? $("#boardName").val() : $("#sessionName").val() ) || "";
    var pseudo = $("#pseudo").val();
  
    /**
     * Send an http request to server to check if the names are taken
     */
    $.get("/api/board-exists", {
      // Parameters
      name: name,
      pseudo: pseudo,
    })
      // Call the callback whether it success or it fail
    .always(function(data) {
      // If the data is null, it can crash, so it prevent this
      if(!data) data = {};
      // If the length is 0 the user hasn't type anything so it's not an error
      if(pseudo.length > 0 || pseudoHasAlreadyChanged) {
        setFeedback("pseudo", !data.pseudo);
        pseudoHasAlreadyChanged = true;
      }
      if(name.length > 0 || nameHasAlreadyChanged) {
        setFeedback("name", !data.name);
        nameHasAlreadyChanged = true;
      }
      
      // Disable the button of the form if one data has an error
      if($("#radioSelectNew").is(":checked"))
        $("#createBoardButton").prop("disabled", pseudo.length < 1 || data.pseudo || data.name);
      else
        $("#joinSessionButton").prop("disabled", pseudo.length < 1 || data.pseudo || !data.name);
    });
    
  }, 300);
  
  // Add listener on a component to refresh the error each time it occurs a change
  $("#pseudo").on("input", fn);
  $("#sessionName").on("change", fn);
  $("#boardName").on("input", fn);
  $("#radioSelectSession").click(fn);
  $("input[type='radio']").on("change", fn);
  
  // Call the function at the beginning to disable the button because data aren't valid
  fn();
  
});

/**
 * Indicates if the input has an error or is a success
 * @param id of the input
 * @param ok boolean, true if success and false if fail
 */
function setFeedback(id, ok) {
  // Get all the components corresponding at the id
  var pf = $("#" + id + "Feedback");
  var pfgo = $("#" + id + "FeedbackGlyphiconOk");
  var pfge = $("#" + id + "FeedbackGlyphiconError");
  
  if(ok) {
    // If success
    
    // Remove error class
    pf.removeClass("has-error");
    // Hide error glyphicon
    pfge.css("visibility", "hidden");
    // Add success class
    pf.addClass("has-success");
    // Show success glyphicon
    pfgo.css("visibility", "visible");
  }else {
    // If fail, do the inverse of the success
    pf.removeClass("has-success");
    pf.addClass("has-error");
    pfgo.css("visibility", "hidden");
    pfge.css("visibility", "visible");
  }
}

/**
 * When the body is fully loaded
 */
$(function() {
  /**
   * Send a request to the server to get the list of sessions
   */
  $.get("/api/board-list").done(function(data) {
    // Get the component showing session list
    var sn = $("#sessionName");
    // Empty the list
    sn.empty();
    // Add data received to the list
    _.forEach(data, function(e) {
      sn.append("<option>" + e + "</option>");
    });
  });
});


$(function() {
  $("#createBoardButton").click(function() {
    var bn = $("#boardName").val();
    $.post("/api/board-create", {
      name: bn
    }).done(function(data) {
    
    }).fail(function(data) {
      $("#alertErrorCreateBoard").css("display", "block");
      setTimeout(function() {
        $("#alertErrorCreateBoard").css("display", "none");
      }, 5000);
    });
    
  });
});





