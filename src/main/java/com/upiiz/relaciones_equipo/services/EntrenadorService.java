package com.upiiz.relaciones_equipo.services;

import com.upiiz.relaciones_equipo.models.Entrenador;
import com.upiiz.relaciones_equipo.repositories.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrenadorService {

    @Autowired
    EntrenadorRepository entrenadorRepository;


    public List<Entrenador> obtenerTodos (){
        return entrenadorRepository.findAll();
    }

    //GUARDAR UN Entrenador
    public Entrenador guardarEntrenador(Entrenador entrenador){
        return entrenadorRepository.save(entrenador);
    }

    // ACTUALIZAR CLIENTE
    public Entrenador updateEntrenador(Entrenador entrenador) {
        return entrenadorRepository.save(entrenador);
    }

    // OBTENER CLIENTE POR ID
    public Entrenador obtenerEntrenadorPorId(Long id) {
        return entrenadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado con id: " + id));
    }

    // ELIMINAR CLIENTE
    public void eliminarEntrenador(Long id) {
        Entrenador entrenador = obtenerEntrenadorPorId(id);
        entrenadorRepository.delete(entrenador);
    }
}
