package it.interno.luoghi.entity;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "TLUOGODETTAGLIO", schema = "DBLUOGHI")
public class TLuogoDettaglio implements Serializable {

    static final long serialVersionUID = -9006059363253522286L;

    @Id
    @Column(name = "CODICELUOGO")
    Integer codiceLuogo ;

    @Column(name = "TIPODETTAGLIOLUOGO")
    Integer descrizioneLuogo ;

    @Column(name = "DATAINIZIOVALIDITA")
    LocalDate dataInizioValidita;

    @Column(name = "DATAFINEVALIDITA")
    LocalDate dataFineValidita;

    @Column(name = "TSINSERIMENTO", scale = 6)
    Timestamp tsInserimento;

    @Column(name = "TSCANCELLAZIONE", scale = 6)
    Timestamp tsCancellazione;

}
