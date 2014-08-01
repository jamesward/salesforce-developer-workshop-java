$(function() {

  $.get("/sessions", function(data) {
    $.each(data, function(index, item) {
      var li = $("<li>").text(item.Name).addClass("table-view-cell");
      $('.session-list').append(li);
    });
  });

});