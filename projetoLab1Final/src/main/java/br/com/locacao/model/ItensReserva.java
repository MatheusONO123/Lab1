package br.com.locacao.model;

public class ItensReserva {
    private Long idServicoReserva;
    private Long idReserva;
    private Long idServico;
    private Integer quantidade;
    private String tipoServico;

    public ItensReserva() {
    }

    public ItensReserva(Long idServicoReserva, Long idReserva, Long idServico, Integer quantidade, String tipoServico) {
        this.idServicoReserva = idServicoReserva;
        this.idReserva = idReserva;
        this.idServico = idServico;
        this.quantidade = quantidade;
        this.tipoServico = tipoServico;
    }

    public Long getIdServicoReserva() {
        return idServicoReserva;
    }

    public void setIdServicoReserva(Long idServicoReserva) {
        this.idServicoReserva = idServicoReserva;
    }

    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }

    public Long getIdServico() {
        return idServico;
    }

    public void setIdServico(Long idServico) {
        this.idServico = idServico;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }
}
