<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="br.com.locacao.model.Usuario" %>
<%
    Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
    if (usuarioLogado == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }

    boolean isSindico = "SINDICO".equals(usuarioLogado.getTipoUsuario());
    String paginaAtual = request.getRequestURI();
    String inicial = usuarioLogado.getNome() != null && !usuarioLogado.getNome().isEmpty()
            ? String.valueOf(usuarioLogado.getNome().charAt(0)).toUpperCase() : "?";
%>

<div class="sidebar">

    <div class="sidebar-logo">
        <h2>&#127970; Alocação</h2>
        <span>de Espaço</span>
        <div class="user-chip">
            <div class="avatar"><%= inicial %></div>
            <div class="info">
                <div class="nome"><%= usuarioLogado.getNome() %></div>
                <div class="tipo">
                    <% if (isSindico) { %>&#128081; Síndico<% } else { %>&#128100; Usuário<% } %>
                </div>
            </div>
        </div>
    </div>

    <nav>
        <a href="index.jsp" class="<%= paginaAtual.contains("index") ? "ativo" : "" %>">
            <span class="icone">&#128200;</span> Dashboard
        </a>

        <% if (isSindico) { %>
            <div class="sidebar-divider">Administração</div>
            <a href="<%= request.getContextPath() %>/usuarios"
               class="<%= paginaAtual.contains("usuario") ? "ativo" : "" %>">
                <span class="icone">&#128101;</span> Usuários
                <span class="sidebar-badge">ADM</span>
            </a>
            <a href="clientes.jsp" class="<%= paginaAtual.contains("cliente") ? "ativo" : "" %>">
                <span class="icone">&#128100;</span> Clientes
            </a>
            <a href="espacos.jsp" class="<%= paginaAtual.contains("espaco") ? "ativo" : "" %>">
                <span class="icone">&#127968;</span> Espaços
            </a>
            <a href="reservas.jsp" class="<%= paginaAtual.contains("reserva") ? "ativo" : "" %>">
                <span class="icone">&#128197;</span> Reservas
            </a>
            <a href="servicos.jsp" class="<%= paginaAtual.contains("servico") ? "ativo" : "" %>">
                <span class="icone">&#128296;</span> Serviços
            </a>
            <a href="listagem.jsp" class="<%= paginaAtual.contains("listagem") ? "ativo" : "" %>">
                <span class="icone">&#128203;</span> Listagem Geral
            </a>
        <% } else { %>
            <div class="sidebar-divider">Minha Área</div>
            <a href="reservas.jsp" class="<%= paginaAtual.contains("reserva") ? "ativo" : "" %>">
                <span class="icone">&#128197;</span> Agendar Espaço
            </a>
            <a href="espacos.jsp" class="<%= paginaAtual.contains("espaco") ? "ativo" : "" %>">
                <span class="icone">&#127968;</span> Ver Espaços
            </a>
        <% } %>
    </nav>

    <div class="sidebar-footer">
        <a href="<%= request.getContextPath() %>/logout">
            <span>&#128682;</span> Sair
        </a>
    </div>

</div>