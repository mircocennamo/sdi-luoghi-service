package it.interno.luoghi.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TTipoLuogoDto {

    String idTipoLuogo;
    String tipoLuogo;
    String tipoDenuncia;
    LocalDate dataInizioValidita;
    LocalDate dataFineValidita;
}
