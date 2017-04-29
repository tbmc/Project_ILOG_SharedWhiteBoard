

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

$('#radioSelectNew').click(function() {
  disableFirst(false);
});
$('#radioSelectSession').click(function() {
  disableFirst(true);
});

$(function() {
  disableFirst(false);
});