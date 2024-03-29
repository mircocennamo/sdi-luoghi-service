package it.interno.luoghi.service;

import it.interno.luoghi.dto.TMacroAreaScomparsaDto;
import it.interno.luoghi.entity.TMacroAreaScomparsa;

import java.time.LocalDate;
import java.util.List;

public interface TMacroAreaScomparsaService {

    List<TMacroAreaScomparsaDto> getAllByData(LocalDate dataRiferimento);
    TMacroAreaScomparsa getById(String idMacroAreaScomparsa, LocalDate dataRif);

}
