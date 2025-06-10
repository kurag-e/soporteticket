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


    //obtener todos los tickets de soporte
    public List<SoporteDTO> listarTickets() {
    List<Soporte> tickets = soporteRepository.findAll();
    return tickets.stream()
            .map(this::convertirASoporteDTO)
            .collect(Collectors.toList());
}
    //obtener ticket por id
    public SoporteDTO obtenerSoportePorId(Long idTicket) {
        Soporte soporte = soporteRepository.findById(idTicket)
                .orElseThrow(() -> new RuntimeException("Soporte no encontrado"));
        return convertirAsoporteDTO(soporte);
    }

    //obtener los tickets de soporte por id de usuario
    public List<SoporteDTO> obtenerSoportesPorUsuario(Long idUsuario) {
        return soporteRepository.findByIdUsuario(idUsuario)
                .stream()
                .map(this::convertirAsoporteDTO)
                .collect(Collectors.toList());
    }

    //crear nuevo ticket de soporte
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

    //actualizar estado de ticket de soporte
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

    //método auxiliar para convertir Soporte a SoporteDTO
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
