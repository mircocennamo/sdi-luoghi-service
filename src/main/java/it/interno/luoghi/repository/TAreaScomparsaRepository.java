package it.interno.luoghi.repository;

import it.interno.luoghi.entity.TAreaScomparsa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TAreaScomparsaRepository extends JpaRepository<TAreaScomparsa, String>, JpaSpecificationExecutor<TAreaScomparsa> {

    @Query(value = "SELECT * FROM DBLUOGHI.TAREASCOMPARSA t " +
            "WHERE ?1 >= t.DATAINIZIOVALIDITA " +
            "AND (?1 <= t.DATAFINEVALIDITA OR t.DATAFINEVALIDITA IS NULL)", nativeQuery = true)
    List<TAreaScomparsa> getAllByData(LocalDate dataRiferimento);

    @Query(value = "SELECT t.* " +
            "FROM DBLUOGHI.TAREASCOMPARSA t  INNER JOIN DBLUOGHI.TMACROAREASCOMPARSA tma ON t.IDMACROAREASCOMPARSA = tma.IDMACROAREASCOMPARSA " +
            "WHERE ?1 >= t.DATAINIZIOVALIDITA " +
            "AND (?1 <= t.DATAFINEVALIDITA OR t.DATAFINEVALIDITA IS NULL) " +
            "AND tma.MACROAREASCOMPARSA = ?2 " +
            "ORDER BY AREASCOMPARSA ", nativeQuery = true)
    List<TAreaScomparsa> getAllByDataAndMacroArea(LocalDate dataRiferimento, String descrizioneMacroArea);

    @Query(value = "SELECT * FROM DBLUOGHI.TAREASCOMPARSA t " +
            "WHERE t.IDAREASCOMPARSA = ?1 " +
            "AND ?2 >= t.DATAINIZIOVALIDITA " +
            "AND (?2 <= t.DATAFINEVALIDITA OR t.DATAFINEVALIDITA IS NULL)", nativeQuery = true)
    TAreaScomparsa getByIdAndData(String idAreaScomparsa, LocalDate dataRif);

    @Query(value = "SELECT t.* " +
            "FROM DBLUOGHI.TAREASCOMPARSA t " +
            "WHERE ?2 >= t.DATAINIZIOVALIDITA " +
            "AND (?2 <= t.DATAFINEVALIDITA OR t.DATAFINEVALIDITA IS NULL) " +
            "AND t.IDMACROAREASCOMPARSA = ?1 " +
            "ORDER BY AREASCOMPARSA ", nativeQuery = true)
    List<TAreaScomparsa> getAllByIdMacroAreaAndData(String idMacroArea, LocalDate dataRiferimento);
}
