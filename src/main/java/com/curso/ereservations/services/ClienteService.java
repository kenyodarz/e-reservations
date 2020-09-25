package com.curso.ereservations.services;

import com.curso.ereservations.models.Cliente;
import com.curso.ereservations.repositories.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Cliente create(Cliente cliente) {
        return this.repository.save(cliente);
    }

    @Transactional
    public Cliente update(Cliente cliente) {
        return this.repository.save(cliente);
    }

    @Transactional
    public void delete(Cliente cliente) {
        this.repository.delete(cliente);
    }

    @Transactional
    public List<Cliente> findAll(){
        return this.repository.findAll();
    }


    public Cliente findByIdentification(String idCliente) {
        return this.repository.findByIdentification(idCliente);
    }
}
