package br.com.locacao.dao;
 
import br.com.locacao.model.Usuario;
import br.com.locacao.dal.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
 
public class UsuarioDAO {
 
    /**
     * Autentica usuário e retorna objeto Usuario completo
     * @param email Email do usuário
     * @param senha Senha do usuário
     * @return Objeto Usuario se autenticado, null caso contrário
     */
    public Usuario autenticar(String email, String senha) {
        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            stmt.setString(2, senha);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipoUsuario(rs.getString("tipoUsuario"));
                return usuario;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao autenticar usuário: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
 
    /**
     * Cadastra novo usuário no sistema
     * @param usuario Objeto Usuario com dados do novo usuário
     * @return true se cadastrado com sucesso, false caso contrário
     */
    public boolean cadastrar(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, email, senha, tipoUsuario) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTipoUsuario());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
 
    /**
     * Verifica se email já existe no banco
     * @param email Email a ser verificado
     * @return true se email existe, false caso contrário
     */
    public boolean emailExiste(String email) {
        String sql = "SELECT COUNT(*) as total FROM usuario WHERE email = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao verificar email: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
 
    /**
     * Lista todos os usuários cadastrados (apenas para síndico)
     * @return Lista de usuários
     */
    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario ORDER BY nome ASC";
        
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTipoUsuario(rs.getString("tipoUsuario"));
                // Não retornamos senha por segurança
                usuarios.add(usuario);
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
            e.printStackTrace();
        }
        
        return usuarios;
    }
 
    /**
     * Busca usuário por ID
     * @param id ID do usuário
     * @return Objeto Usuario se encontrado, null caso contrário
     */
    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipoUsuario(rs.getString("tipoUsuario"));
                return usuario;
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário por ID: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
 
    /**
     * Atualiza dados do usuário
     * @param usuario Objeto Usuario com dados atualizados
     * @return true se atualizado com sucesso, false caso contrário
     */
    public boolean atualizar(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, email = ?, tipoUsuario = ? WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getTipoUsuario());
            stmt.setInt(4, usuario.getId());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
 
    /**
     * Conta total de usuários cadastrados
     * @return Número total de usuários
     */
    public int contarUsuarios() {
        String sql = "SELECT COUNT(*) as total FROM usuario";
        
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return rs.getInt("total");
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao contar usuários: " + e.getMessage());
            e.printStackTrace();
        }
        
        return 0;
    }
}
 