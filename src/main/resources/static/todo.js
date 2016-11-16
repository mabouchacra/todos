$(document).ready(function() {
    $.ajax({
        url: "/todos"
    }).then(function(data) {
       $('.todos').append(data[0].responsable);
        console.log(data[0].responsable);
    });
});