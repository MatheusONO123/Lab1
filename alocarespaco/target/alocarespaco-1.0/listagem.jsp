<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, br.com.locacao.model.*, br.com.locacao.dao.*" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Listagem - Alocação de Espaço</title>
    <link rel="stylesheet" href="css/estilo.css">
</head>
<body>

<%@ include file="menu.jsp" %>

<%
    List<Cliente> clientes = new ClienteDAO().listarTodos();
    List<Espaco>  espacos  = new EspacoDAO().listarTodos();
    List<Reserva> reservas = new ReservaDAO().listarTodos();
    List<Servico> servicos = new ServicoDAO().listarTodos();
%>

<div class="conteudo">

    <div class="topbar">
        <div>
            <h1>Listagem Geral</h1>
            <p>Visão completa de todos os registros</p>
        </div>
    </div>

    <!-- clientes -->
    <div class="tabela-container" style="margin-bottom:24px;">
        <div class="tabela-header">
            <h2>&#128100; Clientes (<%= clientes.size() %>)</h2>
        </div>
        <table>
            <thead>
                <tr><th>#</th><th>Nome</th><th>CPF</th><th>E-mail</th><th>Telefone</th></tr>
            </thead>
            <tbody>
                <% if (clientes.isEmpty()) { %>
                    <tr><td colspan="5" style="text-align:center;color:#aaa;padding:20px;">Sem registros.</td></tr>
                <% } else { for (Cliente c : clientes) { %>
                    <tr>
                        <td><%= c.getId() %></td>
                        <td><%= c.getNome() %></td>
                        <td><%= c.getCpf() %></td>
                        <td><%= c.getEmail() != null ? c.getEmail() : "-" %></td>
                        <td><%= c.getTelefone() != null ? c.getTelefone() : "-" %></td>
                    </tr>
                <% }} %>
            </tbody>
        </table>
    </div>

    <!-- espaços -->
    <div class="tabela-container" style="margin-bottom:24px;">
        <div class="tabela-header">
            <h2>&#127968; Espaços (<%= espacos.size() %>)</h2>
        </div>
        <table>
            <thead>
                <tr><th>#</th><th>Nome</th><th>Capacidade</th><th>Valor/Hora</th><th>Status</th></tr>
            </thead>
            <tbody>
                <% if (espacos.isEmpty()) { %>
                    <tr><td colspan="5" style="text-align:center;color:#aaa;padding:20px;">Sem registros.</td></tr>
                <% } else { for (Espaco e : espacos) {
                    String bc = "disponivel".equals(e.getStatus()) ? "badge-ativo"
                              : "ocupado".equals(e.getStatus()) ? "badge-inativo" : "badge-pendente"; %>
                    <tr>
                        <td><%= e.getId() %></td>
                        <td><%= e.getNome() %></td>
                        <td><%= e.getCapacidade() %> pessoas</td>
                        <td>R$ <%= String.format("%.2f", e.getValorHora()) %></td>
                        <td><span class="badge <%= bc %>"><%= e.getStatus() %></span></td>
                    </tr>
                <% }} %>
            </tbody>
        </table>
    </div>

    <!-- reservas -->
    <div class="tabela-container" style="margin-bottom:24px;">
        <div class="tabela-header">
            <h2>&#128197; Reservas (<%= reservas.size() %>)</h2>
        </div>
        <table>
            <thead>
                <tr><th>#</th><th>Cliente</th><th>Espaço</th><th>Início</th><th>Fim</th><th>Valor</th><th>Status</th></tr>
            </thead>
            <tbody>
                <% if (reservas.isEmpty()) { %>
                    <tr><td colspan="7" style="text-align:center;color:#aaa;padding:20px;">Sem registros.</td></tr>
                <% } else { for (Reserva r : reservas) {
                    String bc = "confirmada".equals(r.getStatus()) ? "badge-ativo"
                              : "cancelada".equals(r.getStatus()) ? "badge-inativo" : "badge-pendente"; %>
                    <tr>
                        <td><%= r.getId() %></td>
                        <td><%= r.getClienteNome() %></td>
                        <td><%= r.getEspacoNome() %></td>
                        <td><%= r.getDataInicio() %></td>
                        <td><%= r.getDataFim() %></td>
                        <td>R$ <%= String.format("%.2f", r.getValorTotal()) %></td>
                        <td><span class="badge <%= bc %>"><%= r.getStatus() %></span></td>
                    </tr>
                <% }} %>
            </tbody>
        </table>
    </div>

    <!-- serviços -->
    <div class="tabela-container">
        <div class="tabela-header">
            <h2>&#128296; Serviços (<%= servicos.size() %>)</h2>
        </div>
        <table>
            <thead>
                <tr><th>#</th><th>Nome</th><th>Descrição</th><th>Valor</th></tr>
            </thead>
            <tbody>
                <% if (servicos.isEmpty()) { %>
                    <tr><td colspan="4" style="text-align:center;color:#aaa;padding:20px;">Sem registros.</td></tr>
                <% } else { for (Servico s : servicos) { %>
                    <tr>
                        <td><%= s.getId() %></td>
                        <td><%= s.getNome() %></td>
                        <td><%= s.getDescricao() != null ? s.getDescricao() : "-" %></td>
                        <td>R$ <%= String.format("%.2f", s.getValor()) %></td>
                    </tr>
                <% }} %>
            </tbody>
        </table>
    </div>

</div>

</body>
</html>
