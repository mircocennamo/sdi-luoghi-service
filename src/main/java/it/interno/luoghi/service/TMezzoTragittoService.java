package it.interno.luoghi.service;

import it.interno.luoghi.dto.TMezzoTragittoDto;

import java.time.LocalDate;
import java.util.List;

public interface TMezzoTragittoService {

    List<TMezzoTragittoDto> getAllByData(LocalDate dataRiferimento);

}
