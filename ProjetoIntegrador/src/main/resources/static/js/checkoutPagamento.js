$(document).ready(function () {

    $(mascara = function (t, mask) {
        var i = t.value.length;
        var saida = mask.substring(1, 0);
        var texto = mask.substring(i)
        if (texto.substring(0, 1) != saida) {
            t.value += texto.substring(0, 1);
        }
    });
});

$('#qntParcelas').keyup(function () {
    $('.total').html('');
    var qnt = $('#qntParcelas').val();
    var total = 900/qnt.toString();
    var round = total.toFixed(2);
    if(qnt === '') {
        $('.total').append('<p> 1 X R$ 900.00 </p>');
    } else {
        $('.total').append('<p>' + qnt + ' X R$ '+ round +'</p>');
    }
});