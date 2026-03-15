package br.com.locacao.controller;

import br.com.locacao.dao.EspacoDAO;
import br.com.locacao.model.Espaco;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class EspacoController extends HttpServlet {

    private final EspacoDAO dao = new EspacoDAO();

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
            res.sendRedirect(req.getContextPath() + "/espacos.jsp");
        }
    }

    private void cadastrar(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        Espaco e = montarEspaco(req);
        dao.cadastrar(e);
        res.sendRedirect(req.getContextPath() + "/espacos.jsp?msg=cadastrado");
    }

    private void editar(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        Espaco e = montarEspaco(req);
        e.setId(Integer.parseInt(req.getParameter("id")));
        dao.atualizar(e);
        res.sendRedirect(req.getContextPath() + "/espacos.jsp?msg=editado");
    }

    private void excluir(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        dao.excluir(id);
        res.sendRedirect(req.getContextPath() + "/espacos.jsp?msg=excluido");
    }

    private Espaco montarEspaco(HttpServletRequest req) {
        Espaco e = new Espaco();
        e.setNome(req.getParameter("nome"));
        e.setDescricao(req.getParameter("descricao"));
        e.setCapacidade(Integer.parseInt(req.getParameter("capacidade")));
        e.setValorHora(Double.parseDouble(req.getParameter("valorHora").replace(",", ".")));
        e.setStatus(req.getParameter("status"));
        return e;
    }
}
