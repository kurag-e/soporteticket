package com.perfulandia.soporteticket.dto;

import java.time.LocalDateTime;

public class SoporteDTO {

    private Long idTicket;
    private Long idUsuario;
    private String tipoTicket;
    private String descripcion;
    private String estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaResolucion;

    //constructor vacío (requerido por frameworks como Jackson)
    public SoporteDTO() {
    }

    //constructor completo
    public SoporteDTO(Long idTicket, Long idUsuario, String tipoTicket, String descripcion,
                      String estado, LocalDateTime fechaCreacion, LocalDateTime fechaResolucion) {
        this.idTicket = idTicket;
        this.idUsuario = idUsuario;
        this.tipoTicket = tipoTicket;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaResolucion = fechaResolucion;
    }

    //getters y setters
    public Long getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Long idTicket) {
        this.idTicket = idTicket;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoTicket() {
        return tipoTicket;
    }

    public void setTipoTicket(String tipoTicket) {
        this.tipoTicket = tipoTicket;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(LocalDateTime fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }
}
