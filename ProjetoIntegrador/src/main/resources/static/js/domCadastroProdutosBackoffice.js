function main() {
  document.querySelector("#botao-cancelar-novo").addEventListener("click", limparCampos);
  document.querySelector("#fechar-janela-novo").addEventListener("click", limparCampos);

}

function limparCampos() {
  var inputs = [];
  inputs = document.querySelectorAll(".inputs-janela-novo");
  for (var i = 0; i < inputs.length; i++) {
    inputs[i].value = '';
  }
}

window.addEventListener("load", main);
