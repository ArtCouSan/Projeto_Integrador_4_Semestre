function myFunction() {
    var x = document.getElementById("myTopnav");
    if (x.className === "topnav") {
        x.className += " responsive";
    } else {
        x.className = "topnav";
    }
}

$(document).ready(function () {

    $('#Meus_DadosB').click(function () {
        $('#Meus_Dados').css("display", "block");
        $('#Meus_Pedidos').css("display", "none");
        $('#Meu_Historico').css("display", "none");
    });

    $('#Meus_PedidosB').click(function () {
        $('#Meus_Dados').css("display", "none");
        $('#Meus_Pedidos').css("display", "block");
        $('#Meu_Historico').css("display", "none");
    });

    $('#Meu_HistoricoB').click(function () {
        $('#Meus_Dados').css("display", "none");
        $('#Meus_Pedidos').css("display", "none");
        $('#Meu_Historico').css("display", "block");
    });


});
