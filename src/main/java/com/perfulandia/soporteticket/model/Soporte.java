package com.perfulandia.soporteticket.model;

import jakarta.persistence.*;

@Entity
@Table(name = "soporte")
public class Soporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "tipo_soporte", nullable = false)
    private String tipoSoporte;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @Column(name = "estado", nullable = false)
    private String estado; // Ej: "Abierto", "En progreso", "Resuelto"

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    // Getters y Setters
}