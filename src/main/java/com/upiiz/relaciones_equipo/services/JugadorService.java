package com.upiiz.relaciones_equipo.services;

import com.upiiz.relaciones_equipo.models.Jugador;
import com.upiiz.relaciones_equipo.repositories.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JugadorService {
    @Autowired
    JugadorRepository jugadorRepository;


    public List<Jugador> obtenerTodos (){
        return jugadorRepository.findAll();
    }

    //GUARDAR UN Entrenador
    public Jugador guardarJugador(Jugador jugador){
        return jugadorRepository.save(jugador);
    }

    // ACTUALIZAR CLIENTE
    public Jugador updateJugador(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    // OBTENER CLIENTE POR ID
    public Jugador obtenerJugadorPorId(Long id) {
        return jugadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado con id: " + id));
    }

    // ELIMINAR CLIENTE
    public void eliminarJugador(Long id) {
        Jugador jugador = obtenerJugadorPorId(id);
        jugadorRepository.delete(jugador);
    }
}
