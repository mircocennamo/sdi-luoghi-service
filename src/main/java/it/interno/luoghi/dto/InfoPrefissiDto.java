package it.interno.luoghi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoPrefissiDto {

    private Integer codiceLuogo;
    private String prefisso;
    private String immagine;
    private String descrizioneLuogo;
    private String denominazioneLuogoLinguaIngleseoMaiuscolo;

}
