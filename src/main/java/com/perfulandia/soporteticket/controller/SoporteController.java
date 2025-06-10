package com.perfulandia.soporteticket.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perfulandia.soporteticket.dto.SoporteDTO;
import com.perfulandia.soporteticket.service.SoporteService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/soporte")
public class SoporteController {
    private final SoporteService soporteService;
    
    public SoporteController(SoporteService soporteService) {
    this.soporteService = soporteService;
}

     @Autowired
    private SoporteService service;

    //obtener todos los tickets de soporte
    @GetMapping
    public ResponseEntity<List<SoporteDTO>> listar() {
        return ResponseEntity.ok(service.listarTickets());
    }

    //obtener ticket por id
    @GetMapping("/{id_ticket}")
    public ResponseEntity<SoporteDTO> obtenerSoportePorId(@PathVariable Long id_ticket) {
    return ResponseEntity.ok(soporteService.obtenerSoportePorId(id_ticket));
    }

    //obtener los tickets de soporte por id de usuario
    @GetMapping("/usuario/{id_usuario}")
    public ResponseEntity<List<SoporteDTO>> obtenerSoportesPorUsuario(@PathVariable Long id_usuario) {
    return ResponseEntity.ok(soporteService.obtenerSoportesPorUsuario(id_usuario));
    }

    //crear nuevo ticket de soporte
    @PostMapping
    public ResponseEntity<SoporteDTO> crearSoporte(@RequestBody SoporteDTO soporteDTO) {
    return ResponseEntity.ok(soporteService.crearSoporte(soporteDTO));
    }

    //actualizar estado de ticket de soporte
    @PutMapping("/{id_ticket}/estado")
    public ResponseEntity<SoporteDTO> actualizarEstado(@PathVariable Long id_ticket, @RequestParam String estado) {
    return ResponseEntity.ok(soporteService.actualizarEstado(id_ticket, estado));
    }
}