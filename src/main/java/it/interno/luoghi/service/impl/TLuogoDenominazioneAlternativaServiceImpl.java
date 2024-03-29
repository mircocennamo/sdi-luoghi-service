package it.interno.luoghi.service.impl;

import it.interno.luoghi.entity.TLuogoDenominazioneAlternativa;
import it.interno.luoghi.repository.LuogoDenominazioneAlternativaRepository;
import it.interno.luoghi.service.TLuogoDenominazioneAlternativaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TLuogoDenominazioneAlternativaServiceImpl implements TLuogoDenominazioneAlternativaService {

    @Autowired
    private LuogoDenominazioneAlternativaRepository luogoDenominazioneAlternativaRepository;

    @Override
    public List<TLuogoDenominazioneAlternativa> findByDescrizioneLuogo(String descrizioneLuogo) {
         return luogoDenominazioneAlternativaRepository.findBydescrizioneLuogo(descrizioneLuogo);
    }
}
