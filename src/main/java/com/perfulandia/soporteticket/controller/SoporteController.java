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

@RestController
@RequestMapping("/soporte")
public class SoporteController {
    private final SoporteService soporteService;
    
    public SoporteController(SoporteService soporteService) {
    this.soporteService = soporteService;
}

    // 🔍 Obtener un soporte por ID
    @GetMapping("/{id_ticket}")
    public ResponseEntity<SoporteDTO> obtenerSoportePorId(@PathVariable Long id_ticket) {
    return ResponseEntity.ok(soporteService.obtenerSoportePorId(id_ticket));
    }

    // 📌 Obtener todos los soportes de un usuario
    @GetMapping("/usuario/{id_usuario}")
    public ResponseEntity<List<SoporteDTO>> obtenerSoportesPorUsuario(@PathVariable Long id_usuario) {
    return ResponseEntity.ok(soporteService.obtenerSoportesPorUsuario(id_usuario));
    }

    // ➕ Crear un nuevo soporte
    @PostMapping
    public ResponseEntity<SoporteDTO> crearSoporte(@RequestBody SoporteDTO soporteDTO) {
    return ResponseEntity.ok(soporteService.crearSoporte(soporteDTO));
    }

    // ✏️ Actualizar el estado de un soporte
    @PutMapping("/{id_ticket}/estado")
    public ResponseEntity<SoporteDTO> actualizarEstado(@PathVariable Long id_ticket, @RequestParam String estado) {
    return ResponseEntity.ok(soporteService.actualizarEstado(id_ticket, estado));
    }
}