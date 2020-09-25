package com.curso.ereservations.controllers;

import com.curso.ereservations.models.Cliente;
import com.curso.ereservations.models.Reserva;
import com.curso.ereservations.services.ClienteService;
import com.curso.ereservations.services.api.ReservaServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/reservas")
public class ReservaRestController {

    private final ReservaServiceAPI serviceAPI;
    private final ClienteService clienteService;

    @Autowired
    public ReservaRestController(ReservaServiceAPI serviceAPI, ClienteService clienteService) {
        this.serviceAPI = serviceAPI;
        this.clienteService = clienteService;
    }

    @GetMapping("/all")
    public List<Reserva> getAll() {
        return serviceAPI.getAll();
    }

    @GetMapping("/{id}")
    public Reserva find(@PathVariable String id) {
        return serviceAPI.get(id);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody Reserva reserva, BindingResult result) {
        if (result.hasErrors()) return this.validar(result);
        Reserva entity = serviceAPI.save(reserva);
        Cliente cliente = clienteService.findByIdentification(entity.getIdCliente());
        cliente.addReserva(entity);
        clienteService.update(cliente);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Reserva entity = serviceAPI.get(id);
        if (entity == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        serviceAPI.delete(id);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<?> findReservasByIdCliente(@PathVariable String id) {
        List<Reserva> reservas = this.serviceAPI.findReservasByIdCliente(id);
        return ResponseEntity.ok(reservas);
    }


    /* Se realizan las Validaciones de los constrains de javax.validator */
    public ResponseEntity<?> validar(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(fieldError ->
                errores.put(
                        fieldError.getField(), " El campo "
                                + fieldError.getField() + " "
                                + fieldError.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errores);
    }
}
