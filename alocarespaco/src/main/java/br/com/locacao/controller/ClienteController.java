package br.com.locacao.controller;

import br.com.locacao.dao.ClienteDAO;
import br.com.locacao.model.Cliente;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ClienteController extends HttpServlet {

    private final ClienteDAO dao = new ClienteDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String acao = req.getParameter("acao");

        if ("cadastrar".equals(acao)) {
            cadastrar(req, res);
        } else if ("editar".equals(acao)) {
            editar(req, res);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String acao = req.getParameter("acao");

        if ("excluir".equals(acao)) {
            excluir(req, res);
        } else {
            res.sendRedirect(req.getContextPath() + "/clientes.jsp");
        }
    }

    private void cadastrar(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        Cliente c = montarCliente(req);
        dao.cadastrar(c);
        res.sendRedirect(req.getContextPath() + "/clientes.jsp?msg=cadastrado");
    }

    private void editar(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        Cliente c = montarCliente(req);
        c.setId(Integer.parseInt(req.getParameter("id")));
        dao.atualizar(c);
        res.sendRedirect(req.getContextPath() + "/clientes.jsp?msg=editado");
    }

    private void excluir(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        dao.excluir(id);
        res.sendRedirect(req.getContextPath() + "/clientes.jsp?msg=excluido");
    }

    private Cliente montarCliente(HttpServletRequest req) {
        Cliente c = new Cliente();
        c.setNome(req.getParameter("nome"));
        c.setCpf(req.getParameter("cpf"));
        c.setEmail(req.getParameter("email"));
        c.setTelefone(req.getParameter("telefone"));
        c.setEndereco(req.getParameter("endereco"));
        return c;
    }
}
