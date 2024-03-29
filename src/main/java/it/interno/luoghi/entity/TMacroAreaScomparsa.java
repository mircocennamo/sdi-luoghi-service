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
@Table(name = "TMACROAREASCOMPARSA", schema = "DBLUOGHI")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TMacroAreaScomparsa {

    @Id
    @Column(name = "IDMACROAREASCOMPARSA", nullable = false)
    private String idMacroAreaScomparsa;

    @Column(name = "MACROAREASCOMPARSA", nullable = false)
    private String macroAreaScomparsa;

    @Column(name = "DATAINIZIOVALIDITA", nullable = false)
    private LocalDate dataInizioValidita;

    @Column(name = "DATAFINEVALIDITA")
    private LocalDate dataFineValidita;

}
