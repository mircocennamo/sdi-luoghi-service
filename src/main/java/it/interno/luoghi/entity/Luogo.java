package it.interno.luoghi.entity;


import it.interno.luoghi.dto.LuogoDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "TLUOGO", schema = "DBLUOGHI")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedNativeQuery(
        name = "getLuogoOriginaleSDIByDesLuoAndSglPrvAndData",
        query = "SELECT L.CODICELUOGO, L.DESCRIZIONELUOGO, L.CODICECOMUNE, L.SIGLAPROVINCIA " +
                "FROM DBLUOGHI.TLUOGO L " +
                "WHERE" +
                "     TRIM(L.DESCRIZIONELUOGO) = ?1 " +
                " AND L.SIGLAPROVINCIA = ?2 " +
                " AND L.INLUOGO = '04' " +
                " AND COALESCE(?3, TO_DATE(SYSDATE, 'DD-MM-YYYY')) BETWEEN L.DATAINIZIOVALIDITA " +
                " AND COALESCE(L.DATAFINEVALIDITA, TO_DATE('31.12.9999', 'DD-MM-YYYY')) " +
                " AND L.TSCANCELLAZIONE IS NULL " +
                "UNION ALL " +
                "SELECT L.CODICELUOGO, L.DESCRIZIONELUOGO, L.CODICECOMUNE, L.SIGLAPROVINCIA " +
                "FROM DBLUOGHI.TLUOGO L," +
                "     DBLUOGHI.TLUOGODENOMINAZIONEALTERNATIVA DA " +
                "WHERE" +
                "     TRIM(DA.DESCRIZIONELUOGO) = ?1 " +
                " AND DA.SIGLAPROVINCIA = ?2 " +
                " AND L.CODICELUOGO = DA.CODICELUOGO " +
                " AND L.INLUOGO = '04' " +
                " AND COALESCE(?3, TO_DATE(SYSDATE, 'DD-MM-YYYY')) BETWEEN L.DATAINIZIOVALIDITA " +
                " AND COALESCE(L.DATAFINEVALIDITA, TO_DATE('31.12.9999', 'DD-MM-YYYY')) " +
                " AND L.TSCANCELLAZIONE IS NULL ",
        resultSetMapping = "luogoAlternativi")
@SqlResultSetMapping(
        name = "luogoAlternativi",
        classes = @ConstructorResult(
                targetClass = LuogoDto.class,
                columns = {
                        @ColumnResult(name = "codiceLuogo", type = Integer.class),
                        @ColumnResult(name = "descrizioneLuogo", type = String.class),
                        @ColumnResult(name = "codiceComune", type = String.class),
                        @ColumnResult(name = "siglaProvincia", type = String.class)
                }
        )
)
public class Luogo implements Serializable {

    static final long serialVersionUID = 6213400488990155416L;

    @Id
    @Column(name = "CODICELUOGO")
    Integer codiceLuogo;

    @Column(name = "DATAINIZIOVALIDITA")
    LocalDate dataInizioValidita;

    @Column(name = "INLUOGO", columnDefinition = "CHAR", length = 2)
    String inLuogo;

    @Column(name = "DESCRIZIONELUOGO", columnDefinition = "CHAR", length = 38)
    String descrizioneLuogo;

    @Column(name = "DATAFINEVALIDITA")
    LocalDate dataFineValidita;

    @Column(name = "CODICEREGIONE", columnDefinition = "CHAR", length = 2)
    String codiceRegione;

    @Column(name = "CODICEPROVINCIA", columnDefinition = "CHAR", length = 3)
    String codiceProvincia;

    @Column(name = "CODICECOMUNE", columnDefinition = "CHAR", length = 6)
    String codiceComune;

    @Column(name = "CODICECATASTALE", columnDefinition = "CHAR", length = 4)
    String codiceCatastale;

    @Column(name = "SIGLAPROVINCIA", columnDefinition = "CHAR", length = 2)
    String siglaProvincia;

    @Column(name = "TSINSERIMENTO", scale = 6)
    Timestamp tsInserimento;

    @Column(name = "TSCANCELLAZIONE", scale = 6)
    Timestamp tsCancellazione;

    @Column(name = "CODICELUOGOAREA")
    Integer codiceLuogoArea;

    @Column(name = "CODICELUOGOZONA")
    Integer codiceLuogoZona;

    @Column(name = "CODICELUOGORIFERIMENTO")
    Integer codiceLuogoRiferimento ;

    @Column(name = "SIGLALUOGOASF", columnDefinition = "CHAR", length = 2)
    String siglaLuogoAsf;

    @Column(name = "CODICEISTAT")
    String codiceIstat;

    @Column(name = "CODICEAREA")
    String codiceArea;

    @Column(name = "CODICECONTINENTE")
    String codiceContinente;

    @Column(name = "DENOMINAZIONELUOGOLINGUAITALIANOMAIUSCOLO", length = 100)
    String denominazioneLuogoLinguaItaliaNomaiuscolo;

    @Column(name = "DENOMINAZIONELUOGOLINGUAITALIANOMINUSCOLO", length = 100)
    String denominazioneLuogoLinguaItalianoMinuscolo;

    @Column(name = "DENOMINAZIONELUOGOLINGUAINGLESEOMAIUSCOLO", length = 100)
    String denominazioneLuogoLinguaIngleseoMaiuscolo;

    @Column(name = "DENOMINAZIONELUOGOLINGUAINGLESEMINUSCOLO", length = 100)
    String denominazioneLuogoLinguaIngleseMinuscolo;

    @Column(name = "CODICEMINISTEROINTERNO")
    String codiceMinisteroInterno;

    @Column(name = "CODICEUNSDM49")
    String codiceUnsdm49;

    @Column(name = "CODICEISO3166CARATTERI2",columnDefinition = "CHAR", length = 2)
    String codiceISO3166Caratteri2;

    @Column(name = "CODICEISO3166CARATTERI3", columnDefinition = "CHAR", length = 3)
    String codiceISO3166Caratteri3;

    @Column(name = "CODICEISTATSTATOPADRECARATTERI3")
    String codiceIstatStatoPadreCaratteri3;

    @Column(name = "CODICEISO3166STATOPADRECARATTERI3",columnDefinition = "CHAR", length = 3)
    String codiceISO3166StatoPadreCaratteri3;
}




