

function disableFirst(first) {
  var t1 = "createNewBoard", t2 = "joinSession";
  $('#' + t1 + " input").prop("disabled", first);
  $("#" + t1 + " select").prop("disabled", first).selectpicker("refresh");

  $('#' + t2 + " input").prop("disabled", !first);
  $("#" + t2 + " select").prop("disabled", !first).selectpicker("refresh");
  
}

function visibleTerm(visible) {
  return visible ? "visible" : "hidden";
}




$(function() {
  $('#radioSelectNew').click(function() {
    disableFirst(false);
  });
  $('#radioSelectSession').click(function() {
    disableFirst(true);
  });
  disableFirst(false);
});





$(function() {
  var fn = _.debounce(function() {
    
    var bn = ($("#radioSelectNew").is(":checked") ? $("#boardName").val() : $("#sessionName").val() ) || "";
    var pseudo = $("#pseudo").val();
    
    $.get("/api/board-exists", {
      name: bn,
      pseudo: pseudo,
    }).always(function(data) {
      if(!data) data = {};
      if(pseudo.length > 0)
        setFeedback("pseudo", !data.pseudo);
      if(bn.length > 0)
        setFeedback("name", !data.name);
      
      if($("#radioSelectNew").is(":checked"))
        $("#createBoardButton").prop("disabled", (pseudo.length < 1 || data.pseudo || data.name));
      $("#joinSessionButton").prop("disabled", pseudo.length < 1 || data.pseudo || !data.name);
    });
    
  }, 300);
  
  $("#pseudo").on("input", fn);
  $("#sessionName").on("change", fn);
  $("#boardName").on("input", fn);
  $("#radioSelectSession").click(fn);
  $("input[type='radio']").on("change", fn);
  
  fn();
  
});

function setFeedback(id, ok) {
  var pf = $("#" + id + "Feedback");
  var pfg = $("#" + id + "FeedbackGlyphicon");
  
  if(ok) {
    pf.removeClass("has-error");
    pf.addClass("has-success");
    pfg.css("visibility", "visible");
  }else {
    pf.removeClass("has-success");
    pf.addClass("has-error");
    pfg.css("visibility", "hidden");
  }
}


$(function() {
  $.get("/api/board-list").done(function(data) {
    var sn = $("#sessionName");
    sn.empty();
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





