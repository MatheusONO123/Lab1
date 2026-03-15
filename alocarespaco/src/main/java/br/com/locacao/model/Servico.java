package br.com.locacao.model;

public class Servico {
    private int    id;
    private String nome;
    private String descricao;
    private double valor;

    public Servico() {}

    public int    getId()        { return id; }
    public String getNome()      { return nome; }
    public String getDescricao() { return descricao; }
    public double getValor()     { return valor; }

    public void setId(int id)           { this.id        = id; }
    public void setNome(String nome)    { this.nome      = nome; }
    public void setDescricao(String d)  { this.descricao = d; }
    public void setValor(double valor)  { this.valor     = valor; }
}
