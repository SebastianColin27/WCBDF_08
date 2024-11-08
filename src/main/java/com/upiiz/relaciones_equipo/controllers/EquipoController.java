package com.upiiz.relaciones_equipo.controllers;

import com.upiiz.relaciones_equipo.dtos.CustomResponse;
import com.upiiz.relaciones_equipo.models.Entrenador;
import com.upiiz.relaciones_equipo.models.Equipo;
import com.upiiz.relaciones_equipo.services.EntrenadorService;
import com.upiiz.relaciones_equipo.services.EquipoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/v1/equipos")
@Tag(
        name = "Equipos"
)
public class EquipoController {

    @Autowired
    EquipoService equipoService;

    @GetMapping()
    public ResponseEntity<CustomResponse<List<Equipo>>> getEquipos(){
        List<Equipo> equipo = new ArrayList<>();
        Link allEquipoLink = linkTo(EquipoController.class).withSelfRel();
        List<Link> links = List.of(allEquipoLink);

        try {
            equipo = equipoService.obtenerTodos();
            if (!equipo.isEmpty()) {
                CustomResponse<List<Equipo>> response = new CustomResponse<>(1, "Equipo encontrados", equipo, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse<>(0, "Equipo no encontrado", equipo, links));
            }
        } catch (Exception e) {
            CustomResponse<List<Equipo>> response = new CustomResponse<>(0, "Error interno de servidor", equipo, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Equipo>> getEquipoById(@PathVariable("id") Long id){
        Optional<Equipo> equipo=null;
        CustomResponse<Equipo> response=null;
        Link allEquipoLink =linkTo(EquipoController.class).withSelfRel();
        List<Link> links = List.of(allEquipoLink);
        try{
            equipo= Optional.ofNullable(equipoService.obtenerEquipoPorId(id));
            if(equipo.isPresent()){
                response=new CustomResponse<>(1,"Equipo encontrado", equipo.get(), links);
                return  ResponseEntity.status(HttpStatus.OK).body(response);
            }else {
                response=new CustomResponse<>(0, "Equipo no encontrado", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (Exception e){
            response=new CustomResponse<>(0, "Error interno de servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<CustomResponse<Equipo>> createEquipo(@RequestBody Equipo equipo){
        Link allEquipoLink=linkTo(EquipoController.class).withSelfRel();
        List<Link> links =List.of(allEquipoLink);
        try {
            Equipo equipo1=equipoService.guardarEquipo(equipo);
            if (equipo1!=null){
                CustomResponse<Equipo> response =new CustomResponse<>(1,"Equipo creado", equipo1, links);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }else {
                CustomResponse<Equipo> response=new CustomResponse<>(0,"Error interno del servidor", null, links);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }

        }catch (Exception e){
            CustomResponse<Equipo> response=new CustomResponse<>(500, "Error interno de servidor: Verifique sus datos", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    public ResponseEntity<CustomResponse<Equipo>> deleteEquipoById(@PathVariable Long id){
        Optional<Equipo> equipo=null;
        CustomResponse<Equipo> response=null;
        Link allEquipoLink = linkTo(EquipoController.class).withSelfRel();
        List<Link> links=List.of(allEquipoLink);
        try{
            equipo= Optional.ofNullable(equipoService.obtenerEquipoPorId(id));
            if(equipo.isPresent()){
                equipoService.eliminarEquipo(id);
                response=new CustomResponse<>(1, "Equipo eliminado", null, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }else {
                response=new CustomResponse<>(0, "Equipo no encontrado", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (Exception e){
            response=new CustomResponse<>(500, "Error interno del servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Equipo>> updateEquipo(@RequestBody Equipo equipo, @PathVariable("id") Long id  ){
        Link allEquipoLink=linkTo(EquipoController.class).withSelfRel();
        List<Link> links =List.of(allEquipoLink);
        try{
            equipo.setId(id);
            Optional<Equipo> equipo2 = Optional.ofNullable(equipoService.obtenerEquipoPorId(id));
            if(equipo2.isPresent()){
                Equipo equipo1=equipoService.updateEquipo(equipo);
                CustomResponse<Equipo> response=new CustomResponse<>(1, "Equipo actualizado", equipo1, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }else{
                CustomResponse<Equipo> response=new CustomResponse<>(0, "Equipo no encontrado", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (Exception e){
            CustomResponse<Equipo> response=new CustomResponse<>(500, "Error interno en el servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }
}
