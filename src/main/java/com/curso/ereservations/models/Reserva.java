package com.curso.ereservations.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="reservas")
public class Reserva {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String idReserva;
    @Column
    //@Temporal(TemporalType.DATE)
    private LocalDate fechaIngresoReserva;
    @Column
    //@Temporal(TemporalType.DATE)
    private LocalDate fechaSalidaReserva;
    @Column
    private int cantidadPersonasReserva;
    @Column
    private String descripcionReserva;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    public Reserva() {

    }

    public String getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }

    public LocalDate getFechaIngresoReserva() {
        return fechaIngresoReserva;
    }

    public void setFechaIngresoReserva(LocalDate fechaIngresoReserva) {
        this.fechaIngresoReserva = fechaIngresoReserva;
    }

    public LocalDate getFechaSalidaReserva() {
        return fechaSalidaReserva;
    }

    public void setFechaSalidaReserva(LocalDate fechaSalidaReserva) {
        this.fechaSalidaReserva = fechaSalidaReserva;
    }

    public int getCantidadPersonasReserva() {
        return cantidadPersonasReserva;
    }

    public void setCantidadPersonasReserva(int cantidadPersonasReserva) {
        this.cantidadPersonasReserva = cantidadPersonasReserva;
    }

    public String getDescripcionReserva() {
        return descripcionReserva;
    }

    public void setDescripcionReserva(String descripcionReserva) {
        this.descripcionReserva = descripcionReserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
