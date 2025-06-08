package com.perfulandia.soporteticket.service;

import org.springframework.stereotype.Service;
import com.perfulandia.soporteticket.repository.SoporteRepository;
import com.perfulandia.soporteticket.model.Soporte;
import com.perfulandia.soporteticket.dto.SoporteDTO;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

@Service
public class SoporteService {
    private final SoporteRepository soporteRepository;

    public SoporteService(SoporteRepository soporteRepository) {
        this.soporteRepository = soporteRepository;
    }

    // 🔍 Obtener soporte por ID
    public SoporteDTO obtenerSoportePorId(Long idTicket) {
        Soporte soporte = soporteRepository.findById(idTicket)
                .orElseThrow(() -> new RuntimeException("Soporte no encontrado"));
        return convertirAsoporteDTO(soporte);
    }

    // 📌 Obtener todos los soportes de un usuario
    public List<SoporteDTO> obtenerSoportesPorUsuario(Long idUsuario) {
        return soporteRepository.findByIdUsuario(idUsuario)
                .stream()
                .map(this::convertirAsoporteDTO)
                .collect(Collectors.toList());
    }

    // ➕ Crear un nuevo soporte
    public SoporteDTO crearSoporte(SoporteDTO soporteDTO) {
        Soporte soporte = new Soporte();
        soporte.setIdUsuario(soporteDTO.getIdUsuario());
        soporte.setTipoTicket(soporteDTO.getTipoTicket());
        soporte.setDescripcion(soporteDTO.getDescripcion());
        soporte.setEstado("Abierto");
        soporte.setFechaCreacion(LocalDateTime.now());
        soporteRepository.save(soporte);
        return convertirAsoporteDTO(soporte);
    }

    // ✏️ Actualizar el estado de un soporte
    public SoporteDTO actualizarEstado(Long idTicket, String nuevoEstado) {
        Soporte soporte = soporteRepository.findById(idTicket)
                .orElseThrow(() -> new RuntimeException("Soporte no encontrado"));
        soporte.setEstado(nuevoEstado);
        if (nuevoEstado.equalsIgnoreCase("Resuelto")) {
            soporte.setFechaResolucion(LocalDateTime.now());
        }
        soporteRepository.save(soporte);
        return convertirAsoporteDTO(soporte);
    }

    // ❌ Eliminar un soporte por ID
    public void eliminarSoporte(Long idTicket) {
        soporteRepository.deleteById(idTicket);
    }

    // 🔄 Método auxiliar para convertir Soporte a SoporteDTO
    private SoporteDTO convertirAsoporteDTO(Soporte soporte) {
        return new SoporteDTO(
                soporte.getIdTicket(),
                soporte.getIdUsuario(),
                soporte.getTipoTicket(),
                soporte.getDescripcion(),
                soporte.getEstado(),
                soporte.getFechaCreacion(),
                soporte.getFechaResolucion()
        );
    }
}
