package com.upiiz.relaciones_equipo.services;

import com.upiiz.relaciones_equipo.models.Liga;
import com.upiiz.relaciones_equipo.repositories.LigaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LigaService {
    @Autowired
    LigaRepository ligaRepository;


    public List<Liga> obtenerTodas (){
        return ligaRepository.findAll();
    }

    //GUARDAR UN Entrenador
    public Liga guardarLiga(Liga liga){
        return ligaRepository.save(liga);
    }

    // ACTUALIZAR CLIENTE
    public Liga updateLiga(Liga liga) {
        return ligaRepository.save(liga);
    }

    // OBTENER CLIENTE POR ID
    public Liga obtenerLigaPorId(Long id) {
        return ligaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Liga no encontrado con id: " + id));
    }

    // ELIMINAR CLIENTE
    public void eliminarLiga(Long id) {
        Liga liga = obtenerLigaPorId(id);
        ligaRepository.delete(liga);
    }
}
