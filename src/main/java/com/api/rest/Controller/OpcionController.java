package com.api.rest.Controller;

import com.api.rest.Repository.OpcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpcionController {
    @Autowired
    private OpcionRepository opcionRepository;

}
