package it.interno.luoghi.service.impl;

import it.interno.luoghi.dto.TMacroAreaScomparsaDto;
import it.interno.luoghi.entity.TMacroAreaScomparsa;
import it.interno.luoghi.repository.TMacroAreaScomparsaRepository;
import it.interno.luoghi.service.TMacroAreaScomparsaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TMacroAreaScomparsaServiceImpl implements TMacroAreaScomparsaService {

    @Autowired
    private TMacroAreaScomparsaRepository repository;

    @Override
    public List<TMacroAreaScomparsaDto> getAllByData(LocalDate dataRiferimento) {
        return TMacroAreaScomparsaDto.convertToDtoList(repository.getAllByData(dataRiferimento));
    }

    @Override
    public TMacroAreaScomparsa getById(String idMacroAreaScomparsa, LocalDate dataRif) {
        return repository.getByIdAndData(idMacroAreaScomparsa, dataRif);
    }


}
