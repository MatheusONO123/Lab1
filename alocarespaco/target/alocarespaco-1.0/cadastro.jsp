<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String erro = (String) request.getAttribute("erro");
%>
 
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro - Sistema de Alocação</title>
 
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
 
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #8B5CF6 0%, #06B6D4 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }
 
        .cadastro-container {
            background: #ffffff;
            border-radius: 20px;
            box-shadow: 0 20px 60px rgba(139, 92, 246, 0.3);
            width: 100%;
            max-width: 480px;
            padding: 50px 40px;
            position: relative;
            overflow: hidden;
        }
 
        /* Decoração gradiente no topo */
        .cadastro-container::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            height: 5px;
            background: linear-gradient(90deg, #8B5CF6 0%, #06B6D4 100%);
        }
 
        .logo {
            text-align: center;
            margin-bottom: 40px;
        }
 
        .logo h1 {
            background: linear-gradient(135deg, #8B5CF6 0%, #06B6D4 100%);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            background-clip: text;
            font-size: 36px;
            margin-bottom: 8px;
            font-weight: 800;
        }
 
        .logo p {
            color: #6b7280;
            font-size: 15px;
            font-weight: 500;
        }
 
        .divider {
            height: 2px;
            background: linear-gradient(90deg, transparent 0%, #e5e7eb 20%, #e5e7eb 80%, transparent 100%);
            margin: 30px 0;
        }
 
        .error-message {
            background-color: #fef2f2;
            color: #991b1b;
            padding: 14px 18px;
            border-radius: 12px;
            margin-bottom: 24px;
            border-left: 4px solid #ef4444;
            font-size: 14px;
            display: flex;
            align-items: center;
            gap: 10px;
            font-weight: 500;
            animation: slideDown 0.3s ease;
        }
 
        @keyframes slideDown {
            from {
                opacity: 0;
                transform: translateY(-10px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }
 
        .form-group {
            margin-bottom: 22px;
        }
 
        .form-group label {
            display: block;
            color: #374151;
            font-weight: 600;
            margin-bottom: 10px;
            font-size: 14px;
        }
 
        .form-group input[type="text"],
        .form-group input[type="email"],
        .form-group input[type="password"] {
            width: 100%;
            padding: 14px 16px;
            border: 2px solid #e5e7eb;
            border-radius: 12px;
            font-size: 15px;
            transition: all 0.3s ease;
            font-family: inherit;
            background: #fafafa;
        }
 
        .form-group input:focus {
            outline: none;
            border-color: #8B5CF6;
            background: #ffffff;
            box-shadow: 0 0 0 4px rgba(139, 92, 246, 0.1);
        }
 
        .checkbox-group {
            display: flex;
            align-items: center;
            gap: 12px;
            margin-bottom: 20px;
            padding: 16px;
            background: #f9fafb;
            border-radius: 12px;
            border: 2px solid #e5e7eb;
            transition: all 0.3s ease;
        }
 
        .checkbox-group:hover {
            background: #f3f4f6;
        }
 
        .checkbox-group input[type="checkbox"] {
            width: 22px;
            height: 22px;
            cursor: pointer;
            accent-color: #8B5CF6;
        }
 
        .checkbox-group label {
            color: #374151;
            font-size: 15px;
            cursor: pointer;
            margin: 0;
            font-weight: 600;
        }
 
        .codigo-sindico {
            display: none;
            margin-top: 20px;
            padding: 20px;
            background: linear-gradient(135deg, rgba(139, 92, 246, 0.05) 0%, rgba(6, 182, 212, 0.05) 100%);
            border-radius: 12px;
            border: 2px solid rgba(139, 92, 246, 0.3);
            animation: fadeIn 0.4s ease;
        }
 
        .codigo-sindico.show {
            display: block;
        }
 
        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: scale(0.95);
            }
            to {
                opacity: 1;
                transform: scale(1);
            }
        }
 
        .codigo-sindico p {
            color: #6D28D9;
            font-size: 14px;
            margin-bottom: 12px;
            font-weight: 600;
        }
 
        .codigo-sindico .subtitle {
            color: #6b7280;
            font-size: 13px;
            margin-bottom: 14px;
            font-weight: 500;
        }
 
        .codigo-sindico input {
            width: 100%;
            padding: 12px 16px;
            border: 2px solid #8B5CF6;
            border-radius: 10px;
            font-size: 15px;
            font-weight: 600;
            background: #ffffff;
            color: #6D28D9;
        }
 
        .codigo-sindico input:focus {
            outline: none;
            box-shadow: 0 0 0 4px rgba(139, 92, 246, 0.2);
        }
 
        .buttons {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 14px;
            margin-top: 30px;
        }
 
        button, .btn {
            padding: 14px 20px;
            border: none;
            border-radius: 12px;
            font-size: 15px;
            font-weight: 700;
            cursor: pointer;
            transition: all 0.3s ease;
            font-family: inherit;
            text-decoration: none;
            text-align: center;
            display: inline-flex;
            align-items: center;
            justify-content: center;
        }
 
        .btn-cadastrar {
            background: linear-gradient(135deg, #06B6D4 0%, #0891B2 100%);
            color: white;
            grid-column: 1;
            box-shadow: 0 4px 12px rgba(6, 182, 212, 0.4);
        }
 
        .btn-cadastrar:hover {
            background: linear-gradient(135deg, #0891B2 0%, #0e7490 100%);
            box-shadow: 0 6px 20px rgba(6, 182, 212, 0.5);
            transform: translateY(-2px);
        }
 
        .btn-cadastrar:active {
            transform: translateY(0);
        }
 
        .btn-voltar {
            background: linear-gradient(135deg, #8B5CF6 0%, #7C3AED 100%);
            color: white;
            grid-column: 2;
            box-shadow: 0 4px 12px rgba(139, 92, 246, 0.4);
        }
 
        .btn-voltar:hover {
            background: linear-gradient(135deg, #7C3AED 0%, #6D28D9 100%);
            box-shadow: 0 6px 20px rgba(139, 92, 246, 0.5);
            transform: translateY(-2px);
        }
 
        .btn-voltar:active {
            transform: translateY(0);
        }
 
        @media (max-width: 480px) {
            .cadastro-container {
                padding: 40px 30px;
            }
 
            .buttons {
                grid-template-columns: 1fr;
            }
 
            .btn-cadastrar, .btn-voltar {
                grid-column: auto;
            }
 
            .logo h1 {
                font-size: 30px;
            }
        }
    </style>
 
    <script>
        function toggleCodigoSindico() {
            const checkbox = document.getElementById('isSindico');
            const codigoDiv = document.getElementById('codigoSindicoDiv');
            const codigoInput = document.getElementById('codigoSindico');
            
            if (checkbox.checked) {
                codigoDiv.classList.add('show');
                codigoInput.required = true;
                codigoInput.focus();
            } else {
                codigoDiv.classList.remove('show');
                codigoInput.required = false;
                codigoInput.value = '';
            }
        }
    </script>
</head>
<body>
    <div class="cadastro-container">
        <div class="logo">
            <h1>📝 Cadastro</h1>
            <p>Crie sua conta no sistema</p>
        </div>
 
        <div class="divider"></div>
 
        <% if (erro != null && !erro.isEmpty()) { %>
            <div class="error-message">
                <span>✗</span>
                <span><%= erro %></span>
            </div>
        <% } %>
 
        <form method="POST" action="${pageContext.request.contextPath}/cadastro">
            <div class="form-group">
                <label for="email">📧 Email</label>
                <input
                    type="email"
                    id="email"
                    name="email"
                    placeholder="seu@email.com"
                    required
                    autofocus
                >
            </div>
 
            <div class="form-group">
                <label for="senha">🔒 Senha</label>
                <input
                    type="password"
                    id="senha"
                    name="senha"
                    placeholder="Mínimo 6 caracteres"
                    required
                    minlength="6"
                >
            </div>
 
            <div class="form-group">
                <label for="confirmarSenha">🔒 Confirmar Senha</label>
                <input
                    type="password"
                    id="confirmarSenha"
                    name="confirmarSenha"
                    placeholder="Digite a senha novamente"
                    required
                    minlength="6"
                >
            </div>
 
            <div class="checkbox-group">
                <input 
                    type="checkbox" 
                    id="isSindico" 
                    name="isSindico"
                    onchange="toggleCodigoSindico()"
                >
                <label for="isSindico">👑 Sou síndico</label>
            </div>
 
            <div id="codigoSindicoDiv" class="codigo-sindico">
                <p>⚠️ Código de Síndico</p>
                <p class="subtitle">Digite o código fornecido pela administração:</p>
                <input
                    type="text"
                    id="codigoSindico"
                    name="codigoSindico"
                    placeholder="Digite o código"
                >
            </div>
 
            <div class="buttons">
                <button type="submit" class="btn-cadastrar">Cadastrar</button>
                <a href="${pageContext.request.contextPath}/login" class="btn btn-voltar">
                    Voltar
                </a>
            </div>
        </form>
    </div>
</body>
</html>