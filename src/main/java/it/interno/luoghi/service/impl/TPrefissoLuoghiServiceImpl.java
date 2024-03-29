package it.interno.luoghi.service.impl;

import it.interno.luoghi.dto.InfoPrefissiDto;
import it.interno.luoghi.entity.Luogo;
import it.interno.luoghi.entity.TPrefissoLuoghi;
import it.interno.luoghi.repository.LuogoRepository;
import it.interno.luoghi.repository.TPrefissoLuoghiRepository;
import it.interno.luoghi.service.TPrefissoLuoghiService;
import it.interno.luoghi.util.ConversionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Locale;

@Service
public class TPrefissoLuoghiServiceImpl implements TPrefissoLuoghiService {

    @Autowired
    private TPrefissoLuoghiRepository tPrefissoLuoghiRepository;

    @Autowired
    private LuogoRepository luogoRepository;

    @Override
    public ArrayList<InfoPrefissiDto> getInfoPrefissi(String descrizione, String inLuogo, LocalDate dataRiferimento) {

        ArrayList<InfoPrefissiDto> result = new ArrayList<>() ;

        ArrayList<TPrefissoLuoghi> prefissoList = tPrefissoLuoghiRepository.findPrefissoByInLuoAndDescrizioneAndData(inLuogo, descrizione, dataRiferimento);
        if(!CollectionUtils.isEmpty(prefissoList)) {
            prefissoList.forEach(tPrefissoLuoghi -> {
                InfoPrefissiDto prefissiDto = new InfoPrefissiDto() ;
                prefissiDto.setPrefisso(tPrefissoLuoghi.getPrefisso());
                prefissiDto.setCodiceLuogo(tPrefissoLuoghi.getCodiceLuogo());
                prefissiDto.setDescrizioneLuogo(tPrefissoLuoghi.getDescrizioneLuogo());
                if ("01".equals(inLuogo)) {
                    byte[] byteImage = ConversionUtils.convertBlobToString(tPrefissoLuoghi.getImmagine()).getBytes();

                    prefissiDto.setImmagine(Base64.getEncoder().encodeToString(byteImage));
                }

                result.add(prefissiDto) ;
            });
        }

        return result;
    }

}
