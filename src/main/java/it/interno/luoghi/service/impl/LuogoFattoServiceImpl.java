package it.interno.luoghi.service.impl;

import static it.interno.luoghi.repository.specifications.LuogoSpecifications.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import it.interno.luoghi.dto.InputLuoghiFattoDto;
import it.interno.luoghi.dto.LuoghiFattoDto;
import it.interno.luoghi.dto.ResponseDto;
import it.interno.luoghi.entity.Luogo;
import it.interno.luoghi.exception.ValidationFieldException;
import it.interno.luoghi.repository.LuogoFattoRepository;
import it.interno.luoghi.service.LuogoFattoService;

@Service
public class LuogoFattoServiceImpl implements LuogoFattoService {

	@Autowired
	private LuogoFattoRepository luogoFattoRepository;

	@Override
	public List<LuoghiFattoDto> getLuoghiFatto(InputLuoghiFattoDto inputLuogoFatto, LocalDate dataRif)
			throws ValidationFieldException {
		List<LuoghiFattoDto> listLuogoFattoDto = null;

		switch (inputLuogoFatto.getInputLuogo()) {
		case "01":
		case "03":
			if (StringUtils.isEmpty(inputLuogoFatto.getDescrizioneLuogo())
					&& StringUtils.isEmpty(inputLuogoFatto.getCodiceProvincia())) {
				listLuogoFattoDto = getLuoghiByInLuogo(inputLuogoFatto.getInputLuogo(), dataRif);
			} else if (StringUtils.isEmpty(inputLuogoFatto.getCodiceProvincia())) {
				listLuogoFattoDto = getLuoghiByInLuogoAndDescrizioneLuogo(inputLuogoFatto.getInputLuogo(), dataRif,
						inputLuogoFatto.getDescrizioneLuogo());
			} else {
				throw new ValidationFieldException("errore.codice.provincia", "400");
			}
			break;
		case "04":
			if (StringUtils.isEmpty(inputLuogoFatto.getDescrizioneLuogo())
					&& StringUtils.isEmpty(inputLuogoFatto.getCodiceProvincia())) {
				listLuogoFattoDto = getLuoghiByInLuogo(inputLuogoFatto.getInputLuogo(), dataRif);
			} else if (StringUtils.isEmpty(inputLuogoFatto.getCodiceProvincia())) {
				listLuogoFattoDto = getLuoghiByInLuogoAndDescrizioneLuogo(inputLuogoFatto.getInputLuogo(), dataRif,
						inputLuogoFatto.getDescrizioneLuogo());
			} else if (StringUtils.isEmpty(inputLuogoFatto.getDescrizioneLuogo())) {
				listLuogoFattoDto = getLuoghiByCodiceProvincia(inputLuogoFatto.getInputLuogo(),
						inputLuogoFatto.getCodiceProvincia(), dataRif);
			} else {
				listLuogoFattoDto = getLuoghiByAll(inputLuogoFatto.getInputLuogo(), dataRif,
						inputLuogoFatto.getDescrizioneLuogo(), inputLuogoFatto.getCodiceProvincia());
			}
			break;
		default:
			throw new ValidationFieldException("errore.valore.nonvalido", "400");
		}
		return listLuogoFattoDto;
	}

	@Override
	public List<LuoghiFattoDto> getLuoghiByAll(String inputLuogo, LocalDate dataRif, String descrizioneLuogo,
			String codiceProvincia) throws ValidationFieldException {
		List<Luogo> listLuogoEntity = luogoFattoRepository.findAll(tsCancellazioneIsNull().and(inLuoEquals(inputLuogo))
				.and(desLuoLike(descrizioneLuogo)).and(codPrvEquals(codiceProvincia)).and(dataLessThanDIniVal(dataRif))
				.and(dataRifGreaterThanDFinVal(dataRif)));
		return this.setListaLuogoDto(listLuogoEntity);
	}

	@Override
	public List<LuoghiFattoDto> getLuoghiByInLuogoAndDescrizioneLuogo(String inputLuogo, LocalDate dataRif,
			String descrizioneLuogo) throws ValidationFieldException {
		List<Luogo> listLuogoEntity = luogoFattoRepository
				.findAll(tsCancellazioneIsNull().and(inLuoEquals(inputLuogo)).and(desLuoLike(descrizioneLuogo))
						.and(dataLessThanDIniVal(dataRif)).and(dataRifGreaterThanDFinVal(dataRif)));
		return this.setListaLuogoDto(listLuogoEntity);
	}

	@Override
	public List<LuoghiFattoDto> getLuoghiByCodiceProvincia(String inputLuogo, String codiceProvincia, LocalDate dataRif)
			throws ValidationFieldException {
		List<Luogo> listLuogoEntity = luogoFattoRepository
				.findAll(tsCancellazioneIsNull().and(inLuoEquals(inputLuogo)).and(codPrvEquals(codiceProvincia))
						.and(dataLessThanDIniVal(dataRif)).and(dataRifGreaterThanDFinVal(dataRif)));
		return this.setListaLuogoDto(listLuogoEntity);
	}

	@Override
	public List<LuoghiFattoDto> getLuoghiByInLuogo(String inputLuogo, LocalDate dataRif)
			throws ValidationFieldException {
		List<Luogo> listLuogoEntity = luogoFattoRepository.findAll(tsCancellazioneIsNull().and(inLuoEquals(inputLuogo))
				.and(dataLessThanDIniVal(dataRif)).and(dataRifGreaterThanDFinVal(dataRif)));
		return this.setListaLuogoDto(listLuogoEntity);
	}

	@Override
	public List<LuoghiFattoDto> setListaLuogoDto(List<Luogo> luoghi) throws ValidationFieldException {
		List<LuoghiFattoDto> luogoDto = new ArrayList<>();
		luoghi.stream().forEach(lu -> {
			LuoghiFattoDto luoghiFattoDto = new LuoghiFattoDto();
			BeanUtils.copyProperties(lu, luoghiFattoDto);
			if (lu.getSiglaProvincia() != null && StringUtils.isEmpty(lu.getSiglaProvincia().trim())) {
				luoghiFattoDto.setSiglaProvincia(lu.getSiglaLuogoAsf());
			}
			luogoDto.add(luoghiFattoDto);
		});
		return luogoDto;
	}

	@Override
	public ResponseDto validazioneLuoghiFatto(Integer codiceLuogo, String descrizioneLuogo, String siglaProvincia,
			LocalDate dataRif) throws ValidationFieldException {
		List<Luogo> listLuoghi = luogoFattoRepository.findAll(tsCancellazioneIsNull().and(cLuoEquals(codiceLuogo))
				.and((sglPrvEquals(siglaProvincia)).or(siglaLuogoAsfEquals(siglaProvincia)))
				.and(desLuoEquals(descrizioneLuogo)).and(dataLessThanDIniVal(dataRif))
				.and(dataRifGreaterThanDFinVal(dataRif)));
		if (listLuoghi.isEmpty()) {
			throw new ValidationFieldException("luoghiFatto.errore.luoghiNotFound", null);
		}
		return ResponseDto.builder().code(HttpStatus.OK.value()).build();
	}

}
