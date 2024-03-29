package it.interno.luoghi.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "TTIPOLUOGO", schema = "DBLUOGHI")
public class TTipoLuogo implements Serializable {

    static final long serialVersionUID = 8949840353112023534L;

    @Id
    @Column(name = "IDTIPOLUOGO", columnDefinition = "CHAR", length = 4)
    String idTipoLuogo;

    @Column(name = "TIPOLUOGO", length = 100)
    String tipoLuogo;

    @Column(name = "TIPODENUNCIA", length = 100)
    String tipoDenuncia;

    @Column(name = "DATAINIZIOVALIDITA")
    LocalDate dataInizioValidita;

    @Column(name = "DATAFINEVALIDITA")
    LocalDate dataFineValidita;
}
