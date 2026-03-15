package br.com.locacao.model;

public class Espaco {
    private int    id;
    private String nome;
    private String descricao;
    private int    capacidade;
    private double valorHora;
    private String status; // disponivel, ocupado, manutencao

    public Espaco() {}

    public int    getId()         { return id; }
    public String getNome()       { return nome; }
    public String getDescricao()  { return descricao; }
    public int    getCapacidade() { return capacidade; }
    public double getValorHora()  { return valorHora; }
    public String getStatus()     { return status; }

    public void setId(int id)               { this.id         = id; }
    public void setNome(String nome)        { this.nome       = nome; }
    public void setDescricao(String d)      { this.descricao  = d; }
    public void setCapacidade(int c)        { this.capacidade = c; }
    public void setValorHora(double v)      { this.valorHora  = v; }
    public void setStatus(String status)    { this.status     = status; }
}
