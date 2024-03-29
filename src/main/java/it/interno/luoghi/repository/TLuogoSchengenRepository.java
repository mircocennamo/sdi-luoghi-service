package it.interno.luoghi.repository;

import it.interno.luoghi.entity.TLuogoSchengen;
import it.interno.luoghi.entity.TLuogoSchengenKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface TLuogoSchengenRepository extends JpaRepository<TLuogoSchengen, TLuogoSchengenKey>, JpaSpecificationExecutor<TLuogoSchengen> {

    Optional<TLuogoSchengen> findBytLuogoSchengenKey_CodiceLuogoAndTsCancellazioneIsNull(String codiceLuogo);

}
