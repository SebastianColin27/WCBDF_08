package com.upiiz.relaciones_equipo.repositories;

import com.upiiz.relaciones_equipo.models.Liga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LigaRepository extends JpaRepository<Liga, Long> {
}
