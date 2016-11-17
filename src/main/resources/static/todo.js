$(document).ready(function() {
    $.ajax({
        url: "/todos"
    }).then(function(data) {
       for (var item in data) {
        $('.todos').append(getLineItem(data[item]));
       }
    }).fail(function() {
        //$('.alert-danger').show();
    });
    $('.alert').hide();
    $('#create-button').addClass('disabled');



       $('#todo-text').on('change keyup paste', function() {
            if($('#todo-text').val().length > 0) {
                $('#create-button').removeClass('disabled');
            } else {
                $('#create-button').addClass('disabled');
            }

       });

     $('#create-button').click(function() {
        if(!$('#create-button').hasClass( "disabled" )) {
                 var dataSend = {};
                 dataSend["texte"] = $('#todo-text').val();
                 $.ajax({
                    type: "POST",
                    url: "/todos",
                    contentType: 'application/json',
                    data : JSON.stringify(dataSend),
                    dataType: 'json'
                 }).then(function(data) {
                    $('.todos').prepend(getLine(data.id,dataSend.texte,'ACTIVE'));
                    $('.alert-success').show();
                 }).fail(function() {
                       $('.alert-danger').show();
                   });
          }
        });

    function getLineItem(item) {
        return getLine(item.id,item.texte,item.status);
    }

    function getLine(id,texte,status) {
        var template = '<li class="list-group-item">'+
                '<span class="todos_id hidden"><strong>'+id+'</strong></span>'+
                '<span class="todos_text">'+texte+'</span>'+
                '<span class="todos_status badge">'+status+'</span>'+
            '</li>';
        return template;
    }

});