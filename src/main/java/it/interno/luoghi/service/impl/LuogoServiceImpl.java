package it.interno.luoghi.service.impl;

import it.interno.luoghi.dto.LuogoDto;
import it.interno.luoghi.dto.ResponseDto;
import it.interno.luoghi.entity.Luogo;
import it.interno.luoghi.entity.TLuogoSchengen;
import it.interno.luoghi.exception.NoDataException;
import it.interno.luoghi.exception.ValidationFieldException;
import it.interno.luoghi.mapper.LuogoMapper;
import it.interno.luoghi.repository.LuogoRepository;
import it.interno.luoghi.repository.TLuogoSchengenRepository;
import it.interno.luoghi.service.LuogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static it.interno.luoghi.repository.specifications.LuogoSpecifications.*;

@Service
@Transactional(readOnly = true)
public class LuogoServiceImpl implements LuogoService {

    @Autowired
    private LuogoRepository luogoRepository;

    @Autowired
    private TLuogoSchengenRepository tLuogoSchengenRepository;

    @Autowired
    private LuogoMapper luogoMapper ;

    @Override
    public List<LuogoDto> getLuoghiByDesLuoAndInLuoAndDataRif(String desLuo, String inLuo, LocalDate dataRif) {

        List<Luogo> list = luogoRepository.findAll(tsCancellazioneIsNull()
                                      .and(desLuoLike(desLuo))
                                      .and(inLuoEquals(inLuo))
                                      .and(dataLessThanDIniVal(dataRif))
                                      .and(dataRifGreaterThanDFinVal(dataRif)), Sort.by(Sort.Direction.ASC, "descrizioneLuogo"));
        List<LuogoDto> listResult = this.setListaLuogoDto(list) ;
        return listResult ;
    }

    @Override
    public List<LuogoDto> getLuoghiRilascioDocumentiByDesLuoAndInLuoAndDataRif(String desLuo, String inLuo, LocalDate dataRif) {
        List<Luogo> list = luogoRepository.findAll(retrievePlacesForDocuments(desLuo, inLuo, dataRif), Sort.by(Sort.Direction.ASC, "descrizioneLuogo"));
        List<LuogoDto> listResult = this.setListaLuogoDto(list) ;
        return listResult ;

    }

    private List<LuogoDto> setListaLuogoDto(List<Luogo> luoghi) {

        List<LuogoDto> luogoDtos = new ArrayList<>();
        luoghi.stream().forEach(luogo -> {
            LuogoDto luogoDto = luogoMapper.luogoToLuogoDto(luogo);
            luogoDtos.add(luogoDto);
        });

        return luogoDtos;
    }

    @Override
    public LuogoDto getLuogoAlternativoSDIByDesLuoAndSglPrvAndData(String descrizioneLuogo, String siglaProvincia, LocalDate dataRif) {
        return luogoRepository.getLuogoAlternativoSDIByDesLuoAndSglPrvAndData(descrizioneLuogo, siglaProvincia, dataRif);
    }

    @Override
    public LuogoDto getLuogoOriginaleSDIByDesLuoAndSglPrvAndData(String descrizioneLuogo, String siglaProvincia, LocalDate dataRif) {
        return luogoRepository.getLuogoOriginaleSDIByDesLuoAndSglPrvAndData(descrizioneLuogo, siglaProvincia, dataRif);
    }

    @Override
    public ResponseDto validazioneLuogo(String tipoLuogo, Integer codiceLuogo, String descrizioneLuogo, String siglaProvincia, LocalDate dataRif) throws ValidationFieldException {
        Optional<Luogo> op = luogoRepository.findOne(tsCancellazioneIsNull()
                .and(inLuoEquals(tipoLuogo))
                .and(cLuoEquals(codiceLuogo))
                .and(desLuoEquals(descrizioneLuogo))
                .and(sglPrvEquals(siglaProvincia))
                .and(dataLessThanDIniVal(dataRif))
                .and(dataRifGreaterThanDFinVal(dataRif)));
        if(!op.isPresent()) {
            throw new ValidationFieldException("luogo.errore.luogoNotFound", null) ;
        }

        return ResponseDto.builder().code(HttpStatus.OK.value()).build() ;
    }

    @Override
    public String getCodiceNazioneInterpol(Integer codiceLuogo) throws NoDataException {

        Optional<Luogo> queryLuogo = luogoRepository.findOne(cLuoEquals(codiceLuogo));
        Luogo luogo = queryLuogo.orElseThrow(() -> new NoDataException("errore.codnazione.asf.nonTrovato"));

        return luogo.getSiglaLuogoAsf();
    }

    @Override
    public String getCodiceNazioneSchengen(String codiceLuogo) {

        Optional<TLuogoSchengen> queryLuogo = tLuogoSchengenRepository.findBytLuogoSchengenKey_CodiceLuogoAndTsCancellazioneIsNull(codiceLuogo);
        return queryLuogo.isPresent() ? queryLuogo.get().getTLuogoSchengenKey().getCodiceLuogoSchengen() : "0999";
    }

    @Override
    public List<LuogoDto> getLuoghiComuniNazioniDiversiDaItaliaByDesLuoAndDataRif(String desLuo, LocalDate dataRif) {
        List<Luogo> list = luogoRepository.findAll(retrieveLuoghiComuniNazioniDiversiDaItalia(desLuo, dataRif), Sort.by(Sort.Direction.ASC, "descrizioneLuogo")) ;
        List<LuogoDto> listResult = this.setListaLuogoDto(list) ;
        return listResult ;

    }

    @Override
    public LuogoDto getCodiceCatastaleByCodiceLuogoAndDataRif(Integer codiceLuogo, LocalDate dataRiferimento) throws NoDataException {
        Optional<Luogo> queryByCodLuo = luogoRepository.findOne(
                tsCancellazioneIsNull()
                .and(cLuoEquals(codiceLuogo))
                .and(dataLessThanDIniVal(dataRiferimento))
                .and(dataRifGreaterThanDFinVal(dataRiferimento))
        );

        return luogoMapper.luogoToLuogoDto(queryByCodLuo.orElseThrow(() -> new NoDataException("luogo.errore.luogoNotFound")));
    }

    @Override
    public boolean checkVeicleNationality(String annoImmatricolazione, String nazionalita) {
        return luogoRepository.checkVeicleNationality(annoImmatricolazione, nazionalita) != null;
    }

    @Override
    public List<LuogoDto> getLuoghiComuniProvinceRegioniNazioniDiversiDaItaliaByInLuoAndDesLuoAndDataRif(String inLuo, String desLuo, LocalDate dataRif) {

        List<Luogo> list = luogoRepository.findAll(tsCancellazioneIsNull()
                .and(inLuoEquals(inLuo))
                .and(desLuoLike(desLuo))
                .and(desLuoNotItaly())
                .and(dataLessThanDIniVal(dataRif))
                .and(dataRifGreaterThanDFinVal(dataRif)), Sort.by(Sort.Direction.ASC, "descrizioneLuogo"));
        List<LuogoDto> listResult = this.setListaLuogoDto(list) ;
        return listResult ;
    }

}
