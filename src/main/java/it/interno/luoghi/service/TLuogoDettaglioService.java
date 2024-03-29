package it.interno.luoghi.service;

import java.time.LocalDate;
import java.util.Date;

public interface TLuogoDettaglioService {

    Boolean verificaNomeObbligatorio(Integer codiceLuogo, LocalDate dataRif) ;

}
