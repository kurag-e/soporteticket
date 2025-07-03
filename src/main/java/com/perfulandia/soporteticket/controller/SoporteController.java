package com.perfulandia.soporteticket.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import com.perfulandia.soporteticket.dto.SoporteDTO;
import com.perfulandia.soporteticket.service.SoporteService;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
@RequestMapping("/api/soporte")
public class SoporteController {

    @Autowired
    private SoporteService service;

    //obtener todos los tickets
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<SoporteDTO>>> listar() {
        List<SoporteDTO> tickets = service.listarTickets();

        List<EntityModel<SoporteDTO>> modelos = tickets.stream()
                .map(ticket -> EntityModel.of(ticket,
                        linkTo(methodOn(SoporteController.class).obtenerSoportePorId(ticket.getIdTicket())).withSelfRel()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                CollectionModel.of(modelos,
                        linkTo(methodOn(SoporteController.class).listar()).withSelfRel())
        );
    }

    //obtener ticket x id
    @GetMapping("/{id_ticket}")
    public ResponseEntity<EntityModel<SoporteDTO>> obtenerSoportePorId(@PathVariable Long id_ticket) {
        SoporteDTO soporte = service.obtenerSoportePorId(id_ticket);
        EntityModel<SoporteDTO> modelo = EntityModel.of(soporte,
                linkTo(methodOn(SoporteController.class).obtenerSoportePorId(id_ticket)).withSelfRel(),
                linkTo(methodOn(SoporteController.class).listar()).withRel("todos"));
        return ResponseEntity.ok(modelo);
    }

    //tickets x usuario
    @GetMapping("/usuario/{id_usuario}")
    public ResponseEntity<CollectionModel<EntityModel<SoporteDTO>>> obtenerSoportesPorUsuario(@PathVariable Long id_usuario) {
        List<SoporteDTO> tickets = service.obtenerSoportesPorUsuario(id_usuario);

        List<EntityModel<SoporteDTO>> modelos = tickets.stream()
                .map(ticket -> EntityModel.of(ticket,
                        linkTo(methodOn(SoporteController.class).obtenerSoportePorId(ticket.getIdTicket())).withSelfRel()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                CollectionModel.of(modelos,
                        linkTo(methodOn(SoporteController.class).obtenerSoportesPorUsuario(id_usuario)).withSelfRel())
        );
    }

    //crear ticket
    @PostMapping
    public ResponseEntity<EntityModel<SoporteDTO>> crearSoporte(@RequestBody SoporteDTO soporteDTO) {
        SoporteDTO creado = service.crearSoporte(soporteDTO);
        EntityModel<SoporteDTO> modelo = EntityModel.of(creado,
                linkTo(methodOn(SoporteController.class).obtenerSoportePorId(creado.getIdTicket())).withSelfRel(),
                linkTo(methodOn(SoporteController.class).listar()).withRel("todos"));
        return ResponseEntity.status(201).body(modelo);
    }

    //actualizar estado de ticket
    @PutMapping("/{id_ticket}/estado")
    public ResponseEntity<EntityModel<SoporteDTO>> actualizarEstado(@PathVariable Long id_ticket, @RequestParam String estado) {
        SoporteDTO actualizado = service.actualizarEstado(id_ticket, estado);
        EntityModel<SoporteDTO> modelo = EntityModel.of(actualizado,
                linkTo(methodOn(SoporteController.class).obtenerSoportePorId(id_ticket)).withSelfRel(),
                linkTo(methodOn(SoporteController.class).listar()).withRel("todos"));
        return ResponseEntity.ok(modelo);
    }
}
