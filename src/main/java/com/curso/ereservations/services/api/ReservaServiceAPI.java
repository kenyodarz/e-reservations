package com.curso.ereservations.services.api;

import com.curso.ereservations.models.Reserva;
import com.curso.ereservations.utils.templates.GenericServiceAPI;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservaServiceAPI extends GenericServiceAPI<Reserva, String> {
    List<Reserva> findByDate(@Param("fechaInicio") LocalDate fechaInicio, @Param("fechaSalida") LocalDate fechaSalida);

    List<Reserva> findReservasByIdCliente(String idCliente);
}
