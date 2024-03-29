package it.interno.luoghi.service.impl;

import it.interno.luoghi.entity.TLuogoDettaglio;
import it.interno.luoghi.repository.LuogoDettaglioRepository;
import it.interno.luoghi.service.TLuogoDettaglioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import static it.interno.luoghi.repository.specifications.TLuogoDettaglioSpecifications.*;

@Service
@Transactional(readOnly = true)
public class TLuogoDettaglioServiceImpl implements TLuogoDettaglioService {

    @Autowired
    LuogoDettaglioRepository luogoDettaglioRepository ;

    @Override
    public Boolean verificaNomeObbligatorio(Integer codiceLuogo, LocalDate dataRif) {
        Optional<TLuogoDettaglio> optional = luogoDettaglioRepository.findOne(tsCancellazioneIsNull()
                .and(codiceLuogoEqual(codiceLuogo))
                .and(dataLessThanDIniVal(dataRif))
                .and(dataRifGreaterThanDFinVal(dataRif)));
        return !optional.isPresent() ;
    }

}
