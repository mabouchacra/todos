$(document).ready(function() {
    $.ajax({
        url: "/todos"
    }).then(function(data) {
       $('.todos').append(getLine(data[0]));
    });

     $('#create-button').click(function() {
         $.ajax({
            type: "POST",
            url: "/todos",
            data: createItemJson($('#todo-test').val()),
         }).then(function(data) {
            $('.todos').prepend(getLine(data[0]));
         });
     });

    function getLine(item) {
        var template = '<li class="list-group-item">'+
                '<span class="todos_id"><strong>'+item.id+'</strong> - </span>'+
                '<span class="todos_text">'+item.texte+'</span>'+
                '<span class="todos_status badge">'+item.status+'</span>'+
            '</li>';

        return template;
    }

    function createItemJson(texte) {
        var itemJson = '[{"id":"null",' +
            '"texte":"'+texte+'",'+
            '"responsable":"Mounir",'+
            '"status":"ACTIVE"}]'

            return itemJson;
    }

});