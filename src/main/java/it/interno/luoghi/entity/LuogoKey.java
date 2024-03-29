package it.interno.luoghi.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LuogoKey implements Serializable{


	static final long serialVersionUID = -5987383127474044150L;
	Integer codiceLuogo;
    Timestamp tsInserimento;

}
