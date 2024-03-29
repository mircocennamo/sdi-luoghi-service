package it.interno.luoghi.service.impl;

import it.interno.luoghi.dto.TAreaScomparsaDto;
import it.interno.luoghi.entity.TAreaScomparsa;
import it.interno.luoghi.mapper.AreaMapper;
import it.interno.luoghi.repository.TAreaScomparsaRepository;
import it.interno.luoghi.repository.TMacroAreaScomparsaRepository;
import it.interno.luoghi.service.TAreaScomparsaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TAreaScomparsaServiceImpl implements TAreaScomparsaService {

    @Autowired
    private TAreaScomparsaRepository tAreaScomparsaRepository;
    @Autowired
    private TMacroAreaScomparsaRepository tMacroAreaScomparsaRepository;
    @Autowired
    private AreaMapper areaMapper;

    @Override
    public HashMap<String, List<TAreaScomparsaDto>> getByDescrizioneMacroAreaAndData(String[] descrizioni, LocalDate dataRiferimento){

        HashMap<String, List<TAreaScomparsaDto>> result = new HashMap<>();

        // Se non sono state fornite descrizioni delle macro aree le prendo tutte dal DB e le sovrascrivo all'array "descrizioni"
        if(descrizioni == null || descrizioni.length == 0){
            List<String> descrizioniMacroAree = tMacroAreaScomparsaRepository.getDescrizioniByData(dataRiferimento);
            descrizioni = descrizioniMacroAree.toArray(new String[0]);
        }

        // Per ogni descrizione delle macro aree prendo la lista delle relative aree e le metto nell'HashMap
        for(String descrizione : descrizioni){
            result.put(descrizione, TAreaScomparsaDto.convertToDtoList(tAreaScomparsaRepository.getAllByDataAndMacroArea(dataRiferimento, descrizione)));
        }

        return result;
    }

    @Override
    public TAreaScomparsa getById(String idAreaScomparsa, LocalDate dataRif) {
        return tAreaScomparsaRepository.getByIdAndData(idAreaScomparsa, dataRif);
    }

    @Override
    public List<TAreaScomparsaDto> getAllByIdMacroAreaAndData(String idMacroArea, LocalDate dataRiferimento) {
        return tAreaScomparsaRepository.getAllByIdMacroAreaAndData(idMacroArea, dataRiferimento)
                .stream().map(el -> areaMapper.toDto(el)).collect(Collectors.toList());
    }
}
