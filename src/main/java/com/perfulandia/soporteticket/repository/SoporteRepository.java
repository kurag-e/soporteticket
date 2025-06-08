package com.perfulandia.soporteticket.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perfulandia.soporteticket.model.Soporte;

@Repository
public interface SoporteRepository extends JpaRepository<Soporte, Long> {

    // 🔍 Buscar soportes por usuario
    List<Soporte> findByIdUsuario(Long idUsuario);

    // 📌 Filtrar por estado ("Abierto", "En progreso", "Resuelto")
    List<Soporte> findByEstado(String estado);

    // 📅 Buscar soportes creados en un rango de fechas
    List<Soporte> findByFechaCreacionBetween(LocalDateTime inicio, LocalDateTime fin);
}
