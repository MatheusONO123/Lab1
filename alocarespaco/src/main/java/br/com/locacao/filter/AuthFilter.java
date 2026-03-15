package br.com.locacao.filter;

import br.com.locacao.model.Usuario;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;

import javax.servlet.http.*;
import java.io.IOException;

/**
 * Filtro para proteger páginas que requerem autenticação
 * Aplica-se a todas as páginas exceto login, cadastro e recursos estáticos
 */
@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String path = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();

        // Páginas públicas (não requerem autenticação)
        boolean isPublicPage = path.endsWith("login.jsp") ||
                               path.endsWith("cadastro.jsp") ||
                               path.contains("/login") ||
                               path.contains("/cadastro") ||
                               path.contains("/css/") ||
                               path.contains("/js/") ||
                               path.contains("/images/") ||
                               path.contains("/assets/");

        // Verificar se usuário está logado
        Usuario usuario = (session != null) ? (Usuario) session.getAttribute("usuario") : null;

        if (isPublicPage) {
            chain.doFilter(request, response);
        } else if (usuario != null) {
            chain.doFilter(request, response);
        } else {
            session = httpRequest.getSession();
            session.setAttribute("erro", "Você precisa fazer login para acessar esta página!");
            httpResponse.sendRedirect(contextPath + "/login.jsp");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inicialização se necessário
    }

    @Override
    public void destroy() {
        // Limpeza se necessário
    }
}