package it.interno.luoghi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class LuogoDto {

    Integer codiceLuogo;
    LocalDate dataInizioValidita;
    String inLuogo;
    String descrizioneLuogo;
    LocalDate dataFineValidita;
    String codiceRegione;
    String codiceProvincia;
    String codiceComune;
    String codiceCatastale;
    String siglaProvincia;
    Timestamp tsInserimento;
    Timestamp tsCancellazione;
    Integer codiceLuogoArea;
    Integer codiceLuogoZona;
    Integer codiceLuogoRiferimento ;
    String siglaLuogoAsf ;
    String codiceIstat;
    String codiceArea;
    String codiceContinente;
    String denominazioneLuogoLinguaItaliaNomaiuscolo;
    String denominazioneLuogoLinguaItalianoMinuscolo;
    String denominazioneLuogoLinguaIngleseoMaiuscolo;
    String denominazioneLuogoLinguaIngleseMinuscolo;
    String codiceMinisteroInterno;
    String codiceUnsdm49;
    String codiceISO3166Caratteri2;
    String codiceISO3166Caratteri3;
    String codiceIstatStatoPadreCaratteri3;
    String codiceISO3166StatoPadreCaratteri3;

    public LuogoDto(Integer codiceLuogo, String descrizioneLuogo, String codiceComune, String siglaProvincia) {
        this.codiceLuogo = codiceLuogo;
        this.descrizioneLuogo = descrizioneLuogo;
        this.codiceComune = codiceComune;
        this.siglaProvincia = siglaProvincia;
    }

    public LuogoDto() {
    }
}
