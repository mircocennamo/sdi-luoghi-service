package it.interno.luoghi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "TMEZZOTRAGITTO", schema = "DBLUOGHI")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TMezzoTragitto {

    @Id
    @Column(name = "IDMEZZOTRAGITTO", nullable = false)
    private String idMezzoTragitto ;

    @Column(name = "MEZZOTRAGITTO", nullable = false)
    private String mezzoTragitto ;

    @Column(name = "DATAINIZIOVALIDITA", nullable = false)
    private LocalDate dataInizioValidita;

    @Column(name = "DATAFINEVALIDITA")
    private LocalDate dataFineValidita;

}
