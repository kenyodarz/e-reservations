package com.curso.ereservations.repositories;

import com.curso.ereservations.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {

    List<Cliente> findByApellidoCliente(String apellido);

    Cliente findByIdentification(String idCliente);



}
