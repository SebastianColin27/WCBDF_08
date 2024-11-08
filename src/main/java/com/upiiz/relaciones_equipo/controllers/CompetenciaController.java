package com.upiiz.relaciones_equipo.controllers;

import com.upiiz.relaciones_equipo.dtos.CustomResponse;
import com.upiiz.relaciones_equipo.models.Competencia;
import com.upiiz.relaciones_equipo.services.CompetenciaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/v1/competencias")
@Tag(
        name = "Competencias"
)
public class CompetenciaController {

    @Autowired
    CompetenciaService competenciaService;

    @GetMapping()
    public ResponseEntity<CustomResponse<List<Competencia>>> getCompetencias(){
        List<Competencia> competencias = new ArrayList<>();
        Link allCompetenciasLink = linkTo(CompetenciaController.class).withSelfRel();
        List<Link> links = List.of(allCompetenciasLink);

        try {
            competencias = competenciaService.obtenerTodas();
            if (!competencias.isEmpty()) {
                CustomResponse<List<Competencia>> response = new CustomResponse<>(1, "Competencias encontradas", competencias, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse<>(0, "Competencia no encontrada", competencias, links));
            }
        } catch (Exception e) {
            CustomResponse<List<Competencia>> response = new CustomResponse<>(0, "Error interno de servidor", competencias, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Competencia>> getCompetenciaById(@PathVariable("id") Long id){
        Optional<Competencia> competencia=null;
        CustomResponse<Competencia> response=null;
        Link allCompetenciasLink =linkTo(CompetenciaController.class).withSelfRel();
        List<Link> links = List.of(allCompetenciasLink);
        try{
            competencia= Optional.ofNullable(competenciaService.obtenerCompetenciaPorId(id));
            if(competencia.isPresent()){
                response=new CustomResponse<>(1,"Competencia encontrada", competencia.get(), links);
                return  ResponseEntity.status(HttpStatus.OK).body(response);
            }else {
                response=new CustomResponse<>(0, "Competencia no encontrada", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (Exception e){
            response=new CustomResponse<>(0, "Error interno de servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<CustomResponse<Competencia>> createCompetencia(@RequestBody Competencia competencia){
        Link allCompetenciaLink=linkTo(CompetenciaController.class).withSelfRel();
        List<Link> links =List.of(allCompetenciaLink);
        try {
            Competencia competencia1=competenciaService.guardarCompetencia(competencia);
            if (competencia1!=null){
                CustomResponse<Competencia> response =new CustomResponse<>(1,"Competencia creada", competencia1, links);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }else {
                CustomResponse<Competencia> response=new CustomResponse<>(0,"Error interno del servidor", null, links);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }

            }catch (Exception e){
            CustomResponse<Competencia> response=new CustomResponse<>(500, "Error interno de servidor: Verifique sus datos", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    public ResponseEntity<CustomResponse<Competencia>> deleteCompetenciaById(@PathVariable Long id){
        Optional<Competencia> competencia=null;
        CustomResponse<Competencia> response=null;
        Link allComptenciaLink = linkTo(CompetenciaController.class).withSelfRel();
        List<Link> links=List.of(allComptenciaLink);
        try{
            competencia= Optional.ofNullable(competenciaService.obtenerCompetenciaPorId(id));
            if(competencia.isPresent()){
                competenciaService.eliminarCompetencia(id);
                response=new CustomResponse<>(1, "Competencia eliminada", null, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }else {
                response=new CustomResponse<>(0, "Competencia no encontrada", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (Exception e){
            response=new CustomResponse<>(500, "Error interno del servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Competencia>> updateCompetencia(@RequestBody Competencia competencia, @PathVariable("id") Long id  ){
        Link allCompetenciaLink=linkTo(CompetenciaController.class).withSelfRel();
        List<Link> links =List.of(allCompetenciaLink);
        try{
            competencia.setId(id);
            Optional<Competencia> competencia2 = Optional.ofNullable(competenciaService.obtenerCompetenciaPorId(id));
            if(competencia2.isPresent()){
                Competencia competencia1=competenciaService.updateCompetencia(competencia);
                CustomResponse<Competencia> response=new CustomResponse<>(1, "Competencia actualizada", competencia1, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }else{
                CustomResponse<Competencia> response=new CustomResponse<>(0, "Competencia no encontrada", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (Exception e){
            CustomResponse<Competencia> response=new CustomResponse<>(500, "Error interno en el servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }
}

