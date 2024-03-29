package it.interno.luoghi.repository;

import it.interno.luoghi.entity.TPrefissoLuoghi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.ArrayList;

public interface TPrefissoLuoghiRepository extends JpaRepository<TPrefissoLuoghi, String>, JpaSpecificationExecutor<TPrefissoLuoghi> {

    @Query(value = "SELECT * " +
            "FROM DBLUOGHI.TPREFISSOLUOGHI t " +
            "WHERE t.CODICELUOGO = ?1 " +
            "AND t.INLUOGO = ?2 " +
            "AND t.TSCANCELLAZIONE IS NULL", nativeQuery = true)
    ArrayList<TPrefissoLuoghi> findAllByCodiceLuogoAndInLuogoAndData(Integer codice, String inLuogo, LocalDate dataRiferimento);

    @Query(value = " SELECT B.PREFISSO, B.CODICELUOGO, TRIM(A.DESCRIZIONELUOGO) AS DESCRIZIONELUOGO, " +
                   "        B.INLUOGO, B.IMMAGINE, " +
                   "        B.TSINSERIMENTO, B.IDUTENTEINSERIMENTO, B.IDUFFICIOINSERIMENTO, " +
                   "        B.TSCANCELLAZIONE, B.IDUTENTECANCELLAZIONE, B.IDUFFICIOCANCELLAZIONE " +
                   " FROM DBLUOGHI.TLUOGO A, " +
                   "      DBLUOGHI.TPREFISSOLUOGHI B " +
                   " WHERE " +
                   "      A.INLUOGO = :inLuogo " +
                   "  AND (A.DESCRIZIONELUOGO LIKE :descrizione% OR A.DENOMINAZIONELUOGOLINGUAINGLESEOMAIUSCOLO LIKE :descrizione% OR B.PREFISSO LIKE :descrizione%) " +
                   "  AND :dataRiferimento BETWEEN A.DATAINIZIOVALIDITA AND NVL(A.DATAFINEVALIDITA, TO_DATE('31.12.9999', 'DD.MM.YYYY') ) " +
                   "  AND A.TSCANCELLAZIONE IS NULL " +
                   "  AND B.INLUOGO = A.INLUOGO " +
                   "  AND B.CODICELUOGO = A.CODICELUOGO " +
                   "  AND B.TSCANCELLAZIONE IS NULL " +
                   " ORDER BY DESCRIZIONELUOGO ASC ", nativeQuery = true)
    ArrayList<TPrefissoLuoghi> findPrefissoByInLuoAndDescrizioneAndData(String inLuogo, String descrizione, LocalDate dataRiferimento);

}
