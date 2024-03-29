package it.interno.luoghi;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import it.interno.luoghi.controller.LuogoController;
import it.interno.luoghi.dto.InputLuoghiFattoDto;
import it.interno.luoghi.dto.LuoghiFattoDto;
import it.interno.luoghi.entity.Luogo;
import it.interno.luoghi.entity.LuogoKey;
import it.interno.luoghi.exception.ValidationFieldException;
import it.interno.luoghi.mapper.LuogoMapper;
import it.interno.luoghi.repository.LuogoFattoRepository;
import it.interno.luoghi.service.LuogoFattoService;
import it.interno.luoghi.service.LuogoService;
import it.interno.luoghi.service.TLuogoDettaglioService;
import it.interno.luoghi.service.impl.LuogoFattoServiceImpl;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { LuoghiFattoTest.LuoghiFattoTestContextConfiguration.class })
@WebAppConfiguration
class LuoghiFattoTest {

	@MockBean
	LuogoFattoRepository luogoFattoRepository;
	@Autowired
	LuogoFattoService luogoFattoService;
	@Autowired
	LuogoController luogoController;
	
	@MockBean
	LuogoService luogoService;

	@MockBean
	private LuogoMapper luogoMapper;

	@MockBean
	private TLuogoDettaglioService luogoDettaglioService;

	@TestConfiguration
	static class LuoghiFattoTestContextConfiguration {
		@Bean
		public LuogoFattoService luogoFattoService() {
			return new LuogoFattoServiceImpl();
		}
		@Bean
		public LuogoController luogoController()
		{
			return new LuogoController();
		}
	}

	private LuogoKey getLuogoEntityKeyTest() {
		LuogoKey luogoKey = new LuogoKey();

		luogoKey.setCodiceLuogo(412057002);
		luogoKey.setTsInserimento(new Timestamp(System.currentTimeMillis()));
		return luogoKey;
	}

	private List<Luogo> getMockListLuogoEntity()  {
		LuogoKey luogoKey = getLuogoEntityKeyTest();
		List<Luogo> listLuogoEntity = new ArrayList<Luogo>();
		Luogo luogo = new Luogo();
		luogo.setCodiceLuogo(luogoKey.getCodiceLuogo());
		luogo.setDataInizioValidita(LocalDate.of(2000, Month.JANUARY, 1));
		luogo.setInLuogo("04");
		luogo.setDescrizioneLuogo("AMATRICE");
		luogo.setDataFineValidita(null);
		luogo.setCodiceRegione("12");
		luogo.setCodiceProvincia("057");
		luogo.setCodiceComune("057002");
		luogo.setCodiceCatastale("A258");
		luogo.setSiglaProvincia("RI");
		luogo.setTsInserimento(luogoKey.getTsInserimento());
		luogo.setTsCancellazione(null);
		luogo.setCodiceLuogoArea(0);
		luogo.setCodiceLuogoZona(0);
		luogo.setCodiceLuogoRiferimento(null);
		luogo.setSiglaLuogoAsf(null);
		listLuogoEntity.add(luogo);

		luogo.setCodiceLuogo(luogoKey.getCodiceLuogo());
		luogo.setDataInizioValidita(LocalDate.of(2000, Month.JANUARY, 1));
		luogo.setInLuogo("01");
		luogo.setDescrizioneLuogo("ANCONA");
		luogo.setDataFineValidita(null);
		luogo.setCodiceRegione("12");
		luogo.setCodiceProvincia("058");
		luogo.setCodiceComune("057002");
		luogo.setCodiceCatastale("A258");
		luogo.setSiglaProvincia("RI");
		luogo.setTsInserimento(luogoKey.getTsInserimento());
		luogo.setTsCancellazione(null);
		luogo.setCodiceLuogoArea(0);
		luogo.setCodiceLuogoZona(0);
		luogo.setCodiceLuogoRiferimento(null);
		luogo.setSiglaLuogoAsf(null);
		listLuogoEntity.add(luogo);

		luogo.setCodiceLuogo(luogoKey.getCodiceLuogo());
		luogo.setDataInizioValidita(LocalDate.of(2000, Month.JANUARY, 1));
		luogo.setInLuogo("03");
		luogo.setDescrizioneLuogo("NAPOLI");
		luogo.setDataFineValidita(null);
		luogo.setCodiceRegione("12");
		luogo.setCodiceProvincia(null);
		luogo.setCodiceComune("057002");
		luogo.setCodiceCatastale("A258");
		luogo.setSiglaProvincia("RI");
		luogo.setTsInserimento(luogoKey.getTsInserimento());
		luogo.setTsCancellazione(null);
		luogo.setCodiceLuogoArea(0);
		luogo.setCodiceLuogoZona(0);
		luogo.setCodiceLuogoRiferimento(null);
		luogo.setSiglaLuogoAsf(null);
		listLuogoEntity.add(luogo);
		return listLuogoEntity;
	}

	private InputLuoghiFattoDto getMockInputValues() throws ValidationFieldException{
		String inLuogo = "04";
		String codProvincia = "057";
		String descrizioneLuogo = "AMATRICE";
		InputLuoghiFattoDto inputValues = new InputLuoghiFattoDto();
		inputValues.setInputLuogo(inLuogo);
		inputValues.setCodiceProvincia(codProvincia);
		inputValues.setDescrizioneLuogo(descrizioneLuogo);
		return inputValues;
	}

	private List<LuoghiFattoDto> getMockListLuoghiFattoDto()throws ValidationFieldException{
		List<LuoghiFattoDto> mockListLuoghiFattoDto = new ArrayList<LuoghiFattoDto>();
		List<Luogo> mockListLuogo = getMockListLuogoEntity();
		return mockListLuoghiFattoDto;
	}

	@BeforeEach
	void testGetLuoghiByAll() throws ValidationFieldException {
		InputLuoghiFattoDto mockListLuoghiFattoDto = getMockInputValues();
		when(luogoFattoService.getLuoghiByAll(mockListLuoghiFattoDto.getInputLuogo(), LocalDate.of(2000, Month.FEBRUARY, 1),
				mockListLuoghiFattoDto.getDescrizioneLuogo(), mockListLuoghiFattoDto.getCodiceProvincia()))
				.thenReturn(getMockListLuoghiFattoDto());

		when(luogoFattoService.getLuoghiByInLuogoAndDescrizioneLuogo("01", LocalDate.of(2000, Month.FEBRUARY, 1), "AN"))
				.thenReturn(getMockListLuoghiFattoDto());

		when(luogoFattoService.getLuoghiByCodiceProvincia("04", "058", LocalDate.of(2000, Month.FEBRUARY, 1)))
				.thenReturn(getMockListLuoghiFattoDto());

		when(luogoFattoService.getLuoghiByInLuogo("01", LocalDate.of(2000, Month.FEBRUARY, 1)))
				.thenReturn(getMockListLuoghiFattoDto());
	}
	
	@Test
	public void test()
	{
		LocalDate dataRif = LocalDate.parse("2022-12-31");
		
		Assertions.assertThrows(ValidationFieldException.class, ()-> luogoFattoService.validazioneLuoghiFatto(null, null, null, dataRif));
	
	}
	
	@Test
	public void testService() throws ValidationFieldException
	{
		LocalDate dataRif = LocalDate.parse("2022-12-31");
		List<LuoghiFattoDto> result = getMockListLuoghiFattoDto();
		
		List<LuoghiFattoDto> actual = luogoFattoService.getLuoghiFatto(getMockInputValues(), dataRif);
		
		assertThat(actual, is(equalTo(result)));
	}
}
