function main() {
  document.querySelector("#input-imagem").addEventListener("change", campoNome);
}

function campoNome() {
  var input = document.querySelector("#input-imagem");
  var file = input.value.split("\\");
  var fileName = file[file.length-1];
  document.querySelector("#titulo").value = fileName;
}

window.addEventListener("load", main);
