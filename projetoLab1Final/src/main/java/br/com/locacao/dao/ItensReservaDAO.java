package br.com.locacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import br.com.locacao.dal.ConnectionFactory;
import br.com.locacao.model.ItensReserva;

public class ItensReservaDAO {
    private Connection conn;

    public ItensReservaDAO() {
        this.conn = new ConnectionFactory().getConnection();
    }

    public void salvar(ItensReserva item) {
        String sql = "INSERT INTO itens_reserva (idReserva, idServico, quantidade, tipoServico) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, item.getIdReserva());
            stmt.setLong(2, item.getIdServico());
            stmt.setInt(3, item.getQuantidade());
            stmt.setString(4, item.getTipoServico());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar item da reserva", e);
        }
    }
}