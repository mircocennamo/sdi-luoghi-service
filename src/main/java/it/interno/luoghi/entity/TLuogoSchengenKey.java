package it.interno.luoghi.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Embeddable
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TLuogoSchengenKey implements Serializable {

    private static final long serialVersionUID = -752705084443813857L;

    @Column(name = "CODICELUOGO", nullable = false)
    String codiceLuogo;

    @Column(name = "CODICELUOGOSCHENGEN", nullable = false)
    String codiceLuogoSchengen;

    @Column(name = "TSINSERIMENTO", nullable = false)
    LocalDateTime tsInserimento;
}
