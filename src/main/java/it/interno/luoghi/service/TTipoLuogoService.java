package it.interno.luoghi.service;

import it.interno.luoghi.dto.TTipoLuogoDto;

import java.util.List;

public interface TTipoLuogoService {

    List<TTipoLuogoDto> findTipoLuogoByTipoDenuncia(String tipoDenuncia);

    TTipoLuogoDto findTipoLuogoById(String tTipoLuogo) ;

}
