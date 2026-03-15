package br.com.locacao.model;

public class Cliente {
    private int    id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String endereco;

    public Cliente() {}

    public int    getId()       { return id; }
    public String getNome()     { return nome; }
    public String getCpf()      { return cpf; }
    public String getEmail()    { return email; }
    public String getTelefone() { return telefone; }
    public String getEndereco() { return endereco; }

    public void setId(int id)             { this.id       = id; }
    public void setNome(String nome)      { this.nome     = nome; }
    public void setCpf(String cpf)        { this.cpf      = cpf; }
    public void setEmail(String email)    { this.email    = email; }
    public void setTelefone(String t)     { this.telefone = t; }
    public void setEndereco(String e)     { this.endereco = e; }
}
