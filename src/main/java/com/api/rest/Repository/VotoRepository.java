package com.api.rest.Repository;

import com.api.rest.Model.Encuesta;
import com.api.rest.Model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long>{

    @Query(value = "select v.* from Opcion o , Voto v where o.encuesta_id = ?1 and v.opcion_id = o.opcion_id", nativeQuery = true)
    public Iterable<Voto> findByEncuesta(Long encuestaId);

}
