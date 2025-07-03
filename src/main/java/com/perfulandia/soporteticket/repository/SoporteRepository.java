package com.perfulandia.soporteticket.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perfulandia.soporteticket.model.Soporte;

@Repository
public interface SoporteRepository extends JpaRepository<Soporte, Long> {

    Optional<Soporte> findById(Long idTicket);
    //buscar ticket x usuario
    List<Soporte> findByIdUsuario(Long idUsuario);

    //filtrar x estado ("Abierto", "En progreso", "Resuelto")
    List<Soporte> findByEstado(String estado);

    //buscar tickets creados en un rango de fechas
    List<Soporte> findByFechaCreacionBetween(LocalDateTime inicio, LocalDateTime fin);
}
