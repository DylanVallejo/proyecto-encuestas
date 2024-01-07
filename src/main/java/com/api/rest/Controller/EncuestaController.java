package com.api.rest.Controller;


import com.api.rest.Model.Encuesta;
import com.api.rest.Repository.EncuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
    public ResponseEntity<?> crearEncuesta(@RequestBody Encuesta encuesta){
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

}
