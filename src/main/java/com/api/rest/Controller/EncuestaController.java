package com.api.rest.Controller;


import com.api.rest.Exception.ResourceNotFoundException;
import com.api.rest.Model.Encuesta;
import com.api.rest.Repository.EncuestaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EncuestaController {

    @Autowired
    private EncuestaRepository encuestaRepository;

    @GetMapping("/encuestas")
    public ResponseEntity<Iterable<Encuesta>> listarTodasLasEncuestas(){
        return new ResponseEntity<>(encuestaRepository.findAll(), HttpStatus.OK);
    }


    @PostMapping("/encuestas")
    public ResponseEntity<?> crearEncuesta(@Valid @RequestBody Encuesta encuesta){
        encuesta = encuestaRepository.save(encuesta);


//      al crear una encuesta generara un id devolveremos el codigo de creado y creamos una uri con el id generado de la encuesta
//        que retornara en las cabeceras
        HttpHeaders httpHeaders = new HttpHeaders();
        URI newEncuestaUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(encuesta.getId()).toUri();
        httpHeaders.setLocation(newEncuestaUri);
//        Uri newEncuestaUri = servletUriComponentsBuil

        return new ResponseEntity<>(null, httpHeaders,HttpStatus.CREATED);
    }

    @GetMapping("/encuestas/{encuestaId}")
    public ResponseEntity<?> obtenerEncuesta(@PathVariable Long encuestaId){
        verifyEncuesta(encuestaId);
        Optional<Encuesta> encuesta = encuestaRepository.findById(encuestaId);

        if (encuesta.isPresent()){
            return new ResponseEntity<>(encuesta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

//realizar el metodo para eliminar y actualizar

    @DeleteMapping("/encuestas/{encuestaId}")
    public ResponseEntity<?> eliminarEncuestas(@PathVariable Long encuestaId){
        verifyEncuesta(encuestaId);
        encuestaRepository.deleteById(encuestaId);
        return new ResponseEntity<>("se ha eliminado la encuesta con id :" + encuestaId , HttpStatus.OK);
    }


    @PutMapping("/encuestas/{encuestaId}")
    public ResponseEntity<?> actualizarEncuesta(@Valid @RequestBody Encuesta encuesta,@PathVariable Long encuestaId) {
        verifyEncuesta(encuestaId);
        encuesta.setId(encuestaId);
        Encuesta encuestaResponse  = encuestaRepository.save(encuesta);
//        return new ResponseEntity<>(encuestaResponse, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    protected void verifyEncuesta( Long encuestaId){
        Optional<Encuesta> encuesta = encuestaRepository.findById(encuestaId);
        if(!encuesta.isPresent()){
            throw  new ResourceNotFoundException("Encuesta con el ID : " + encuestaId + " no encontrada. ");
        }
    }



}
