<%-- 
    Document   : cadastro
    Created on : 5 de abr. de 2025, 16:29:01
    Author     : levas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Cadastro de Veículo | AutoMech</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <link rel="stylesheet" href="assets/css/styles.css?v=2.0">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="form-card">
                <div class="form-header">
                    <h1><i class="fas fa-car-alt"></i> Cadastrar Novo Veículo</h1>
                    <a href="index.jsp" class="btn-back" onclick="return redirecionarComRefresh()">
                        <i class="fas fa-arrow-left"></i> Voltar
                    </a>
                </div>

                <% if (request.getAttribute("msgErro") != null) {%>
                <div class="alert alert-danger">
                    <i class="fas fa-exclamation-circle"></i> <%= request.getAttribute("msgErro")%>
                </div>
                <% }%>

                <form action="CarroController" method="POST" onsubmit="return validarFormulario() && confirmarDados()" class="vehicle-form">
                    <input type="hidden" name="op" value="Cadastrar">

                    <div class="form-grid">
                        <div class="form-group">
                            <label for="txtmarca" class="required">
                                <i class="fas fa-car"></i> Marca:
                            </label>
                            <input type="text" id="txtmarca" name="txtmarca" required>
                        </div>

                        <div class="form-group">
                            <label for="txtmodelo" class="required">
                                <i class="fas fa-car-side"></i> Modelo:
                            </label>
                            <input type="text" id="txtmodelo" name="txtmodelo" required>
                        </div>

                        <div class="form-group">
                            <label for="txtano" class="required">
                                <i class="fas fa-calendar-alt"></i> Ano:
                            </label>
                            <input type="number" id="txtano" name="txtano" required>
                        </div>

                        <div class="form-group">
                            <label for="txtcor">
                                <i class="fas fa-palette"></i> Cor:
                            </label>
                            <input type="text" id="txtcor" name="txtcor">
                        </div>

                        <div class="form-group">
                            <label for="txtmotor" class="required">
                                <i class="fas fa-cog"></i> Motor:
                            </label>
                            <input type="text" id="txtmotor" name="txtmotor" required>
                        </div>

                        <div class="form-group">
                            <label for="txtplaca" class="required">
                                <i class="fas fa-id-card"></i> Placa:
                            </label>
                            <input type="text" id="txtplaca" name="txtplaca" required>
                        </div>

                        <div class="form-group">
                            <label for="txtdono">
                                <i class="fas fa-user"></i> Proprietário:
                            </label>
                            <input type="text" id="txtdono" name="txtdono">
                        </div>

                        <div class="form-group">
                            <label for="txtcnh">
                                <i class="fas fa-id-card-alt"></i> CNH:
                            </label>
                            <input type="text" id="txtcnh" name="txtcnh">
                        </div>

                        <div class="form-group">
                            <label for="txttelefone">
                                <i class="fas fa-phone"></i> Telefone:
                            </label>
                            <input type="text" id="txttelefone" name="txttelefone">
                        </div>
                    </div>

                    <div class="form-footer">
                        <button type="submit" class="btn btn-submit">
                            <i class="fas fa-save"></i> Cadastrar Veículo
                        </button>
                        <button type="reset" class="btn btn-reset">
                            <i class="fas fa-eraser"></i> Limpar
                        </button>
                    </div>
                </form>
            </div>
        </div>

        <script src="assets/js/scripts.js"></script>
        <script>
                    document.addEventListener('DOMContentLoaded', aplicarMascaras);
        </script>
    </body>
</html>