package com.upiiz.relaciones_equipo.controllers;

import com.upiiz.relaciones_equipo.dtos.CustomResponse;
import com.upiiz.relaciones_equipo.models.Competencia;
import com.upiiz.relaciones_equipo.models.Entrenador;
import com.upiiz.relaciones_equipo.services.CompetenciaService;
import com.upiiz.relaciones_equipo.services.EntrenadorService;
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
@RequestMapping("/api/v1/entrenadores")
@Tag(
        name = "Entrenadores"
)
public class EntrenadorController {


    @Autowired
    EntrenadorService entrenadorService;

    @GetMapping()
    public ResponseEntity<CustomResponse<List<Entrenador>>> getEntrenadores(){
        List<Entrenador> entrenador = new ArrayList<>();
        Link allEntrenadoresLink = linkTo(EntrenadorController.class).withSelfRel();
        List<Link> links = List.of(allEntrenadoresLink);

        try {
            entrenador = entrenadorService.obtenerTodos();
            if (!entrenador.isEmpty()) {
                CustomResponse<List<Entrenador>> response = new CustomResponse<>(1, "Entrenador encontrados", entrenador, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse<>(0, "Entrenador no encontrado", entrenador, links));
            }
        } catch (Exception e) {
            CustomResponse<List<Entrenador>> response = new CustomResponse<>(0, "Error interno de servidor", entrenador, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Entrenador>> getEntrenadorById(@PathVariable("id") Long id){
        Optional<Entrenador> entrenador=null;
        CustomResponse<Entrenador> response=null;
        Link allEntrenadorLink =linkTo(EntrenadorController.class).withSelfRel();
        List<Link> links = List.of(allEntrenadorLink);
        try{
            entrenador= Optional.ofNullable(entrenadorService.obtenerEntrenadorPorId(id));
            if(entrenador.isPresent()){
                response=new CustomResponse<>(1,"Entrenador encontrado", entrenador.get(), links);
                return  ResponseEntity.status(HttpStatus.OK).body(response);
            }else {
                response=new CustomResponse<>(0, "Entrenador no encontrado", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (Exception e){
            response=new CustomResponse<>(0, "Error interno de servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<CustomResponse<Entrenador>> createEntrenador(@RequestBody Entrenador entrenador){
        Link allEntrenadorLink=linkTo(EntrenadorController.class).withSelfRel();
        List<Link> links =List.of(allEntrenadorLink);
        try {
            Entrenador entrenador1=entrenadorService.guardarEntrenador(entrenador);
            if (entrenador1!=null){
                CustomResponse<Entrenador> response =new CustomResponse<>(1,"Entrenador creado", entrenador1, links);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }else {
                CustomResponse<Entrenador> response=new CustomResponse<>(0,"Error interno del servidor", null, links);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }

        }catch (Exception e){
            CustomResponse<Entrenador> response=new CustomResponse<>(500, "Error interno de servidor: Verifique sus datos", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    public ResponseEntity<CustomResponse<Entrenador>> deleteEntrenadorById(@PathVariable Long id){
        Optional<Entrenador> entrenador=null;
        CustomResponse<Entrenador> response=null;
        Link allEntrenadorLink = linkTo(EntrenadorController.class).withSelfRel();
        List<Link> links=List.of(allEntrenadorLink);
        try{
            entrenador= Optional.ofNullable(entrenadorService.obtenerEntrenadorPorId(id));
            if(entrenador.isPresent()){
                entrenadorService.eliminarEntrenador(id);
                response=new CustomResponse<>(1, "Entrenador eliminado", null, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }else {
                response=new CustomResponse<>(0, "Entrenador no encontrado", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (Exception e){
            response=new CustomResponse<>(500, "Error interno del servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Entrenador>> updateEntrenador(@RequestBody Entrenador entrenador, @PathVariable("id") Long id  ){
        Link allEntrenadorLink=linkTo(EntrenadorController.class).withSelfRel();
        List<Link> links =List.of(allEntrenadorLink);
        try{
            entrenador.setId(id);
            Optional<Entrenador> entrenador2 = Optional.ofNullable(entrenadorService.obtenerEntrenadorPorId(id));
            if(entrenador2.isPresent()){
                Entrenador entrenador1=entrenadorService.updateEntrenador(entrenador);
                CustomResponse<Entrenador> response=new CustomResponse<>(1, "Entrenador actualizado", entrenador1, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }else{
                CustomResponse<Entrenador> response=new CustomResponse<>(0, "Entrenador no encontrado", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (Exception e){
            CustomResponse<Entrenador> response=new CustomResponse<>(500, "Error interno en el servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }
}
