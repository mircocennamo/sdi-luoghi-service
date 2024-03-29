package it.interno.luoghi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.interno.luoghi.serializer.LocalDateDeserializer;
import it.interno.luoghi.serializer.LocalDateSerializer;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class LuogoFieldsValidationDto {

    @NotNull(message = "Il campo 'Tipo Luogo {errore.campo.obbligatorio}")
    String tipoLuogo;

    @NotNull(message = "Il campo 'Codice Luogo' {errore.campo.obbligatorio}")
    Integer codiceLuogo ;

    @NotBlank(message = "Il campo 'Descrizione Luogo' {errore.campo.obbligatorio}")
    String descrizioneLuogo ;

    @NotNull(message = "Il campo 'Sigla Provincia' {errore.campo.obbligatorio}")
    String siglaProvincia ;

    @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate dataRif ;

}
