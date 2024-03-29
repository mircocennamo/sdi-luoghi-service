package it.interno.luoghi.repository;

import it.interno.luoghi.entity.TTipoLuogo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TTipoLuogoRepository extends CrudRepository<TTipoLuogo, String> {

    List<TTipoLuogo> findTTipoLuogoBytipoDenuncia(String tipoDenuncia);

    TTipoLuogo findTTipoLuogoByidTipoLuogo(String idTipoLuogo);

}
