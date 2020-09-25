package com.curso.ereservations.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "reservas")
public class Reserva {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String idReserva;
    @Column
    @NotNull
    private LocalDate fechaIngresoReserva;
    @Column
    @NotNull
    private LocalDate fechaSalidaReserva;
    @Column
    @Min(value = 1, message = "La cantidad minima de personas tiene que se 1")
    private int cantidadPersonasReserva;
    @Column
    private String descripcionReserva;
    @Column
    private String idCliente;

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

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }
}
