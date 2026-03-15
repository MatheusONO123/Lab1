package br.com.locacao.model;

import java.util.Date;

public class Reserva {
    private int          id;
    private int          clienteId;
    private String       clienteNome;
    private int          espacoId;
    private String       espacoNome;
    private Date         dataInicio;
    private Date         dataFim;
    private double       valorTotal;
    private String       status; // pendente, confirmada, cancelada

    public Reserva() {}

    public int    getId()           { return id; }
    public int    getClienteId()    { return clienteId; }
    public String getClienteNome()  { return clienteNome; }
    public int    getEspacoId()     { return espacoId; }
    public String getEspacoNome()   { return espacoNome; }
    public Date   getDataInicio()   { return dataInicio; }
    public Date   getDataFim()      { return dataFim; }
    public double getValorTotal()   { return valorTotal; }
    public String getStatus()       { return status; }

    public void setId(int id)                   { this.id          = id; }
    public void setClienteId(int cid)           { this.clienteId   = cid; }
    public void setClienteNome(String cn)       { this.clienteNome = cn; }
    public void setEspacoId(int eid)            { this.espacoId    = eid; }
    public void setEspacoNome(String en)        { this.espacoNome  = en; }
    public void setDataInicio(Date d)           { this.dataInicio  = d; }
    public void setDataFim(Date d)              { this.dataFim     = d; }
    public void setValorTotal(double v)         { this.valorTotal  = v; }
    public void setStatus(String status)        { this.status      = status; }
}
