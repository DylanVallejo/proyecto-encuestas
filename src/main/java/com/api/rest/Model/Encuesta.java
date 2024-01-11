package com.api.rest.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.annotation.Order;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Encuesta {

    @Id
    @GeneratedValue
    @Column(name = "encuesta_id")
    private Long id;

    @Column(name = "pregunta")
    private String pregunta;


//    una encuesta puede tener muchas opciones por  lo que es OneToMany RELACION UNO A MUCHOS


    @OneToMany(cascade = CascadeType.ALL)
//    al definir la realacion solo aqui se llama realcion unidireccional si en opcion usaramos manytoone muchas opciones a una
//    encuesa se convertiria en una relaicon bidireccional
    @JoinColumn(name = "encuesta_id")
//    se ordena por opciones
    @OrderBy
    private Set<Opcion> opciones;
}
