package com.upiiz.relaciones_equipo.controllers;

import com.upiiz.relaciones_equipo.dtos.CustomResponse;
import com.upiiz.relaciones_equipo.models.Competencia;
import com.upiiz.relaciones_equipo.models.Liga;
import com.upiiz.relaciones_equipo.services.LigaService;
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
@RequestMapping("/api/v1/ligas")
@Tag(
        name = "Ligas"
)
public class LigaController {

    @Autowired
    LigaService ligaService;

    @GetMapping()
    public ResponseEntity<CustomResponse<List<Liga>>> getLiga(){
        List<Liga> ligas = new ArrayList<>();
        Link allLigasLink = linkTo(LigaController.class).withSelfRel();
        List<Link> links = List.of(allLigasLink);

        try {
            ligas = ligaService.obtenerTodas();
            if (!ligas.isEmpty()) {
                CustomResponse<List<Liga>> response = new CustomResponse<>(1, "Ligass encontradas", ligas, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse<>(0, "Liga no encontrada", ligas, links));
            }
        } catch (Exception e) {
            CustomResponse<List<Liga>> response = new CustomResponse<>(0, "Error interno de servidor", ligas, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Liga>> getLigaById(@PathVariable("id") Long id){
        Optional<Liga> liga=null;
        CustomResponse<Liga> response=null;
        Link allLigasLink =linkTo(LigaController.class).withSelfRel();
        List<Link> links = List.of(allLigasLink);
        try{
            liga= Optional.ofNullable(ligaService.obtenerLigaPorId(id));
            if(liga.isPresent()){
                response=new CustomResponse<>(1,"Liga encontrada", liga.get(), links);
                return  ResponseEntity.status(HttpStatus.OK).body(response);
            }else {
                response=new CustomResponse<>(0, "Liga no encontrada", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (Exception e){
            response=new CustomResponse<>(0, "Error interno de servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<CustomResponse<Liga>> createLiga(@RequestBody Liga liga){
        Link allLigaLink=linkTo(LigaController.class).withSelfRel();
        List<Link> links =List.of(allLigaLink);
        try {
            Liga liga1=ligaService.guardarLiga(liga);
            if (liga1!=null){
                CustomResponse<Liga> response =new CustomResponse<>(1,"Liga creada", liga1, links);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }else {
                CustomResponse<Liga> response=new CustomResponse<>(0,"Error interno del servidor", null, links);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }

        }catch (Exception e){
            CustomResponse<Liga> response=new CustomResponse<>(500, "Error interno de servidor: Verifique sus datos", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    public ResponseEntity<CustomResponse<Liga>> deleteLigaById(@PathVariable Long id){
        Optional<Liga> liga =null;
        CustomResponse<Liga> response=null;
        Link allLigaLink = linkTo(LigaController.class).withSelfRel();
        List<Link> links=List.of(allLigaLink);
        try{
            liga = Optional.ofNullable(ligaService.obtenerLigaPorId(id));
            if(liga.isPresent()){
                ligaService.eliminarLiga(id);
                response=new CustomResponse<>(1, "Liga eliminada", null, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }else {
                response=new CustomResponse<>(0, "Liga no encontrada", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (Exception e){
            response=new CustomResponse<>(500, "Error interno del servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Liga>> updateLiga(@RequestBody Liga liga, @PathVariable("id") Long id  ){
        Link allLigaLink =linkTo(LigaController.class).withSelfRel();
        List<Link> links =List.of(allLigaLink);
        try{
            liga.setId(id);
            Optional<Liga> liga2 = Optional.ofNullable(ligaService.obtenerLigaPorId(id));
            if(liga2.isPresent()){
                Liga liga1=ligaService.updateLiga(liga);
                CustomResponse<Liga> response=new CustomResponse<>(1, "Liga actualizada", liga1, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }else{
                CustomResponse<Liga> response=new CustomResponse<>(0, "Liga no encontrada", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (Exception e){
            CustomResponse<Liga> response=new CustomResponse<>(500, "Error interno en el servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }
}
