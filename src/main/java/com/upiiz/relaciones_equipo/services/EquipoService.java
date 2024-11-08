package com.upiiz.relaciones_equipo.services;

import com.upiiz.relaciones_equipo.models.Equipo;
import com.upiiz.relaciones_equipo.repositories.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoService {
    @Autowired
    EquipoRepository equipoRepository;


    public List<Equipo> obtenerTodos (){
        return equipoRepository.findAll();
    }

    //GUARDAR UN Entrenador
    public Equipo guardarEquipo(Equipo equipo){
        return equipoRepository.save(equipo);
    }

    // ACTUALIZAR CLIENTE
    public Equipo updateEquipo(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    // OBTENER CLIENTE POR ID
    public Equipo obtenerEquipoPorId(Long id) {
        return equipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con id: " + id));
    }

    // ELIMINAR CLIENTE
    public void eliminarEquipo(Long id) {
        Equipo equipo = obtenerEquipoPorId(id);
        equipoRepository.delete(equipo);
    }
}
