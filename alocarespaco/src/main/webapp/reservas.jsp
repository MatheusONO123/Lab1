<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, br.com.locacao.model.*, br.com.locacao.dao.*" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Reservas - Alocação de Espaço</title>
    <link rel="stylesheet" href="css/estilo.css">
</head>
<body>

<%@ include file="menu.jsp" %>

<%
    ReservaDAO reservaDAO = new ReservaDAO();
    ClienteDAO clienteDAO = new ClienteDAO();
    EspacoDAO  espacoDAO  = new EspacoDAO();
    List<Reserva> reservas = reservaDAO.listarTodos();
    List<Cliente> clientes = clienteDAO.listarTodos();
    List<Espaco>  espacos  = espacoDAO.listarDisponiveis();
    String msg = request.getParameter("msg");
%>

<div class="conteudo">

    <div class="topbar">
        <div>
            <h1>&#128197; Reservas</h1>
            <p>
                <% if (isSindico) { %>Gerenciamento completo de reservas
                <% } else { %>Faça o agendamento de um espaço disponível<% } %>
            </p>
        </div>
    </div>

    <% if ("cadastrado".equals(msg)) { %>
        <div class="alerta alerta-sucesso">&#10003; Reserva cadastrada com sucesso!</div>
    <% } else if ("atualizado".equals(msg)) { %>
        <div class="alerta alerta-sucesso">&#10003; Status atualizado com sucesso!</div>
    <% } else if ("excluido".equals(msg)) { %>
        <div class="alerta alerta-sucesso">&#10003; Reserva excluída com sucesso!</div>
    <% } else if ("erro".equals(msg)) { %>
        <div class="alerta alerta-erro">&#10007; Erro ao salvar. Verifique os dados.</div>
    <% } %>

    <div class="form-container" style="margin-bottom:28px; max-width:100%;">
        <h2 style="font-size:15px; color:#5b21b6; margin-bottom:20px;">&#128197; Nova Reserva</h2>
        <form action="reserva" method="post">
            <input type="hidden" name="acao" value="cadastrar">
            <div class="form-linha">
                <div class="form-grupo">
                    <label>Cliente *</label>
                    <select name="clienteId" required>
                        <option value="">Selecione um cliente</option>
                        <% for (Cliente c : clientes) { %>
                            <option value="<%= c.getId() %>"><%= c.getNome() %></option>
                        <% } %>
                    </select>
                </div>
                <div class="form-grupo">
                    <label>Espaço *</label>
                    <select name="espacoId" required>
                        <option value="">Selecione um espaço</option>
                        <% for (Espaco e : espacos) { %>
                            <option value="<%= e.getId() %>">
                                <%= e.getNome() %> — R$ <%= String.format("%.2f", e.getValorHora()) %>/h
                            </option>
                        <% } %>
                    </select>
                </div>
            </div>
            <div class="form-linha">
                <div class="form-grupo">
                    <label>Data/Hora Início *</label>
                    <input type="datetime-local" name="dataInicio" required>
                </div>
                <div class="form-grupo">
                    <label>Data/Hora Fim *</label>
                    <input type="datetime-local" name="dataFim" required>
                </div>
            </div>
            <div class="form-grupo">
                <label>Valor Total (R$) *</label>
                <input type="text" name="valorTotal" placeholder="0,00" required>
            </div>
            <button type="submit" class="btn btn-primario" style="margin-top:6px;">
                &#128190; Salvar Reserva
            </button>
        </form>
    </div>

    <div class="tabela-container">
        <div class="tabela-header">
            <h2>Reservas (<%= reservas.size() %>)</h2>
        </div>
        <table>
            <thead>
                <tr>
                    <th>#</th><th>Cliente</th><th>Espaço</th>
                    <th>Início</th><th>Fim</th><th>Valor</th><th>Status</th>
                    <% if (isSindico) { %><th>Ações</th><% } %>
                </tr>
            </thead>
            <tbody>
                <% if (reservas.isEmpty()) { %>
                    <tr><td colspan="<%= isSindico ? 8 : 7 %>"
                            style="text-align:center;color:#aaa;padding:30px;">
                        Nenhuma reserva cadastrada.
                    </td></tr>
                <% } else { for (Reserva r : reservas) {
                    String bc = "confirmada".equals(r.getStatus()) ? "badge-ativo"
                              : "cancelada".equals(r.getStatus())  ? "badge-inativo"
                                                                   : "badge-pendente";
                %>
                    <tr>
                        <td style="color:#64748b;font-weight:600;">#<%= r.getId() %></td>
                        <td><strong><%= r.getClienteNome() %></strong></td>
                        <td><%= r.getEspacoNome() %></td>
                        <td><%= r.getDataInicio() %></td>
                        <td><%= r.getDataFim() %></td>
                        <td>R$ <%= String.format("%.2f", r.getValorTotal()) %></td>
                        <td><span class="badge <%= bc %>"><%= r.getStatus() %></span></td>
                        <% if (isSindico) { %>
                        <td style="display:flex;gap:5px;flex-wrap:wrap;">
                            <form action="reserva" method="post" style="display:inline;">
                                <input type="hidden" name="acao"   value="status">
                                <input type="hidden" name="id"     value="<%= r.getId() %>">
                                <input type="hidden" name="status" value="confirmada">
                                <button type="submit" class="btn btn-sucesso btn-sm">&#10003;</button>
                            </form>
                            <form action="reserva" method="post" style="display:inline;">
                                <input type="hidden" name="acao"   value="status">
                                <input type="hidden" name="id"     value="<%= r.getId() %>">
                                <input type="hidden" name="status" value="cancelada">
                                <button type="submit" class="btn btn-perigo btn-sm">&#10007;</button>
                            </form>
                            <a href="reserva?acao=excluir&id=<%= r.getId() %>"
                               class="btn btn-outline btn-sm"
                               onclick="return confirm('Excluir esta reserva?')">&#128465;</a>
                        </td>
                        <% } %>
                    </tr>
                <% } } %>
            </tbody>
        </table>
    </div>

</div>
</body>
</html>