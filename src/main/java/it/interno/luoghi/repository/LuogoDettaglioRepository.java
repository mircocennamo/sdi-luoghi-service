package it.interno.luoghi.repository;

import it.interno.luoghi.entity.TLuogoDettaglio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LuogoDettaglioRepository extends JpaRepository<TLuogoDettaglio, Integer>, JpaSpecificationExecutor<TLuogoDettaglio>  {

}
