/**
 * Created by Vlad on 24.06.2016.
 */

$(document).ready(function(){
    $('.modal-trigger').leanModal();
    $('select').material_select();
});

function BeforeSubmit(element){
    var input = $("<input>").attr("type", "hidden").attr("name", "product_id").val($(element).attr('id'));
    $(element).append($(input));
    window.location.replace("index.jsp");
}

function BeforeAdd(element){
    var input = $("<input>").attr("type", "hidden").attr("name", "goodsType").val($("#goodsType").val());
    $(element).append($(input));
    //window.location.replace("index.jsp");
}

function AddFields(element) {
    var form = $(element).parent();

    $('<div class="row">' +
    '<div class="input-field col s6">' +
    '<input type="text" class="validate" onkeyup="AddNameChange(this)">' +
    '</div>' +
    '<div class="input-field col s6">' +
    '<input type="text" class="validate">'+
    '</div>').insertBefore($( "#fieldAdd" ));

    /*form.insertBefore('<div class="row">' +
        '<div class="input-field col s6">' +
        '<input type="text" class="validate" onkeyup="AddNameChange(this)">' +
        '</div>' +
        '<div class="input-field col s6">' +
        '<input type="text" class="validate">'+
        '</div>',);*/
}

function AddNameChange(element){
    var inputToChange = $(element).parent().next().children().eq(0);
    inputToChange.attr("name", $(element).val());
}

function Search() {
    var searchText = $("#icon_prefix").val().toLowerCase();
    var cardContent = $(".card-content");
    
    for(var i = 0; i < cardContent.length; i++) {
        if(cardContent.eq(i).text().toLowerCase().indexOf(searchText)==-1){
            cardContent.eq(i).parent().parent().hide();
        } else {
            cardContent.eq(i).parent().parent().show();
        }
    }
}

function NewField(element) {
    var tbody = $(element).parent().children().eq(0).children().eq(1);
    tbody.append('<tr><td><input type="text" class="validate" value="attr_name" onkeyup="NameChange(this)"></td><td><input type="text" class="validate" value="attr_value"></td></tr>');
}

function NameChange(element){
    var attrvalueInput = $(element).parent().parent().children().eq(1).children().eq(0);
    attrvalueInput.attr("name", $(element).val());
}
