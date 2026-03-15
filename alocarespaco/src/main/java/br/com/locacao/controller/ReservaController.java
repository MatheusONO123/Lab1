package br.com.locacao.controller;

import br.com.locacao.dao.ReservaDAO;
import br.com.locacao.model.Reserva;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class ReservaController extends HttpServlet {

    private final ReservaDAO dao = new ReservaDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String acao = req.getParameter("acao");

        if ("cadastrar".equals(acao)) {
            cadastrar(req, res);
        } else if ("status".equals(acao)) {
            atualizarStatus(req, res);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String acao = req.getParameter("acao");

        if ("excluir".equals(acao)) {
            excluir(req, res);
        } else {
            res.sendRedirect(req.getContextPath() + "/reservas.jsp");
        }
    }

    private void cadastrar(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Reserva r = new Reserva();
            r.setClienteId(Integer.parseInt(req.getParameter("clienteId")));
            r.setEspacoId(Integer.parseInt(req.getParameter("espacoId")));
            r.setDataInicio(sdf.parse(req.getParameter("dataInicio")));
            r.setDataFim(sdf.parse(req.getParameter("dataFim")));
            r.setValorTotal(Double.parseDouble(req.getParameter("valorTotal").replace(",", ".")));
            r.setStatus("pendente");
            dao.cadastrar(r);
            res.sendRedirect(req.getContextPath() + "/reservas.jsp?msg=cadastrado");
        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect(req.getContextPath() + "/reservas.jsp?msg=erro");
        }
    }

    private void atualizarStatus(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String status = req.getParameter("status");
        dao.atualizarStatus(id, status);
        res.sendRedirect(req.getContextPath() + "/reservas.jsp?msg=atualizado");
    }

    private void excluir(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        dao.excluir(id);
        res.sendRedirect(req.getContextPath() + "/reservas.jsp?msg=excluido");
    }
}
