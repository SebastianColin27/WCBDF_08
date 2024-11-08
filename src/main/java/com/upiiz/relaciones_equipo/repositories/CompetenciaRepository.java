package com.upiiz.relaciones_equipo.repositories;

import com.upiiz.relaciones_equipo.models.Competencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetenciaRepository extends JpaRepository<Competencia, Long> {
}
