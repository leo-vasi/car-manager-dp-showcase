<%-- 
    Document   : index
    Created on : 5 de abr. de 2025, 13:18:21
    Author     : levas
--%>

<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.List"%>
<%@page import="com.leo.pjoficina.model.Carro"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Gestão de Veículos | AutoMech</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <link rel="stylesheet" href="assets/css/styles.css?v=3.0">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <header class="page-header">
                <div class="header-content">
                    <h1><i class="fas fa-car-alt"></i> Gestão de Veículos</h1>
                    <div class="header-actions">
                        <a href="cadastro.jsp" class="btn btn-primary">
                            <i class="fas fa-plus"></i> Novo Veículo
                        </a>
                        <a href="CarroController?op=ConsultarTodos" class="btn btn-secondary">
                            <i class="fas fa-sync-alt"></i> Atualizar Página
                        </a>
                    </div>
                </div>
            </header>
            <% if (request.getAttribute("msgSucesso") != null) {%>
            <div class="alert alert-success">
                <i class="fas fa-check-circle"></i> <%= request.getAttribute("msgSucesso")%>
            </div>
            <% } %>
            <% if (request.getAttribute("msgErro") != null) {%>
            <div class="alert alert-danger">
                <i class="fas fa-exclamation-circle"></i> <%= request.getAttribute("msgErro")%>
            </div>
            <% } %>

            <section class="search-section">
                <div class="search-container">
                    <div class="search-options">
                        <label for="search-option"><i class="fas fa-search"></i> Buscar veículos:</label>
                        <select id="search-option" name="tipoBusca" class="form-select">
                            <option value="id">Por ID</option>
                            <option value="placa">Por Placa</option>
                            <option value="cnh">Por CNH</option>
                            <option value="telefone">Por Telefone</option>
                        </select>
                    </div>
                    <div class="search-input-group">
                        <input type="text" id="search-input" name="valorBusca" placeholder="Digite aqui..." class="form-input">
                        <button onclick="searchCarro()" class="btn btn-search">
                            <i class="fas fa-arrow-right"></i>
                        </button>
                    </div>
                </div>
            </section>

            <section class="data-section">
                <div class="section-header">
                    <h2><i class="fas fa-list"></i> Lista de Veículos</h2>
                    <div class="table-actions">
                        <button class="btn btn-export" onclick="exportToExcel()">
                            <i class="fas fa-file-excel"></i> Exportar
                        </button>
                    </div>
                </div>

                <div class="table-responsive">
                    <table class="data-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Marca</th>
                                <th>Modelo</th>
                                <th>Ano</th>
                                <th>Cor</th>
                                <th>Motor</th>
                                <th>Placa</th>
                                <th>Proprietário</th>
                                <th>CNH</th>
                                <th>Telefone</th>
                                <th>Cadastro</th>
                                <th>Alteração</th>
                                <th class="actions">Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% if (request.getAttribute("lcarro") != null) {
                                    for (Carro carro : (List<Carro>) request.getAttribute("lcarro")) {%>
                            <tr>
                                <td><%= carro.getId()%></td>
                                <td><%= carro.getMarca()%></td>
                                <td><%= carro.getModelo()%></td>
                                <td><%= carro.getAno()%></td>
                                <td><%= carro.getCor()%></td>
                                <td><%= carro.getMotor()%></td>
                                <td class="plate"><%= carro.getPlaca()%></td>
                                <td><%= carro.getDono() != null ? carro.getDono() : "N/A"%></td>
                                <td><%= carro.getCnh() != null ? carro.getCnh() : "N/A"%></td>
                                <td><%= carro.getTelefone() != null ? carro.getTelefone() : "N/A"%></td>
                                <td>
                                    <%= carro.getCadastradoEm() != null
                                            ? DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(carro.getCadastradoEm())
                                            : "N/A"%>
                                </td>
                                <td>
                                    <%= carro.getAlteradoEm() != null
                                            ? DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(carro.getAlteradoEm())
                                            : "N/A"%>
                                </td>
                                <td class="actions">
                                    <button onclick="toggleEdicao(<%= carro.getId()%>)" class="btn-action btn-edit" title="Editar">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                    <form action="CarroController" method="POST" class="inline-form">
                                        <input type="hidden" name="op" value="Deletar">
                                        <input type="hidden" name="txtid" value="<%= carro.getId()%>">
                                        <button type="submit" onclick="return confirmarExclusao()" class="btn-action btn-delete" title="Excluir">
                                            <i class="fas fa-trash-alt"></i>
                                        </button>
                                    </form>
                                </td>
                            </tr>
                            <tr id="form-edicao-<%= carro.getId()%>" class="edit-form-row" style="display: none;">
                                <td colspan="13">
                                    <div class="edit-form-container">
                                        <h3><i class="fas fa-edit"></i> Editar Veículo #<%= carro.getId()%></h3>
                                        <form onsubmit="return validarFormulario(this) && confirmarDados()">
                                            <input type="hidden" name="op" value="Atualizar">
                                            <input type="hidden" name="txtid" value="<%= carro.getId()%>">

                                            <div class="form-grid">
                                                <div class="form-group">
                                                    <label for="txtmarca-<%= carro.getId()%>">Marca:</label>
                                                    <input type="text" id="txtmarca-<%= carro.getId()%>" name="txtmarca" value="<%= carro.getMarca()%>" required>
                                                </div>

                                                <div class="form-group">
                                                    <label for="txtmodelo-<%= carro.getId()%>">Modelo:</label>
                                                    <input type="text" id="txtmodelo-<%= carro.getId()%>" name="txtmodelo" value="<%= carro.getModelo()%>" required>
                                                </div>

                                                <div class="form-group">
                                                    <label for="txtano-<%= carro.getId()%>">Ano:</label>
                                                    <input type="number" id="txtano-<%= carro.getId()%>" name="txtano" value="<%= carro.getAno()%>" required>
                                                </div>

                                                <div class="form-group">
                                                    <label for="txtcor-<%= carro.getId()%>">Cor:</label>
                                                    <input type="text" id="txtcor-<%= carro.getId()%>" name="txtcor" value="<%= carro.getCor()%>">
                                                </div>

                                                <div class="form-group">
                                                    <label for="txtmotor-<%= carro.getId()%>">Motor:</label>
                                                    <input type="text" id="txtmotor-<%= carro.getId()%>" name="txtmotor" value="<%= carro.getMotor()%>" required>
                                                </div>

                                                <div class="form-group">
                                                    <label for="txtplaca-<%= carro.getId()%>">Placa:</label>
                                                    <input type="text" id="txtplaca-<%= carro.getId()%>" name="txtplaca" value="<%= carro.getPlaca()%>" required>
                                                </div>

                                                <div class="form-group">
                                                    <label for="txtdono-<%= carro.getId()%>">Proprietário:</label>
                                                    <input type="text" id="txtdono-<%= carro.getId()%>" name="txtdono" value="<%= carro.getDono()%>">
                                                </div>

                                                <div class="form-group">
                                                    <label for="txtcnh-<%= carro.getId()%>">CNH:</label>
                                                    <input type="text" id="txtcnh-<%= carro.getId()%>" name="txtcnh" value="<%= carro.getCnh()%>">
                                                </div>

                                                <div class="form-group">
                                                    <label for="txttelefone-<%= carro.getId()%>">Telefone:</label>
                                                    <input type="text" id="txttelefone-<%= carro.getId()%>" name="txttelefone" value="<%= carro.getTelefone()%>">
                                                </div>
                                            </div>

                                            <div class="form-actions">
                                                <button type="submit" class="btn btn-save">
                                                    <i class="fas fa-save"></i> Salvar Alterações
                                                </button>
                                                <button type="button" onclick="toggleEdicao(<%= carro.getId()%>)" class="btn btn-cancel">
                                                    <i class="fas fa-times"></i> Cancelar
                                                </button>
                                            </div>
                                        </form>
                                    </div>
                                </td>
                            </tr>
                            <% }
                            } else { %>
                            <tr>
                                <td colspan="13" class="no-data">
                                    <i class="fas fa-info-circle"></i> Nenhum veículo cadastrado
                                </td>
                            </tr>
                            <% }%>
                        </tbody>
                    </table>
                </div>
            </section>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/xlsx@0.18.5/dist/xlsx.full.min.js"></script>
        <script src="assets/js/scripts.js?v=4.0"></script>
    </body>
</html>