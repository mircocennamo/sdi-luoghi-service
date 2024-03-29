package it.interno.luoghi.service.impl;

import it.interno.luoghi.dto.TTipoLuogoDto;
import it.interno.luoghi.entity.TTipoLuogo;
import it.interno.luoghi.mapper.TTipoLuogoMapper;
import it.interno.luoghi.repository.TTipoLuogoRepository;
import it.interno.luoghi.service.TTipoLuogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TTipoLuogoServiceImpl implements TTipoLuogoService {

    @Autowired
    private TTipoLuogoRepository tTipoLuogoRepository;

    @Autowired
    private TTipoLuogoMapper tTipoLuogoMapper;

    @Override
    public List<TTipoLuogoDto> findTipoLuogoByTipoDenuncia(String tipoDenuncia) {

        List<TTipoLuogo> tipoLuogo = tTipoLuogoRepository.findTTipoLuogoBytipoDenuncia(tipoDenuncia.toUpperCase());
        return tipoLuogo.stream()
                .map(tTipoLuogoMapper::toTTipoLuogoDto)
                .collect(Collectors.toList());
    }

    @Override
    public TTipoLuogoDto findTipoLuogoById(String idTipoLuogo) {
        TTipoLuogo entity = tTipoLuogoRepository.findTTipoLuogoByidTipoLuogo(idTipoLuogo);
        return tTipoLuogoMapper.toTTipoLuogoDto(entity);
    }

}
