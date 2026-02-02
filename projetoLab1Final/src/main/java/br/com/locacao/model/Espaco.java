package br.com.locacao.model;

import java.math.BigDecimal;

public class Espaco {
    private Long idEspaco;
    private String nomeEspaco;
    private Integer capacidadeMaxima;
    private String descricao;
    private BigDecimal valorBase;

    public Espaco() {
    }

    public Espaco(Long idEspaco, String nomeEspaco, Integer capacidadeMaxima, String descricao, BigDecimal valorBase) {
        this.idEspaco = idEspaco;
        this.nomeEspaco = nomeEspaco;
        this.capacidadeMaxima = capacidadeMaxima;
        this.descricao = descricao;
        this.valorBase = valorBase;
    }

    public Long getIdEspaco() {
        return idEspaco;
    }

    public void setIdEspaco(Long idEspaco) {
        this.idEspaco = idEspaco;
    }

    public String getNomeEspaco() {
        return nomeEspaco;
    }

    public void setNomeEspaco(String nomeEspaco) {
        this.nomeEspaco = nomeEspaco;
    }

    public Integer getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(Integer capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorBase() {
        return valorBase;
    }

    public void setValorBase(BigDecimal valorBase) {
        this.valorBase = valorBase;
    }
}
