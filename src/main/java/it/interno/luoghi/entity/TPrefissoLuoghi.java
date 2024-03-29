package it.interno.luoghi.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;


@Getter
@Setter
@Entity
@Table(name = "TPREFISSOLUOGHI", schema = "DBLUOGHI")
@NoArgsConstructor
@AllArgsConstructor
@IdClass(TPrefissoLuoghiKey.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TPrefissoLuoghi implements Serializable {

    static final long serialVersionUID = 1265769238200051798L;

    @Id
    @Column(name = "PREFISSO", nullable = false)
    String prefisso;

    @Column(name = "CODICELUOGO", nullable = false)
    Integer codiceLuogo;

    @Column(name = "DESCRIZIONELUOGO", nullable = false)
    String descrizioneLuogo;

    @Column(name = "INLUOGO", nullable = false)
    Integer inLuogo;

    @Column(name = "IMMAGINE", nullable = false)
    @Lob
    Blob immagine;

    @Id
    @Column(name = "TSINSERIMENTO", nullable = false)
    Timestamp tsInserimento;

    @Column(name = "TSCANCELLAZIONE", nullable = false)
    Timestamp tsCancellazione;

    @Column(name = "IDUTENTEINSERIMENTO", nullable = false)
    String idUtenteInserimento;

    @Column(name = "IDUFFICIOINSERIMENTO", nullable = false)
    String idUfficioInserimento;

    @Column(name = "IDUTENTECANCELLAZIONE", nullable = false)
    String idUtenteCancellazione;

    @Column(name = "IDUFFICIOCANCELLAZIONE", nullable = false)
    String isUfficioCancellazione;

}
