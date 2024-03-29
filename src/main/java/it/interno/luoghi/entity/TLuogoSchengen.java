package it.interno.luoghi.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TLUOGOSCHENGEN", schema = "DBLUOGHI")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TLuogoSchengen implements Serializable {

    private static final long serialVersionUID = 6663663901039247343L;

    @EmbeddedId
    TLuogoSchengenKey tLuogoSchengenKey;

    @Column(name = "TSCANCELLAZIONE")
    LocalDateTime tsCancellazione;

}
