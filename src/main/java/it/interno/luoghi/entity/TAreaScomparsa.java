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
@Table(name = "TAREASCOMPARSA", schema = "DBLUOGHI")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TAreaScomparsa {

    @Id
    @Column(name = "IDAREASCOMPARSA", nullable = false)
    private String idAreaScomparsa;

    @Column(name = "IDMACROAREASCOMPARSA", nullable = false)
    private String idMacroAreaScomparsa;

    @Column(name = "AREASCOMPARSA", nullable = false)
    private String areaScomparsa;

    @Column(name = "DATAINIZIOVALIDITA", nullable = false)
    private LocalDate dataInizioValidita;

    @Column(name = "DATAFINEVALIDITA")
    private LocalDate dataFineValidita;

}
