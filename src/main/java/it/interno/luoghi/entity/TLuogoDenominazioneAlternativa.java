package it.interno.luoghi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TLUOGODENOMINAZIONEALTERNATIVA", schema = "DBLUOGHI")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TLuogoDenominazioneAlternativa implements Serializable {

    private static final long serialVersionUID = 8734143704549644941L;

    @Id
    @Column(name = "CODICELUOGO")
    Integer codiceLuogo ;

    @Column(name = "DESCRIZIONELUOGO", columnDefinition = "CHAR", length = 38)
    String descrizioneLuogo ;

    @Column(name = "SIGLAPROVINCIA", columnDefinition = "CHAR", length = 2)
    String siglaProvincia ;

    @Column(name = "DATAINIZIOVALIDITA")
    LocalDate dataInizioValidita ;

    @Column(name = "DATAFINEVALIDITA")
    LocalDate dataFineValidita ;

}
