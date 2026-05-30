const area = document.getElementById("areaUpload");
const input = document.getElementById("inputImagem");
const preview = document.getElementById("preview");
const texto = document.getElementById("texto");


area.addEventListener("click", () => {
    input.click();
});


input.addEventListener("change", function() {
    const arquivo = this.files[0];

    if (arquivo) {
        const leitor = new FileReader();

        leitor.onload = function(e) {
            preview.src = e.target.result;
            preview.style.display = "block";
            texto.style.display = "none";
        }

        leitor.readAsDataURL(arquivo);
    }
});



