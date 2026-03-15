<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="br.com.locacao.model.Usuario" %>
<%
    // Verificação de acesso ANTES do include (usa session direto)
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
    Usuario _u = (Usuario) session.getAttribute("usuario");
    if (!"SINDICO".equals(_u.getTipoUsuario())) {
        session.setAttribute("erro", "Acesso negado! Apenas síndicos podem acessar esta página.");
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }

    // Lista de usuários enviada pelo UsuariosController
    @SuppressWarnings("unchecked")
    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
    if (usuarios == null) usuarios = new java.util.ArrayList<>();

    int qtdSindicos = 0;
    for (Usuario us : usuarios) {
        if ("SINDICO".equals(us.getTipoUsuario())) qtdSindicos++;
    }
    int qtdUsuariosComuns = usuarios.size() - qtdSindicos;
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Usuários - Alocação de Espaço</title>
    <link rel="stylesheet" href="css/estilo.css">
</head>
<body>

<%@ include file="menu.jsp" %>

<div class="conteudo">

    <div class="topbar">
        <div>
            <h1>&#128101; Gerenciamento de Usuários</h1>
            <p>Lista de todos os usuários cadastrados no sistema</p>
        </div>
        <a href="index.jsp" class="btn btn-outline">&#8592; Voltar ao Dashboard</a>
    </div>

    <div class="cards-grid" style="max-width:660px;">
        <div class="card-stat azul">
            <h3>Total de Usuários</h3>
            <div class="valor"><%= usuarios.size() %></div>
        </div>
        <div class="card-stat ciano">
            <h3>Síndicos</h3>
            <div class="valor"><%= qtdSindicos %></div>
        </div>
        <div class="card-stat roxo">
            <h3>Usuários Comuns</h3>
            <div class="valor"><%= qtdUsuariosComuns %></div>
        </div>
    </div>

    <div class="tabela-container">
        <div class="tabela-header">
            <h2>&#128101; Usuários Cadastrados (<%= usuarios.size() %>)</h2>
        </div>
        <table>
            <thead>
                <tr>
                    <th>#</th>
                    <th>Nome</th>
                    <th>E-mail</th>
                    <th>Tipo</th>
                </tr>
            </thead>
            <tbody>
                <% if (usuarios.isEmpty()) { %>
                    <tr>
                        <td colspan="4" style="text-align:center; color:#aaa; padding:40px;">
                            Nenhum usuário cadastrado ainda.
                        </td>
                    </tr>
                <% } else {
                    for (Usuario us : usuarios) {
                        boolean uSindico = "SINDICO".equals(us.getTipoUsuario());
                        String avatarBg = uSindico
                            ? "linear-gradient(135deg,#7c3aed,#5b21b6)"
                            : "linear-gradient(135deg,#06b6d4,#0891b2)";
                        String letraAvatar = (us.getNome() != null && !us.getNome().isEmpty())
                            ? String.valueOf(us.getNome().charAt(0)).toUpperCase() : "?";
                %>
                    <tr>
                        <td style="color:#64748b; font-weight:600;">#<%= us.getId() %></td>
                        <td>
                            <div style="display:flex; align-items:center; gap:10px;">
                                <div style="width:32px;height:32px;border-radius:50%;
                                            background:<%= avatarBg %>;display:flex;
                                            align-items:center;justify-content:center;
                                            color:#fff;font-size:13px;font-weight:700;flex-shrink:0;">
                                    <%= letraAvatar %>
                                </div>
                                <strong><%= us.getNome() %></strong>
                            </div>
                        </td>
                        <td><%= us.getEmail() %></td>
                        <td>
                            <% if (uSindico) { %>
                                <span class="badge badge-sindico">&#128081; Síndico</span>
                            <% } else { %>
                                <span class="badge badge-usuario">&#128100; Usuário</span>
                            <% } %>
                        </td>
                    </tr>
                <% } } %>
            </tbody>
        </table>
    </div>

</div>
</body>
</html>