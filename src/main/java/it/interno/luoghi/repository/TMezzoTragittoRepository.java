package it.interno.luoghi.repository;

import it.interno.luoghi.entity.TMezzoTragitto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TMezzoTragittoRepository extends JpaRepository<TMezzoTragitto, String>, JpaSpecificationExecutor<TMezzoTragitto> {

    @Query(value = "SELECT * FROM DBLUOGHI.TMEZZOTRAGITTO t " +
            "WHERE ?1 >= t.DATAINIZIOVALIDITA " +
            "AND (?1 <= t.DATAFINEVALIDITA OR t.DATAFINEVALIDITA IS NULL)", nativeQuery = true)
    List<TMezzoTragitto> getAllByData(LocalDate dataRiferimento);

}
