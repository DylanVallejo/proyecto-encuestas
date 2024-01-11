package com.api.rest.Controller;

import com.api.rest.Model.Voto;
import com.api.rest.Repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VotoController {

    @Autowired
    private VotoRepository votoRepository;

    @PostMapping("/encuestas/{encuestaId}")
    public ResponseEntity opcionElegida(@RequestBody Voto voto, Long encuestaId){


        return null;
    }


}
