package com.api.rest.Repository;

import com.api.rest.Model.Encuesta;
import com.api.rest.Model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long>{



}
