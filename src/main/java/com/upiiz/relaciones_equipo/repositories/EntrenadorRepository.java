package com.upiiz.relaciones_equipo.repositories;

import com.upiiz.relaciones_equipo.models.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador, Long> {
}
