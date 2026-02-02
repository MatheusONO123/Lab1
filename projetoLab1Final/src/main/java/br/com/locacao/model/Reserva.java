package br.com.locacao.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Reserva {
    private Reserva Reserva;
    private Cliente Cliente;
    private Espaco Espaco;
    private LocalDate dataReserva;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;
    private Integer numeroConvidados;
    private Boolean decoracao;
    private BigDecimal precoTotal;

    public Reserva() {
    }

    public Reserva(Reserva Reserva, Cliente Cliente, Espaco Espaco, LocalDate dataReserva, LocalTime horarioInicio, LocalTime horarioFim, Integer numeroConvidados, Boolean decoracao, BigDecimal precoTotal) {
        this.Reserva = Reserva;
        this.Cliente = Cliente;
        this.Espaco = Espaco;
        this.dataReserva = dataReserva;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.numeroConvidados = numeroConvidados;
        this.decoracao = decoracao;
        this.precoTotal = precoTotal;
    }

    public Reserva getIdReserva() {
        return Reserva;
    }

    public void setIdReserva(Reserva Reserva) {
        this.Reserva = Reserva;
    }

    public Cliente getcliente() {
        return Cliente;
    }

    public void setcliente(Cliente cliente) {
        this.Cliente = cliente;
    }

    public Espaco getIdEspaco() {
        return Espaco;
    }

    public void setIdEspaco(Espaco Espaco) {
        this.Espaco = Espaco;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalTime getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(LocalTime horarioFim) {
        this.horarioFim = horarioFim;
    }

    public Integer getNumeroConvidados() {
        return numeroConvidados;
    }

    public void setNumeroConvidados(Integer numeroConvidados) {
        this.numeroConvidados = numeroConvidados;
    }

    public Boolean getDecoracao() {
        return decoracao;
    }

    public void setDecoracao(Boolean decoracao) {
        this.decoracao = decoracao;
    }

    public BigDecimal getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(BigDecimal precoTotal) {
        this.precoTotal = precoTotal;
    }
}
