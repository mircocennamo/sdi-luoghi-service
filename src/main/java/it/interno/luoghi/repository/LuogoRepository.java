package it.interno.luoghi.repository;

import it.interno.luoghi.dto.LuogoDto;
import it.interno.luoghi.entity.Luogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.ArrayList;

public interface LuogoRepository extends JpaRepository<Luogo, Integer>, JpaSpecificationExecutor<Luogo> {

    @Query(value = "SELECT new it.interno.luoghi.dto.LuogoDto(L.codiceLuogo, TRIM(COALESCE(A.descrizioneLuogo, L.descrizioneLuogo)), " +
                   " L.codiceComune, L.siglaProvincia) " +
                   "FROM Luogo L LEFT JOIN TLuogoDenominazioneAlternativa A ON " +
                   "           A.codiceLuogo = L.codiceLuogo " +
                   "WHERE" +
                   "     TRIM(L.descrizioneLuogo) = :descrizioneLuogo " +
                   " AND L.inLuogo = '04' " +
                   " AND COALESCE(:dataRif, to_date(sysdate, 'DD-MM-YYYY')) BETWEEN L.dataInizioValidita " +
                   " AND COALESCE(L.dataFineValidita, to_date('31.12.9999', 'DD-MM-YYYY')) " +
                   " AND L.siglaProvincia = :siglaProvincia " +
                   " AND L.tsCancellazione IS NULL ")
    LuogoDto getLuogoAlternativoSDIByDesLuoAndSglPrvAndData(@Param("descrizioneLuogo") String descrizioneLuogo,
                                                                  @Param("siglaProvincia") String siglaProvincia,
                                                                  @Param("dataRif") LocalDate dataRif) ;

    @Query(name = "getLuogoOriginaleSDIByDesLuoAndSglPrvAndData", nativeQuery = true)
    LuogoDto getLuogoOriginaleSDIByDesLuoAndSglPrvAndData(String descrizioneLuogo, String siglaProvincia, LocalDate dataRif) ;

    @Query(value = "SELECT * FROM DBLUOGHI.TLUOGO t \n" +
            "WHERE EXTRACT(YEAR FROM t.DATAINIZIOVALIDITA) < ?1 \n" +
            "AND NVL(EXTRACT(YEAR FROM t.DATAFINEVALIDITA), '2100') > ?1\n" +
            "AND CODICELUOGO = ?2", nativeQuery = true)
    Luogo checkVeicleNationality(String annoImmatricolazione, String nazionalita);

    @Query(value = "SELECT * " +
            "FROM DBLUOGHI.TLUOGO t " +
            "WHERE (t.DESCRIZIONELUOGO like :descrizione% OR t.DENOMINAZIONELUOGOLINGUAINGLESEOMAIUSCOLO like :descrizione%) " +
            "AND t.INLUOGO = :inLuogo " +
            "AND :dataRiferimento >= t.DATAINIZIOVALIDITA \n" +
            "AND (:dataRiferimento <= t.DATAFINEVALIDITA OR t.DATAFINEVALIDITA IS NULL)", nativeQuery = true)
    ArrayList<Luogo> getLuoghiByDescrizioneAndInLuogoAndDataRiferimento(String descrizione, String inLuogo, LocalDate dataRiferimento);
}
