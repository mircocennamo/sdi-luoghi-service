package it.interno.luoghi.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TPrefissoLuoghiKey implements Serializable {

    static final long serialVersionUID = 462505759102045000L;
    String prefisso;
    Timestamp tsInserimento;

}
