package br.com.locacao.model;

public class ItensReserva {
    private int    id;
    private int    reservaId;
    private int    servicoId;
    private String servicoNome;
    private int    quantidade;
    private double valorUnitario;

    public ItensReserva() {}

    public int    getId()            { return id; }
    public int    getReservaId()     { return reservaId; }
    public int    getServicoId()     { return servicoId; }
    public String getServicoNome()   { return servicoNome; }
    public int    getQuantidade()    { return quantidade; }
    public double getValorUnitario() { return valorUnitario; }

    public void setId(int id)                  { this.id             = id; }
    public void setReservaId(int rid)          { this.reservaId      = rid; }
    public void setServicoId(int sid)          { this.servicoId      = sid; }
    public void setServicoNome(String sn)      { this.servicoNome    = sn; }
    public void setQuantidade(int q)           { this.quantidade     = q; }
    public void setValorUnitario(double v)     { this.valorUnitario  = v; }

    public double getSubtotal() {
        return quantidade * valorUnitario;
    }
}
