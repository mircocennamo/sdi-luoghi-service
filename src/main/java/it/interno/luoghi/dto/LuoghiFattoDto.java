package it.interno.luoghi.dto;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class LuoghiFattoDto implements Serializable {
	private static final long serialVersionUID = 864329722533905751L;

	Integer codiceLuogo;
	String descrizioneLuogo;
	String codiceProvincia;
	String siglaProvincia;
	String codiceCatastale;

}
