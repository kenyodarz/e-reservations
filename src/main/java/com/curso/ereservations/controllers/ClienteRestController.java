package com.curso.ereservations.controllers;

import com.curso.ereservations.models.Cliente;
import com.curso.ereservations.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/clientes/")
public class ClienteRestController {

    private final ClienteService service;

    @Autowired
    public ClienteRestController(ClienteService service) {
        this.service = service;
    }

    @PostMapping("save")
    public ResponseEntity<?> createCliente(@Valid @RequestBody Cliente cliente, BindingResult result) {
        if (result.hasErrors()) {
            return this.validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(cliente));
    }

    @PutMapping("{idCliente}")
    public ResponseEntity<?> updateCliente(
            @Valid @RequestBody Cliente cliente,
            BindingResult result,
            @PathVariable String idCliente ){
        if (result.hasErrors()) {
            return this.validar(result);
        }
        Cliente optionalCliente = service.findByIdentification(idCliente);
        if(optionalCliente == null) return ResponseEntity.notFound().build();
        optionalCliente = cliente;
        return ResponseEntity.status(HttpStatus.OK).body(service.update(optionalCliente));

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
