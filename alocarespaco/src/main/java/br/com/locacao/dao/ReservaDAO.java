package br.com.locacao.dao;

import br.com.locacao.dal.ConnectionFactory;
import br.com.locacao.model.Reserva;

import java.sql.*;
import java.util.*;

public class ReservaDAO {

    public void cadastrar(Reserva r) {
        String sql = "INSERT INTO reserva (cliente_id, espaco_id, data_inicio, data_fim, valor_total, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, r.getClienteId());
            ps.setInt(2, r.getEspacoId());
            ps.setTimestamp(3, new Timestamp(r.getDataInicio().getTime()));
            ps.setTimestamp(4, new Timestamp(r.getDataFim().getTime()));
            ps.setDouble(5, r.getValorTotal());
            ps.setString(6, r.getStatus());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarStatus(int id, String status) {
        String sql = "UPDATE reserva SET status = ? WHERE id = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM reserva WHERE id = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Reserva buscarPorId(int id) {
        String sql = "SELECT r.*, c.nome AS cliente_nome, e.nome AS espaco_nome " +
                     "FROM reserva r " +
                     "JOIN cliente c ON r.cliente_id = c.id " +
                     "JOIN espaco  e ON r.espaco_id  = e.id " +
                     "WHERE r.id = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapear(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Reserva> listarTodos() {
        List<Reserva> lista = new ArrayList<>();
        String sql = "SELECT r.*, c.nome AS cliente_nome, e.nome AS espaco_nome " +
                     "FROM reserva r " +
                     "JOIN cliente c ON r.cliente_id = c.id " +
                     "JOIN espaco  e ON r.espaco_id  = e.id " +
                     "ORDER BY r.data_inicio DESC";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) lista.add(mapear(rs));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public int contar() {
        String sql = "SELECT COUNT(*) FROM reserva";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private Reserva mapear(ResultSet rs) throws SQLException {
        Reserva r = new Reserva();
        r.setId(rs.getInt("id"));
        r.setClienteId(rs.getInt("cliente_id"));
        r.setClienteNome(rs.getString("cliente_nome"));
        r.setEspacoId(rs.getInt("espaco_id"));
        r.setEspacoNome(rs.getString("espaco_nome"));
        r.setDataInicio(rs.getTimestamp("data_inicio"));
        r.setDataFim(rs.getTimestamp("data_fim"));
        r.setValorTotal(rs.getDouble("valor_total"));
        r.setStatus(rs.getString("status"));
        return r;
    }
}
