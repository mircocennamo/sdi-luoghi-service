package it.interno.luoghi.repository;

import it.interno.luoghi.entity.TAreaScomparsa;
import it.interno.luoghi.entity.TMacroAreaScomparsa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TMacroAreaScomparsaRepository extends JpaRepository<TMacroAreaScomparsa, String>, JpaSpecificationExecutor<TMacroAreaScomparsa> {

    @Query(value = "SELECT * FROM DBLUOGHI.TMACROAREASCOMPARSA t " +
            "WHERE ?1 >= t.DATAINIZIOVALIDITA " +
            "AND (?1 <= t.DATAFINEVALIDITA OR t.DATAFINEVALIDITA IS NULL)", nativeQuery = true)
    List<TMacroAreaScomparsa> getAllByData(LocalDate dataRiferimento);

    @Query(value = "SELECT t.MACROAREASCOMPARSA FROM DBLUOGHI.TMACROAREASCOMPARSA t " +
            "WHERE ?1 >= t.DATAINIZIOVALIDITA " +
            "AND (?1 <= t.DATAFINEVALIDITA OR t.DATAFINEVALIDITA IS NULL)", nativeQuery = true)
    List<String> getDescrizioniByData(LocalDate dataRiferimento);

    @Query(value = "SELECT * FROM DBLUOGHI.TMACROAREASCOMPARSA t " +
            "WHERE t.IDMACROAREASCOMPARSA = ?1 " +
            "AND ?2 >= t.DATAINIZIOVALIDITA " +
            "AND (?2 <= t.DATAFINEVALIDITA OR t.DATAFINEVALIDITA IS NULL)", nativeQuery = true)
    TMacroAreaScomparsa getByIdAndData(String idMacroAreaScomparsa, LocalDate dataRif);
}
