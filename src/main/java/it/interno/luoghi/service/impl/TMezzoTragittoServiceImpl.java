package it.interno.luoghi.service.impl;

import it.interno.luoghi.dto.TMezzoTragittoDto;
import it.interno.luoghi.repository.TMezzoTragittoRepository;
import it.interno.luoghi.service.TMezzoTragittoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TMezzoTragittoServiceImpl implements TMezzoTragittoService {

    @Autowired
    private TMezzoTragittoRepository repository;

    @Override
    public List<TMezzoTragittoDto> getAllByData(LocalDate dataRiferimento) {
        return TMezzoTragittoDto.convertToDtoList(repository.getAllByData(dataRiferimento));
    }
}
