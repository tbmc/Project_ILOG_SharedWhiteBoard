

$('#radioSelectNew').click(function() {
  $('#createNewBoard').prop("disabled", false);
});
$('#radioSelectSession').click(function() {
  $('#createNewBoard').prop("disabled", true);
});
