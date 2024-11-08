package com.upiiz.relaciones_equipo.repositories;

import com.upiiz.relaciones_equipo.models.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {
}
