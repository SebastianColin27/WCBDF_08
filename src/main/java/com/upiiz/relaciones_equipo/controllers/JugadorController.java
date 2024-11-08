package com.upiiz.relaciones_equipo.controllers;


import com.upiiz.relaciones_equipo.dtos.CustomResponse;
import com.upiiz.relaciones_equipo.models.Equipo;
import com.upiiz.relaciones_equipo.models.Jugador;
import com.upiiz.relaciones_equipo.services.EquipoService;
import com.upiiz.relaciones_equipo.services.JugadorService;
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
@RequestMapping("/api/v1/jugadores")
@Tag(
        name = "Jugadores"
)
public class JugadorController {
    @Autowired
    JugadorService jugadorService;

    @GetMapping()
    public ResponseEntity<CustomResponse<List<Jugador>>> getJugador(){
        List<Jugador> jugador = new ArrayList<>();
        Link allJugadorLink = linkTo(JugadorController.class).withSelfRel();
        List<Link> links = List.of(allJugadorLink);

        try {
            jugador = jugadorService.obtenerTodos();
            if (!jugador.isEmpty()) {
                CustomResponse<List<Jugador>> response = new CustomResponse<>(1, "Jugador encontrados", jugador, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse<>(0, "Jugador no encontrado", jugador, links));
            }
        } catch (Exception e) {
            CustomResponse<List<Jugador>> response = new CustomResponse<>(0, "Error interno de servidor", jugador, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Jugador>> getJugadorById(@PathVariable("id") Long id){
        Optional<Jugador> jugador=null;
        CustomResponse<Jugador> response=null;
        Link allJugadorLink =linkTo(JugadorController.class).withSelfRel();
        List<Link> links = List.of(allJugadorLink);
        try{
            jugador= Optional.ofNullable(jugadorService.obtenerJugadorPorId(id));
            if(jugador.isPresent()){
                response=new CustomResponse<>(1,"Jugador encontrado", jugador.get(), links);
                return  ResponseEntity.status(HttpStatus.OK).body(response);
            }else {
                response=new CustomResponse<>(0, "Jugadorno encontrado", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (Exception e){
            response=new CustomResponse<>(0, "Error interno de servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<CustomResponse<Jugador>> createJugador(@RequestBody Jugador jugador){
        Link allJugadorLink=linkTo(JugadorController.class).withSelfRel();
        List<Link> links =List.of(allJugadorLink);
        try {
            Jugador jugador1=jugadorService.guardarJugador(jugador);
            if (jugador1!=null){
                CustomResponse<Jugador> response =new CustomResponse<>(1,"Jugador creado", jugador1, links);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }else {
                CustomResponse<Jugador> response=new CustomResponse<>(0,"Error interno del servidor", null, links);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }

        }catch (Exception e){
            CustomResponse<Jugador> response=new CustomResponse<>(500, "Error interno de servidor: Verifique sus datos", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    public ResponseEntity<CustomResponse<Jugador>> deleteJugadoroById(@PathVariable Long id){
        Optional<Jugador> jugador=null;
        CustomResponse<Jugador> response=null;
        Link allJugadorLink = linkTo(JugadorController.class).withSelfRel();
        List<Link> links=List.of(allJugadorLink);
        try{
            jugador= Optional.ofNullable(jugadorService.obtenerJugadorPorId(id));
            if(jugador.isPresent()){
                jugadorService.eliminarJugador(id);
                response=new CustomResponse<>(1, "Jugador eliminado", null, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }else {
                response=new CustomResponse<>(0, "Jugador no encontrado", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (Exception e){
            response=new CustomResponse<>(500, "Error interno del servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Jugador>> updateJugador(@RequestBody Jugador jugador, @PathVariable("id") Long id  ){
        Link allJugadorLink=linkTo(JugadorController.class).withSelfRel();
        List<Link> links =List.of(allJugadorLink);
        try{
            jugador.setId(id);
            Optional<Jugador> jugador2 = Optional.ofNullable(jugadorService.obtenerJugadorPorId(id));
            if(jugador2.isPresent()){
                Jugador jugador1=jugadorService.updateJugador(jugador);
                CustomResponse<Jugador> response=new CustomResponse<>(1, "Jugador actualizado", jugador1, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }else{
                CustomResponse<Jugador> response=new CustomResponse<>(0, "Jugador no encontrado", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (Exception e){
            CustomResponse<Jugador> response=new CustomResponse<>(500, "Error interno en el servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }
}
