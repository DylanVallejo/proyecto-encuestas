package com.api.rest.Controller;

import com.api.rest.Model.Voto;
import com.api.rest.Repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class VotoController {

    @Autowired
    private VotoRepository votoRepository;

    @PostMapping("/encuestas/{encuestaId}/votos")
    public ResponseEntity crearVoto(@PathVariable Long encuestaId, @RequestBody Voto voto){
        voto = votoRepository.save(voto);
//        establecemos encabezados para los recursos creados
        HttpHeaders httpHeaders = new HttpHeaders( );
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(voto.getId()).toUri());


        return new ResponseEntity(null, httpHeaders , HttpStatus.CREATED);
    }


    @GetMapping("/encuestas/{encuestaId}/votos")
    public Iterable<Voto> listarTodosLosVotos(@PathVariable Long encuestaId){
        return votoRepository.findByEncuesta(encuestaId);
    }


}
