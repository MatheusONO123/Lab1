package br.com.locacao.controller;

import br.com.locacao.dao.ServicoDAO;
import br.com.locacao.model.Servico;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ServicoController extends HttpServlet {

    private final ServicoDAO dao = new ServicoDAO();

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
            res.sendRedirect(req.getContextPath() + "/servicos.jsp");
        }
    }

    private void cadastrar(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        Servico s = montarServico(req);
        dao.cadastrar(s);
        res.sendRedirect(req.getContextPath() + "/servicos.jsp?msg=cadastrado");
    }

    private void editar(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        Servico s = montarServico(req);
        s.setId(Integer.parseInt(req.getParameter("id")));
        dao.atualizar(s);
        res.sendRedirect(req.getContextPath() + "/servicos.jsp?msg=editado");
    }

    private void excluir(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        dao.excluir(id);
        res.sendRedirect(req.getContextPath() + "/servicos.jsp?msg=excluido");
    }

    private Servico montarServico(HttpServletRequest req) {
        Servico s = new Servico();
        s.setNome(req.getParameter("nome"));
        s.setDescricao(req.getParameter("descricao"));
        s.setValor(Double.parseDouble(req.getParameter("valor").replace(",", ".")));
        return s;
    }
}
