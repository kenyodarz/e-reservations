package com.curso.ereservations.services;

import com.curso.ereservations.models.Reserva;
import com.curso.ereservations.repositories.ReservaRepository;
import com.curso.ereservations.services.api.ReservaServiceAPI;
import com.curso.ereservations.utils.templates.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReservaService extends GenericServiceImpl<Reserva, String> implements ReservaServiceAPI {

    private final ReservaRepository repository;

    @Autowired
    public ReservaService(ReservaRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Reserva, String> getRepository() {
        return repository;
    }

    @Override
    @NotNull
    @Transactional
    public List<Reserva> findByDate(LocalDate fechaInicio, LocalDate fechaSalida) {
        return repository.findByDate(fechaInicio, fechaSalida);
    }

    @Override
    @NotNull
    @Transactional
    public List<Reserva> findReservasByIdCliente(String idCliente) {
        return repository.findReservasByIdCliente(idCliente);
    }
}
