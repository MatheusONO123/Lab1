<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="br.com.locacao.model.Usuario" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Alocação de Espaço</title>
    <link rel="stylesheet" href="css/estilo.css">
</head>
<body>

<%@ include file="menu.jsp" %>

<%
    String msgSessao  = (String) session.getAttribute("sucesso");
    String erroSessao = (String) session.getAttribute("erro");
    session.removeAttribute("sucesso");
    session.removeAttribute("erro");
%>

<div class="conteudo">

    <div class="topbar">
        <div>
            <h1>Dashboard</h1>
            <p>
                Bem-vindo(a), <strong><%= usuarioLogado.getNome() %></strong> &mdash;
                <% if (isSindico) { %>
                    <span class="topbar-badge sindico">&#128081; Síndico</span>
                <% } else { %>
                    <span class="topbar-badge usuario">&#128100; Usuário</span>
                <% } %>
            </p>
        </div>
    </div>

    <% if (msgSessao != null && !msgSessao.isEmpty()) { %>
        <div class="alerta alerta-sucesso">&#10003; <%= msgSessao %></div>
    <% } %>
    <% if (erroSessao != null && !erroSessao.isEmpty()) { %>
        <div class="alerta alerta-erro">&#10007; <%= erroSessao %></div>
    <% } %>

    <% if (isSindico) { %>

        <div class="card-boas-vindas">
            <h2>&#127968; Painel de Administração</h2>
            <p>Gerencie usuários, espaços, clientes e reservas do condomínio.</p>
            <div style="display:flex; gap:12px; flex-wrap:wrap;">
                <a href="<%= request.getContextPath() %>/usuarios" class="btn btn-ciano">
                    &#128101; Ver Usuários
                </a>
                <a href="reservas.jsp"
                   class="btn" style="background:rgba(255,255,255,0.2);color:#fff;">
                    &#128197; Ver Reservas
                </a>
            </div>
        </div>

        <div class="cards-grid">
            <div class="card-stat azul">
                <h3>Total de Usuários</h3>
                <div class="valor">--</div>
            </div>
            <div class="card-stat ciano">
                <h3>Espaços</h3>
                <div class="valor">--</div>
            </div>
            <div class="card-stat verde">
                <h3>Reservas</h3>
                <div class="valor">--</div>
            </div>
            <div class="card-stat laranja">
                <h3>Clientes</h3>
                <div class="valor">--</div>
            </div>
        </div>

        <div class="tabela-container" style="padding: 24px;">
            <h2 style="font-size:15px; color:#5b21b6; margin-bottom:16px;">&#9889; Ações Rápidas</h2>
            <div style="display:flex; gap:12px; flex-wrap:wrap;">
                <a href="clientes.jsp"  class="btn btn-primario">&#128100; Novo Cliente</a>
                <a href="espacos.jsp"   class="btn btn-ciano">&#127968; Novo Espaço</a>
                <a href="reservas.jsp"  class="btn btn-aviso">&#128197; Nova Reserva</a>
                <a href="servicos.jsp"  class="btn btn-sucesso">&#128296; Novo Serviço</a>
                <a href="listagem.jsp"  class="btn btn-outline">&#128203; Listagem Geral</a>
            </div>
        </div>

    <% } else { %>

        <div class="card-boas-vindas">
            <h2>&#127881; Bem-vindo(a), <%= usuarioLogado.getNome() %>!</h2>
            <p>Confira os espaços disponíveis e faça o seu agendamento.</p>
            <div style="display:flex; gap:12px; flex-wrap:wrap;">
                <a href="reservas.jsp" class="btn btn-ciano">&#128197; Fazer Reserva</a>
                <a href="espacos.jsp"
                   class="btn" style="background:rgba(255,255,255,0.2);color:#fff;">
                    &#127968; Ver Espaços
                </a>
            </div>
        </div>

        <div class="cards-grid">
            <div class="card-stat ciano">
                <h3>Espaços Disponíveis</h3>
                <div class="valor">--</div>
            </div>
            <div class="card-stat verde">
                <h3>Minhas Reservas</h3>
                <div class="valor">--</div>
            </div>
            <div class="card-stat roxo">
                <h3>Serviços</h3>
                <div class="valor">--</div>
            </div>
        </div>

        <div class="tabela-container" style="padding: 24px;">
            <h2 style="font-size:15px; color:#5b21b6; margin-bottom:16px;">&#128204; O que deseja fazer?</h2>
            <div style="display:flex; gap:12px; flex-wrap:wrap;">
                <a href="reservas.jsp" class="btn btn-primario">&#128197; Agendar Espaço</a>
                <a href="espacos.jsp"  class="btn btn-outline">&#127968; Ver Espaços</a>
            </div>
        </div>

    <% } %>

</div>
</body>
</html>