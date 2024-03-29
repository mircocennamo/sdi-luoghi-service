package it.interno.luoghi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationVeicleNationalityDto {
    @NotNull(message = "Il campo 'Anno Immatricolazione' {errore.campo.obbligatorio}")
    private String annoImmatricolazione;
    @NotNull(message = "Il campo 'Nazionalita' {errore.campo.obbligatorio}")
    private String nazionalita;
}
