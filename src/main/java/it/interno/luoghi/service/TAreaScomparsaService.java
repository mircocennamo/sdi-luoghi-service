package it.interno.luoghi.service;

import it.interno.luoghi.dto.TAreaScomparsaDto;
import it.interno.luoghi.entity.TAreaScomparsa;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public interface TAreaScomparsaService {

    HashMap<String, List<TAreaScomparsaDto>> getByDescrizioneMacroAreaAndData(String[] descrizioni, LocalDate dataRiferimento);
    TAreaScomparsa getById(String idAreaScomparsa, LocalDate dataRif);
    List<TAreaScomparsaDto> getAllByIdMacroAreaAndData(String idMacroArea, LocalDate dataRiferimento);
}
