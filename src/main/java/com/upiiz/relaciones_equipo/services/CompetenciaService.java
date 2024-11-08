package com.upiiz.relaciones_equipo.services;

import com.upiiz.relaciones_equipo.models.Competencia;
import com.upiiz.relaciones_equipo.repositories.CompetenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetenciaService {

    @Autowired
    CompetenciaRepository competenciaRepository;

    //lISTAR YODOD
    public List<Competencia> obtenerTodas (){
        return competenciaRepository.findAll();
    }

    //GUARDAR UN CLIENTE
    public Competencia guardarCompetencia(Competencia competencia){
        return competenciaRepository.save(competencia);
    }

    // ACTUALIZAR CLIENTE
    public Competencia updateCompetencia(Competencia competencia) {
        return competenciaRepository.save(competencia);
    }

    // OBTENER CLIENTE POR ID
    public Competencia obtenerCompetenciaPorId(Long id) {
        return competenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Competencia no encontrado con id: " + id));
    }

    // ELIMINAR CLIENTE
    public void eliminarCompetencia(Long id) {
        Competencia competencia = obtenerCompetenciaPorId(id);
        competenciaRepository.delete(competencia);
    }
}
