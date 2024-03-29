package it.interno.luoghi.service;

import java.time.LocalDate;
import java.util.List;

import it.interno.luoghi.dto.InputLuoghiFattoDto;
import it.interno.luoghi.dto.LuoghiFattoDto;
import it.interno.luoghi.dto.ResponseDto;
import it.interno.luoghi.entity.Luogo;
import it.interno.luoghi.exception.ValidationFieldException;

public interface LuogoFattoService {

	List<LuoghiFattoDto> getLuoghiFatto(InputLuoghiFattoDto inputLuogoFatto, LocalDate dataRif) throws ValidationFieldException;

	ResponseDto validazioneLuoghiFatto(Integer codiceLuogo, String descrizioneLuogo,String siglaProvincia,
			LocalDate dataRif) throws ValidationFieldException;

	List<LuoghiFattoDto> setListaLuogoDto(List<Luogo> luoghi) throws ValidationFieldException;

	List<LuoghiFattoDto> getLuoghiByInLuogo(String inputLuogo, LocalDate dataRif) throws ValidationFieldException;

	List<LuoghiFattoDto> getLuoghiByInLuogoAndDescrizioneLuogo(String inputLuogo, LocalDate dataRif,
			String descrizioneLuogo) throws ValidationFieldException;

	List<LuoghiFattoDto> getLuoghiByCodiceProvincia(String inputLuogo, String codiceProvincia, LocalDate dataRif) throws ValidationFieldException;

	List<LuoghiFattoDto> getLuoghiByAll(String inputLuogo, LocalDate dataRif, String descrizioneLuogo,
			String codiceProvincia) throws ValidationFieldException;

}
