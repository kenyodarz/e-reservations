package com.curso.ereservations.repositories;

import com.curso.ereservations.models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, String> {


    @Query("select r from Reserva r where r.fechaIngresoReserva =:fechaInicio and r.fechaSalidaReserva=:fechaSalida")
    List<Reserva> findByDate(@Param("fechaInicio") LocalDate fechaInicio,@Param("fechaSalida") LocalDate fechaSalida);

    @Query("select r from Reserva r where r.cliente.idCliente = ?1")
    List<Reserva> findClientesByIdCliente(String idCliente);
}
