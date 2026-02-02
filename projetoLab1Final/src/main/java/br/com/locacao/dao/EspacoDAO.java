package br.com.locacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.locacao.dal.ConnectionFactory;
import br.com.locacao.model.Espaco;

public class EspacoDAO {

    private Connection conn;

    public EspacoDAO() {
        this.conn = new ConnectionFactory().getConnection();
    }

    public void salvar(Espaco espaco) {
        String sql = "INSERT INTO espaco (nomeEspaco, capacidadeMaxima, descricao, valorBase) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, espaco.getNomeEspaco());
            stmt.setInt(2, espaco.getCapacidadeMaxima());
            stmt.setString(3, espaco.getDescricao());
            stmt.setBigDecimal(4, espaco.getValorBase());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Espaco> listarTodos() {
        String sql = "SELECT * FROM espaco";
        List<Espaco> lista = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Espaco espaco = new Espaco();
                espaco.setIdEspaco(rs.getLong("idEspaco"));
                espaco.setNomeEspaco(rs.getString("nomeEspaco"));
                espaco.setCapacidadeMaxima(rs.getInt("capacidadeMaxima"));
                espaco.setDescricao(rs.getString("descricao"));
                espaco.setValorBase(rs.getBigDecimal("valorBase"));
                lista.add(espaco);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public void editar(Espaco espaco) {
        String sql = "UPDATE espaco SET nomeEspaco = ?, capacidadeMaxima = ?, descricao = ?, valorBase = ? WHERE idEspaco = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, espaco.getNomeEspaco());
            stmt.setInt(2, espaco.getCapacidadeMaxima());
            stmt.setString(3, espaco.getDescricao());
            stmt.setBigDecimal(4, espaco.getValorBase());
            stmt.setLong(5, espaco.getIdEspaco());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluir(Long idEspaco) {
        String sql = "DELETE FROM espaco WHERE idEspaco = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, idEspaco);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
