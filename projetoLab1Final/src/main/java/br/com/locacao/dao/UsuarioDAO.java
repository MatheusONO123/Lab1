package br.com.locacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.locacao.dal.ConnectionFactory;
import br.com.locacao.model.Usuario;

public class UsuarioDAO {

    private Connection conn;

    public UsuarioDAO() {
        this.conn = new ConnectionFactory().getConnection();
    }

    public Usuario autenticar(String email, String senha) {
        String sql = "SELECT * FROM Usuario WHERE email = ? AND senha = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getLong("id_usuario"));
                u.setEmail(rs.getString("email"));
                u.setTipoUsuario(rs.getString("tipo_usuario"));
                return u;
            }

            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
