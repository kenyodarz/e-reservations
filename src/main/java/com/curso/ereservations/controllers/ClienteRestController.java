package com.curso.ereservations.controllers;

import com.curso.ereservations.models.Cliente;
import com.curso.ereservations.services.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@RequestMapping("/api/clientes/")
@Api(tags = "cliente")
public class ClienteRestController {

    private final ClienteService service;

    @Autowired
    public ClienteRestController(ClienteService service) {
        this.service = service;
    }

    @PostMapping("save")
    @ApiOperation(value = "Crear Cliente", notes = "servicio para crear un nuevo cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente creado Correctamente"),
            @ApiResponse(code = 400, message = "Campos Inválidos")})
    public ResponseEntity<?> createCliente(@Valid @RequestBody Cliente cliente, BindingResult result) {
        if (result.hasErrors()) {
            return this.validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(cliente));
    }

    @PutMapping("update/{idCliente}")
    @ApiOperation(value = "Actualizar Cliente", notes = "servicio para actualizar un cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente actualizado Correctamente"),
            @ApiResponse(code = 400, message = "Campos Inválidos"),
            @ApiResponse(code = 404, message = "Cliente no Encontrado")})
    public ResponseEntity<?> updateCliente(
            @Valid @RequestBody Cliente cliente,
            BindingResult result,
            @PathVariable String idCliente) {
        if (result.hasErrors()) {
            return this.validar(result);
        }
        Cliente optionalCliente = service.findByIdentification(idCliente);
        if (optionalCliente == null) return ResponseEntity.notFound().build();
        optionalCliente = cliente;
        return ResponseEntity.status(HttpStatus.OK).body(service.update(optionalCliente));

    }

    @DeleteMapping("delete/{idCliente}")
    @ApiOperation(value = "Eliminar Cliente", notes = "servicio para eliminar un cliente")
    public void deleteCliente(@PathVariable String idCliente) {
        Cliente cliente = service.findByIdentification(idCliente);
        if (cliente != null) service.delete(cliente);
    }

    @GetMapping("all")
    @ApiOperation(value = "Listar Clientes", notes = "servicio para listar todos los clientes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Clientes Listados Correctamente")})
    public ResponseEntity<List<Cliente>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
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
