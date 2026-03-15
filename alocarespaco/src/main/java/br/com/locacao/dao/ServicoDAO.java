package br.com.locacao.dao;

import br.com.locacao.dal.ConnectionFactory;
import br.com.locacao.model.Servico;

import java.sql.*;
import java.util.*;

public class ServicoDAO {

    public void cadastrar(Servico s) {
        String sql = "INSERT INTO servico (nome, descricao, valor) VALUES (?, ?, ?)";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, s.getNome());
            ps.setString(2, s.getDescricao());
            ps.setDouble(3, s.getValor());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Servico s) {
        String sql = "UPDATE servico SET nome=?, descricao=?, valor=? WHERE id=?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, s.getNome());
            ps.setString(2, s.getDescricao());
            ps.setDouble(3, s.getValor());
            ps.setInt(4, s.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM servico WHERE id = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Servico buscarPorId(int id) {
        String sql = "SELECT * FROM servico WHERE id = ?";
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

    public List<Servico> listarTodos() {
        List<Servico> lista = new ArrayList<>();
        String sql = "SELECT * FROM servico ORDER BY nome";
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
        String sql = "SELECT COUNT(*) FROM servico";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private Servico mapear(ResultSet rs) throws SQLException {
        Servico s = new Servico();
        s.setId(rs.getInt("id"));
        s.setNome(rs.getString("nome"));
        s.setDescricao(rs.getString("descricao"));
        s.setValor(rs.getDouble("valor"));
        return s;
    }
}
