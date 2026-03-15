package br.com.locacao.controller;
 
import br.com.locacao.dao.UsuarioDAO;
import br.com.locacao.model.Usuario;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
 
@WebServlet("/cadastro")
public class CadastroController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioDAO usuarioDAO;
    
    // Código secreto para síndico
    private static final String CODIGO_SINDICO = "ordem";
 
    @Override
    public void init() {
        usuarioDAO = new UsuarioDAO();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Exibir página de cadastro
        request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Buscar parâmetros
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String confirmarSenha = request.getParameter("confirmarSenha");
        String isSindico = request.getParameter("isSindico"); // checkbox
        String codigoSindico = request.getParameter("codigoSindico");
 
        // Validações básicas
        if (nome == null || nome.trim().isEmpty()) {
            request.setAttribute("erro", "Nome é obrigatório!");
            request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
            return;
        }
 
        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("erro", "Email é obrigatório!");
            request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
            return;
        }
 
        if (senha == null || senha.trim().isEmpty()) {
            request.setAttribute("erro", "Senha é obrigatória!");
            request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
            return;
        }
 
        if (senha.length() < 6) {
            request.setAttribute("erro", "Senha deve ter no mínimo 6 caracteres!");
            request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
            return;
        }
 
        if (!senha.equals(confirmarSenha)) {
            request.setAttribute("erro", "As senhas não coincidem!");
            request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
            return;
        }
 
        // Verificar se email já existe
        if (usuarioDAO.emailExiste(email.trim())) {
            request.setAttribute("erro", "Este email já está cadastrado!");
            request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
            return;
        }
 
        // Determinar tipo de usuário
        String tipoUsuario = "USUARIO"; // Padrão
        
        if ("on".equals(isSindico)) {
            // Usuário marcou "Sou síndico"
            
            if (codigoSindico == null || codigoSindico.trim().isEmpty()) {
                request.setAttribute("erro", "Código de síndico é obrigatório!");
                request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
                return;
            }
            
            // Validar código de síndico
            if (!CODIGO_SINDICO.equals(codigoSindico.trim())) {
                request.setAttribute("erro", "Código de síndico incorreto!");
                request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
                return;
            }
            
            tipoUsuario = "SINDICO";
        }
 
        // Criar usuário
        Usuario usuario = new Usuario();
        usuario.setNome(nome.trim());
        usuario.setEmail(email.trim());
        usuario.setSenha(senha); // Em produção, fazer hash da senha!
        usuario.setTipoUsuario(tipoUsuario);
 
        // Tentar cadastrar
        boolean sucesso = usuarioDAO.cadastrar(usuario);
 
        if (sucesso) {
            // Cadastro realizado com sucesso
            HttpSession session = request.getSession();
            session.setAttribute("sucesso", "Cadastro realizado com sucesso! Faça login.");
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            // Erro ao cadastrar
            request.setAttribute("erro", "Erro ao cadastrar usuário. Tente novamente.");
            request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
        }
    }
}