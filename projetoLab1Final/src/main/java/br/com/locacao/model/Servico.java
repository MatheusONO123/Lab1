package br.com.locacao.model;

import java.math.BigDecimal;

public class Servico {
    private Long idServico;
    private String tipoAlimento;
    private String descricao;
    private Integer limiteMaximo;
    private BigDecimal valorUnitario;

    public Servico() {
    }

    public Servico(Long idServico, String tipoAlimento, String descricao, Integer limiteMaximo, BigDecimal valorUnitario) {
        this.idServico = idServico;
        this.tipoAlimento = tipoAlimento;
        this.descricao = descricao;
        this.limiteMaximo = limiteMaximo;
        this.valorUnitario = valorUnitario;
    }

    public Long getIdServico() {
        return idServico;
    }

    public void setIdServico(Long idServico) {
        this.idServico = idServico;
    }

    public String getTipoAlimento() {
        return tipoAlimento;
    }

    public void setTipoAlimento(String tipoAlimento) {
        this.tipoAlimento = tipoAlimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getLimiteMaximo() {
        return limiteMaximo;
    }

    public void setLimiteMaximo(Integer limiteMaximo) {
        this.limiteMaximo = limiteMaximo;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
