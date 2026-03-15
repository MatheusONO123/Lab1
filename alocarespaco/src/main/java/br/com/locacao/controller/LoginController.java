package br.com.locacao.controller;

import br.com.locacao.dao.UsuarioDAO;
import br.com.locacao.model.Usuario;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login", "/logout"})
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioDAO usuarioDAO;
 
    @Override
    public void init() {
        usuarioDAO = new UsuarioDAO();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String path = request.getServletPath();
        
        if ("/logout".equals(path)) {
            // Logout: invalidar sessão e redirecionar
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            // Login: exibir página de login
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Buscar parâmetros
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
 
        // Validações
        if (email == null || email.trim().isEmpty() ||
            senha == null || senha.trim().isEmpty()) {
            
            request.setAttribute("erro", "Email e senha são obrigatórios!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
 
        // Tentar autenticar
        Usuario usuario = usuarioDAO.autenticar(email.trim(), senha);
 
        if (usuario != null) {
            // Login bem-sucedido - criar sessão
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            session.setAttribute("usuarioId", usuario.getId());
            session.setAttribute("usuarioNome", usuario.getNome());
            session.setAttribute("usuarioEmail", usuario.getEmail());
            session.setAttribute("tipoUsuario", usuario.getTipoUsuario());
            
            // Redirecionar para dashboard
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            
        } else {
            // Login falhou
            request.setAttribute("erro", "Email ou senha incorretos!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
 