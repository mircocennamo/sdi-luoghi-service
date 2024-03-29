package it.interno.luoghi.service;

import it.interno.luoghi.dto.LuogoDto;
import it.interno.luoghi.dto.ResponseDto;
import it.interno.luoghi.exception.NoDataException;
import it.interno.luoghi.exception.ValidationFieldException;

import java.time.LocalDate;
import java.util.List;

public interface LuogoService {

    List<LuogoDto> getLuoghiByDesLuoAndInLuoAndDataRif(String desLuo, String inLuo, LocalDate dataRif);

    List<LuogoDto> getLuoghiRilascioDocumentiByDesLuoAndInLuoAndDataRif(String desLuo, String inLuo, LocalDate dataRif);

    LuogoDto getLuogoAlternativoSDIByDesLuoAndSglPrvAndData(String descrizioneLuogo, String siglaProvincia, LocalDate dataRif) ;

    LuogoDto getLuogoOriginaleSDIByDesLuoAndSglPrvAndData(String descrizioneLuogo, String siglaProvincia, LocalDate dataRif) ;

    ResponseDto validazioneLuogo(String tipoLuogo, Integer codiceLuogo, String descrizioneLuogo, String siglaProvincia, LocalDate dataRif) throws ValidationFieldException;

    String getCodiceNazioneInterpol(Integer codiceLuogo) throws NoDataException;

    String getCodiceNazioneSchengen(String codiceLuogo) throws NoDataException;

    List<LuogoDto> getLuoghiComuniNazioniDiversiDaItaliaByDesLuoAndDataRif(String desLuo, LocalDate dataRif) ;

    LuogoDto getCodiceCatastaleByCodiceLuogoAndDataRif(Integer codiceLuogo, LocalDate dataNascita) throws NoDataException;

    boolean checkVeicleNationality(String annoImmatricolazione, String nazionalita);

    List<LuogoDto> getLuoghiComuniProvinceRegioniNazioniDiversiDaItaliaByInLuoAndDesLuoAndDataRif(String inLuo, String desLuo, LocalDate dataRif);

}
