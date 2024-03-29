package it.interno.luoghi.service;

import it.interno.luoghi.entity.TLuogoDenominazioneAlternativa;

import java.util.List;

public interface TLuogoDenominazioneAlternativaService {

    List<TLuogoDenominazioneAlternativa> findByDescrizioneLuogo(String descrizioneLuogo);
}
