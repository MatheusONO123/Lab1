package br.com.locacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;

import br.com.locacao.dal.ConnectionFactory;
import br.com.locacao.model.Reserva;

public class ReservaDAO {

    private Connection conn;

    public ReservaDAO() {
        this.conn = new ConnectionFactory().getConnection();
    }

    public void salvar(Reserva reserva) {
        String sql = "INSERT INTO reserva (idCliente, idEspaco, dataReserva, horarioInicio, horarioFim, numeroConvidados, decoracao, precoTotal) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, reserva.getIdCliente());
            stmt.setLong(2, reserva.getIdEspaco());
            stmt.setDate(3, Date.valueOf(reserva.getDataReserva()));
            stmt.setTime(4, Time.valueOf(reserva.getHorarioInicio()));
            stmt.setTime(5, Time.valueOf(reserva.getHorarioFim()));
            stmt.setInt(6, reserva.getNumeroConvidados());
            stmt.setBoolean(7, reserva.getDecoracao());
            stmt.setBigDecimal(8, reserva.getPrecoTotal());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editar(Reserva reserva) {
        String sql = "UPDATE reserva SET idCliente = ?, idEspaco = ?, dataReserva = ?, horarioInicio = ?, horarioFim = ?, numeroConvidados = ?, decoracao = ?, precoTotal = ? WHERE idReserva = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, reserva.getIdCliente());
            stmt.setLong(2, reserva.getIdEspaco());
            stmt.setDate(3, Date.valueOf(reserva.getDataReserva()));
            stmt.setTime(4, Time.valueOf(reserva.getHorarioInicio()));
            stmt.setTime(5, Time.valueOf(reserva.getHorarioFim()));
            stmt.setInt(6, reserva.getNumeroConvidados());
            stmt.setBoolean(7, reserva.getDecoracao());
            stmt.setBigDecimal(8, reserva.getPrecoTotal());
            stmt.setLong(9, reserva.getIdReserva());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluir(Long idReserva) {
        String sql = "DELETE FROM reserva WHERE idReserva = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, idReserva);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
