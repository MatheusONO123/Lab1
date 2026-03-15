package br.com.locacao.model;
 
public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String tipoUsuario; // NOVO: "SINDICO" ou "USUARIO"
 
    // Construtor vazio
    public Usuario() {}
 
    // Construtor completo
    public Usuario(int id, String nome, String email, String senha, String tipoUsuario) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }
 
    // Getters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public String getTipoUsuario() { return tipoUsuario; }
 
    // Setters
    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setSenha(String senha) { this.senha = senha; }
    public void setTipoUsuario(String tipoUsuario) { this.tipoUsuario = tipoUsuario; }
    
    // Método auxiliar para verificar se é síndico
    public boolean isSindico() {
        return "SINDICO".equalsIgnoreCase(this.tipoUsuario);
    }
    
    // Método auxiliar para verificar se é usuário
    public boolean isUsuario() {
        return "USUARIO".equalsIgnoreCase(this.tipoUsuario);
    }
    
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", tipoUsuario='" + tipoUsuario + '\'' +
                '}';
    }
}