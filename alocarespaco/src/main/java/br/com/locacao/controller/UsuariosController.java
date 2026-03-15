package br.com.locacao.controller;
 
import br.com.locacao.dao.UsuarioDAO;
import br.com.locacao.model.Usuario;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
 
@WebServlet("/usuarios")
public class UsuariosController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioDAO usuarioDAO;
 
    @Override
    public void init() {
        usuarioDAO = new UsuarioDAO();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
        
        // Verificar se está logado
        if (usuarioLogado == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        
        // Verificar se é síndico
        if (!"SINDICO".equals(usuarioLogado.getTipoUsuario())) {
            request.setAttribute("erro", "Acesso negado! Apenas síndicos podem ver esta página.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        
        // Buscar todos os usuários
        List<Usuario> usuarios = usuarioDAO.listarTodos();
        request.setAttribute("usuarios", usuarios);
        
        // Encaminhar para a página
        request.getRequestDispatcher("/usuarios.jsp").forward(request, response);
    }
}