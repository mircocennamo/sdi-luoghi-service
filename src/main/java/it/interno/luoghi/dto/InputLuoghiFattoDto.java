package it.interno.luoghi.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class InputLuoghiFattoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message ="Errore nel passaggio di inputLuogo, il parametro non puo essere vuoto.")
	@NotNull(message ="Errore nel passaggio di inputLuogo, il parametro non puo essere nullo.")
	//@Pattern(regexp = Constants.REGEX_VALID_AUDIT_INLUOGO, message = "Inserito carattere non valido in inputLuogo.")
	@Size(max = 2, message = "Il campo 'inputLuogo' {errore.campo.troppolungo}")
	private String inputLuogo;
	
	@Size(max = 3, message = "Il campo 'codiceProvincia' {errore.campo.troppolungo}")
	//@Pattern(regexp = Constants.REGEX_VALID_CODICE_PROVINCIA, message = "Inserito valore non valido in codiceProvincia.")
	private String codiceProvincia;
	
	@Size(max = 38, message = "Il campo 'descrizioneLuogo' {errore.campo.troppolungo}")
	//@Pattern(regexp = Constants.REGEX_VALID_DESCRIZIONE, message = "Inserito carattere non valido in descrizioneLuogo.")
	private String descrizioneLuogo;
}
