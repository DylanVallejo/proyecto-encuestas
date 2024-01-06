package com.api.rest.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Voto {

    @Id
    @GeneratedValue
    @Column(name = "voto_id")
    private Long id;

//    muchos votos van a pertenecer a una opcion
    @ManyToOne  //indicamos que vamos a tener de 0 a muchas instancias de Opciones en votos
//    o un voto puede tener varias opciones
    @JoinColumn(name = "opcion_id")
    private Opcion opcion;
}
