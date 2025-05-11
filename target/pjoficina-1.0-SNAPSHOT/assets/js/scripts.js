/* Funções principais */
function toggleEdicao(id) {
    const form = document.getElementById('form-edicao-' + id);
    form.style.display = form.style.display === 'none' ? 'table-row' : 'none';
}

function searchCarro() {
    const tipoBusca = document.getElementById('search-option').value;
    const valorBusca = document.getElementById('search-input').value;
    window.location.href = `CarroController?op=Buscar&tipoBusca=${tipoBusca}&valorBusca=${encodeURIComponent(valorBusca)}`;
}

function confirmarExclusao() {
    return confirm('Deseja realmente excluir este veículo?');
}

function redirecionarComRefresh() {
    window.location.href = 'CarroController?op=ConsultarTodos';
    return false;
}

window.exportToExcel = function () {
    const table = document.querySelector(".data-table");
    const html = table.outerHTML;

    const blob = new Blob([html], {type: "application/vnd.ms-excel"});
    const link = document.createElement("a");
    link.href = URL.createObjectURL(blob);
    link.download = "veiculos.xls";
    link.click();
};


function validarFormulario(formElement) {
    const form = formElement || document.querySelector('form.vehicle-form');
    const placaInput = form.querySelector('[name="txtplaca"]');
    const anoInput = form.querySelector('[name="txtano"]');
    const telefoneInput = form.querySelector('[name="txttelefone"]');
    if (placaInput) {
        const placa = placaInput.value.replace(/-/g, '');
        if (!/^[A-Z]{3}[0-9][A-Z0-9][0-9]{2}$/i.test(placa) &&
            !/^[A-Z]{3}[0-9]{4}$/i.test(placa)) {
            alert('Placa inválida. Formato esperado: ABC1D23 ou ABC1234\nA placa deve ser única.');
            placaInput.focus();
            return false;
        }
    }
    if (anoInput) {
        const ano = parseInt(anoInput.value);
        if (isNaN(ano)) {
            alert('Ano deve ser um número válido');
            anoInput.focus();
            return false;
        }
        const anoAtual = new Date().getFullYear();
        if (ano > anoAtual) {
            alert(`Ano não pode ser maior que ${anoAtual}`);
            anoInput.focus();
            return false;
        }
    }
    if (telefoneInput && telefoneInput.value.trim() !== '') {
        const telefone = telefoneInput.value.replace(/\D/g, '');
        if (!/^(\d{10}|\d{11})$/.test(telefone)) {
            alert('Telefone inválido. Deve conter 10 ou 11 dígitos (incluindo DDD)');
            telefoneInput.focus();
            return false;
        }
    }
    return true;
}


function aplicarMascaras() {
    const placaInput = document.getElementById('txtplaca');
    if (placaInput) {
        placaInput.addEventListener('input', function (e) {
            let value = e.target.value.replace(/-/g, '').toUpperCase();
            if (value.length > 7)
                value = value.substring(0, 7);
            if (value.length > 3)
                value = value.substring(0, 3) + '-' + value.substring(3);
            e.target.value = value;
        });
    }

    const telefoneInput = document.getElementById('txttelefone');
    if (telefoneInput) {
        telefoneInput.addEventListener('input', function (e) {
            let value = e.target.value.replace(/\D/g, '');
            if (value.length > 0) {
                if (value.length <= 2) {
                    value = `(${value}`;
                } else if (value.length <= 6) {
                    value = `(${value.substring(0, 2)}) ${value.substring(2)}`;
                } else if (value.length <= 10) {
                    value = `(${value.substring(0, 2)}) ${value.substring(2, 6)}-${value.substring(6, 10)}`;
                } else {
                    value = `(${value.substring(0, 2)}) ${value.substring(2, 7)}-${value.substring(7, 11)}`;
                }
            }
            e.target.value = value;
        });

        telefoneInput.addEventListener('keydown', function (e) {
            if (e.key === 'Backspace' && this.selectionStart === 1) {
                e.preventDefault();
            }
        });
    }
}


function confirmarDados(formElement) {
    const form = formElement || document.querySelector('form.vehicle-form');
    const marca = form.querySelector('[name="txtmarca"]').value;
    const modelo = form.querySelector('[name="txtmodelo"]').value;
    const placa = form.querySelector('[name="txtplaca"]').value;
    const placaDisplay = placa.replace(/-/g, '');
    return confirm(`Confirme os dados:\n\nMarca: ${marca}\nModelo: ${modelo}\nPlaca: ${placaDisplay}\n\nDeseja continuar?`);
}




document.addEventListener('DOMContentLoaded', function () {
    aplicarMascaras();
});