package com.perfulandia.soporteticket.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoporteDTO {
    private Long idTicket;
    private Long idUsuario;
    private String tipoTicket;
    private String descripcion;
    private String estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaResolucion;
}
