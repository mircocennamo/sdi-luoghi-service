package it.interno.luoghi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.interno.luoghi.dto.*;
import it.interno.luoghi.entity.TAreaScomparsa;
import it.interno.luoghi.entity.TMacroAreaScomparsa;
import it.interno.luoghi.exception.NoDataException;
import it.interno.luoghi.exception.ValidationFieldException;
import it.interno.luoghi.service.*;
import it.interno.luoghi.util.StringUpperCaseEditor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@RestController
@Validated
public class LuogoController {

	@Autowired
	private LuogoService luogoService;

	@Autowired
	private LuogoFattoService luogoFattoService;

	@Autowired
	private TLuogoDettaglioService luogoDettaglioService;

	@Autowired
	private TMezzoTragittoService tMezzoTragittoService;

	@Autowired
	private TAreaScomparsaService tAreaScomparsaService;

	@Autowired
	private TMacroAreaScomparsaService tMacroAreaScomparsaService;

	@Autowired
	private TPrefissoLuoghiService tPrefissoLuoghiService;


	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {

		StringUpperCaseEditor stringUpperCaseEditor = new StringUpperCaseEditor();
		webDataBinder.registerCustomEditor(String.class, stringUpperCaseEditor);

	}

	@Operation(description = "API per recuperare luoghi filtrando per descrizione luogo, tipo luogo e data ")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
			@ApiResponse(responseCode = "404", description = "Url non definita"),
			@ApiResponse(responseCode = "500", description = "Errore del sistema") })
	@GetMapping(path = "/{inLuo}")
	public ResponseEntity<ResponseDto<List<LuogoDto>>> getLuoghi(
			@PathVariable @NotBlank(message = "Il campo 'Indicatore Luogo' {errore.campo.obbligatorio}") String inLuo,
			@RequestParam @NotBlank(message = "Il campo 'Descrizione Luogo' {errore.campo.obbligatorio}") String desLuo,
			@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") LocalDate dataRif) {

		List<LuogoDto> luoghi = luogoService.getLuoghiByDesLuoAndInLuoAndDataRif(desLuo, inLuo, dataRif);
		ResponseDto<List<LuogoDto>> responseDto = ResponseDto.<List<LuogoDto>>builder().code(HttpStatus.OK.value())
				.body(luoghi).build();

		return ResponseEntity.ok(responseDto);
	}

	@Operation(description = "API per recuperare luoghi relativi al rilascio dei documenti, filtrando per descrizione luogo, tipo luogo e data ")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
			@ApiResponse(responseCode = "404", description = "Url non definita"),
			@ApiResponse(responseCode = "500", description = "Errore del sistema") })
	@GetMapping(path = "/{inLuo}/documento")
	public ResponseEntity<ResponseDto<List<LuogoDto>>> getLuoghiRilascioDocumento(
			@PathVariable @NotBlank(message = "Il campo 'Indicatore Luogo' {errore.campo.obbligatorio}") String inLuo,
			@RequestParam @NotBlank(message = "Il campo 'Descrizione Luogo' {errore.campo.obbligatorio}") String desLuo,
			@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") LocalDate dataRif) {

		List<LuogoDto> luoghi = luogoService.getLuoghiRilascioDocumentiByDesLuoAndInLuoAndDataRif(desLuo, inLuo,
				dataRif);
		ResponseDto<List<LuogoDto>> responseDto = ResponseDto.<List<LuogoDto>>builder().code(HttpStatus.OK.value())
				.body(luoghi).build();

		return ResponseEntity.ok(responseDto);
	}

	@Operation(description = "API per recuperare luoghi alternativi a SDI")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
			@ApiResponse(responseCode = "404", description = "Url non definita"),
			@ApiResponse(responseCode = "500", description = "Errore del sistema") })
	@GetMapping(path = "/alternativi")
	public ResponseEntity<ResponseDto<LuogoDto>> getLuoghiAlternativiSDI(
			@RequestParam @NotBlank(message = "Il campo 'Descrizione Luogo' {errore.campo.obbligatorio}") String desLuo,
			@RequestParam @NotBlank(message = "Il campo 'Sigla Provincia' {errore.campo.obbligatorio}") String sglPrv,
			@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") LocalDate dataRif) {

		LuogoDto luoghi = luogoService.getLuogoAlternativoSDIByDesLuoAndSglPrvAndData(desLuo, sglPrv, dataRif);
		ResponseDto<LuogoDto> responseDto = ResponseDto.<LuogoDto>builder().code(HttpStatus.OK.value()).body(luoghi)
				.build();

		return ResponseEntity.ok(responseDto);
	}

	@Operation(description = "API per recuperare luoghi originali di SDI")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
			@ApiResponse(responseCode = "404", description = "Url non definita"),
			@ApiResponse(responseCode = "500", description = "Errore del sistema") })
	@GetMapping(path = "/originali")
	public ResponseEntity<ResponseDto<LuogoDto>> getLuoghiOriginaliSDI(
			@RequestParam @NotBlank(message = "Il campo 'Descrizione Luogo' {errore.campo.obbligatorio}") String desLuo,
			@RequestParam @NotBlank(message = "Il campo 'Sigla Provincia' {errore.campo.obbligatorio}") String sglPrv,
			@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") LocalDate dataRif) {

		LuogoDto luoghi = luogoService.getLuogoOriginaleSDIByDesLuoAndSglPrvAndData(desLuo, sglPrv, dataRif);
		ResponseDto<LuogoDto> responseDto = ResponseDto.<LuogoDto>builder().code(HttpStatus.OK.value()).body(luoghi)
				.build();

		return ResponseEntity.ok(responseDto);
	}

	@Operation(description = "API per verificare se il nome del soggetto è obbligatorio")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
			@ApiResponse(responseCode = "404", description = "Url non definita"),
			@ApiResponse(responseCode = "500", description = "Errore del sistema") })
	@GetMapping(path = "/{codiceLuogo}/personafisica/nome-obbligatorio")
	public ResponseEntity<ResponseDto<Boolean>> verificaNomeObbligatorio(
			@PathVariable @NotNull(message = "Il campo 'Codice Luogo' {errore.campo.obbligatorio}") Integer codiceLuogo,
			@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") LocalDate dataRif) {

		Boolean result = luogoDettaglioService.verificaNomeObbligatorio(codiceLuogo, dataRif);
		ResponseDto<Boolean> responseDto = ResponseDto.<Boolean>builder().code(HttpStatus.OK.value()).body(result)
				.build();

		return ResponseEntity.ok(responseDto);
	}

	@Operation(description = "API per validare un luogo")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
			@ApiResponse(responseCode = "404", description = "Url non definita"),
			@ApiResponse(responseCode = "500", description = "Errore del sistema") })
	@PostMapping(path = "/validazione")
	public ResponseEntity validazioneLuogo(@RequestBody @Valid LuogoFieldsValidationDto dto)
			throws ValidationFieldException {

		ResponseDto responseDto = luogoService.validazioneLuogo(dto.getTipoLuogo(), dto.getCodiceLuogo(),
				dto.getDescrizioneLuogo(), dto.getSiglaProvincia(), dto.getDataRif());

		return ResponseEntity.ok(responseDto);
	}

	@Operation(description = "API per recuperare i dati del luogo di un fatto")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
			@ApiResponse(responseCode = "404", description = "Url non definita"),
			@ApiResponse(responseCode = "500", description = "Errore del sistema") })
	@PostMapping("/luoghiFatto")

	public ResponseEntity<ResponseDto<List<LuoghiFattoDto>>> getLuoghiFatto(@RequestBody @Valid InputLuoghiFattoDto inputLuogoFatto,
						@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") LocalDate dataRif)
			throws ValidationFieldException {

		List<LuoghiFattoDto> listLuogo = null;
		ResponseDto<List<LuoghiFattoDto>> responseDto = null;
		if(inputLuogoFatto.getDescrizioneLuogo() != null) {
			inputLuogoFatto.setDescrizioneLuogo(inputLuogoFatto.getDescrizioneLuogo().toUpperCase());
		}
		if (!StringUtils.isEmpty(inputLuogoFatto.getDescrizioneLuogo())
				&& inputLuogoFatto.getDescrizioneLuogo().length() < 2) {
			responseDto = ResponseDto.<List<LuoghiFattoDto>>builder().code(HttpStatus.BAD_REQUEST.value()).error("Inserire almeno due caratteri").build();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		} else {
			listLuogo = luogoFattoService.getLuoghiFatto(inputLuogoFatto, dataRif);
			responseDto = ResponseDto.<List<LuoghiFattoDto>>builder().code(HttpStatus.OK.value()).body(listLuogo).build();
		}
		return ResponseEntity.ok(responseDto);
	}

	@Operation(description = "API per validare i luoghiFatto")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
			@ApiResponse(responseCode = "404", description = "Url non definita"),
			@ApiResponse(responseCode = "500", description = "Errore del sistema") })

	@PostMapping(path = "/validazioneLuoghiFatto")
	public ResponseEntity validazioneLuoghiFatto(@RequestBody @Valid LuoghiFattoDto dto,
			@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") LocalDate dataRif) throws ValidationFieldException {

		ResponseDto responseDto = luogoFattoService.validazioneLuoghiFatto(dto.getCodiceLuogo(),
				dto.getDescrizioneLuogo(), dto.getSiglaProvincia(), dataRif);

		return ResponseEntity.ok(responseDto);
	}

	@Operation(description = "API per recuperare il codice nazione per INTERPOL")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
							@ApiResponse(responseCode = "404", description = "Url non definita"),
							@ApiResponse(responseCode = "500", description = "Errore del sistema") })
	@GetMapping(path = "/{codiceLuogo}/interpol")
	public ResponseEntity<ResponseDto<String>> getNazioneInterpol(@PathVariable Integer codiceLuogo) throws NoDataException {

		String codiceNazioneInterpol = luogoService.getCodiceNazioneInterpol(codiceLuogo);
		ResponseDto<String> responseDto = ResponseDto.<String>builder().code(HttpStatus.OK.value()).body(codiceNazioneInterpol).build();

		return ResponseEntity.ok(responseDto);
	}


	@Operation(description = "API per recuperare il codice nazione per SCHENGEN")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
			@ApiResponse(responseCode = "404", description = "Url non definita"),
			@ApiResponse(responseCode = "500", description = "Errore del sistema") })
	@GetMapping(path = "/{codiceLuogo}/schengen")
	public ResponseEntity<ResponseDto<String>> getNazioneSchengen(@PathVariable String codiceLuogo) throws NoDataException {

		String codiceNazioneInterpol = luogoService.getCodiceNazioneSchengen(codiceLuogo);
		ResponseDto<String> responseDto = ResponseDto.<String>builder().code(HttpStatus.OK.value()).body(codiceNazioneInterpol).build();

		return ResponseEntity.ok(responseDto);
	}

	@Operation(description = "API per recuperare luoghi come Comuni o Nazioni diversi da Italia, filtrando per descrizione luogo, ata ")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
			@ApiResponse(responseCode = "404", description = "Url non definita"),
			@ApiResponse(responseCode = "500", description = "Errore del sistema") })
	@GetMapping(path = "/comuni-nazioni-no-ita")
	public ResponseEntity<ResponseDto<List<LuogoDto>>> getLuoghiComuniNazioniDiversiDaItaliaByDesLuoAndDataRif(
			@RequestParam @NotBlank(message = "Il campo 'Descrizione Luogo' {errore.campo.obbligatorio}") String desLuo,
			@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") LocalDate dataRif) {

		List<LuogoDto> luoghi = luogoService.getLuoghiComuniNazioniDiversiDaItaliaByDesLuoAndDataRif(desLuo, dataRif);
		ResponseDto<List<LuogoDto>> responseDto = ResponseDto.<List<LuogoDto>>builder().code(HttpStatus.OK.value())
				.body(luoghi).build();

		return ResponseEntity.ok(responseDto);
	}

	@Operation(description = "API per recuperare il luogo tramite id e data riferimento ")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
			@ApiResponse(responseCode = "404", description = "Url non definita"),
			@ApiResponse(responseCode = "500", description = "Errore del sistema") })
	@GetMapping(path = "/{codiceLuogo}/")
	public ResponseEntity<ResponseDto<LuogoDto>> getLuogoByCodiceLuogo(@PathVariable Integer codiceLuogo,
																  @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") LocalDate dataRif) throws NoDataException {

		LuogoDto luogoDto = luogoService.getCodiceCatastaleByCodiceLuogoAndDataRif(codiceLuogo, dataRif);
		return ResponseEntity.ok(ResponseDto.<LuogoDto>builder().code(HttpStatus.OK.value()).body(luogoDto).build());
	}

	@Operation(description = "API validare l'anno di immatricolazione del veicolo rispetto alla nazionalità")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
			@ApiResponse(responseCode = "404", description = "Url non definita"),
			@ApiResponse(responseCode = "500", description = "Errore del sistema") })
	@PostMapping(path = "/validazioneVeicoloNazionalita")
	public boolean checkVeicleNationality(@RequestBody @Valid ValidationVeicleNationalityDto input){
		return luogoService.checkVeicleNationality(input.getAnnoImmatricolazione(), input.getNazionalita());
	}




	@Operation(description = "API per prendere i mezzi del tragitto")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
			@ApiResponse(responseCode = "404", description = "Url non definita"),
			@ApiResponse(responseCode = "500", description = "Errore del sistema") })
	@GetMapping(path = "/mezzoTragitto")
	public ResponseEntity<ResponseDto<List<TMezzoTragittoDto>>> getAllMezzoTragittoByData(
			@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") LocalDate dataRif) {

		List<TMezzoTragittoDto> result = tMezzoTragittoService.getAllByData(dataRif);
		return ResponseEntity.ok(ResponseDto.<List<TMezzoTragittoDto>>builder().code(HttpStatus.OK.value()).body(result).build());
	}

	@Operation(description = "API per prendere le aree della scomparsa")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
			@ApiResponse(responseCode = "404", description = "Url non definita"),
			@ApiResponse(responseCode = "500", description = "Errore del sistema") })
	@PostMapping(path = "/areaScomparsa")
	public ResponseEntity<ResponseDto<HashMap<String,List<TAreaScomparsaDto>>>> getAllAreaScomparsa(
			@RequestBody (required = false) String[] arrayDescrMacroArea,
			@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") LocalDate dataRif) {


		HashMap <String,List<TAreaScomparsaDto>> result = tAreaScomparsaService.getByDescrizioneMacroAreaAndData(arrayDescrMacroArea, dataRif);
		return ResponseEntity.ok(ResponseDto.<HashMap <String,List<TAreaScomparsaDto>>>builder().code(HttpStatus.OK.value()).body(result).build());
	}

	@Operation(description = "API per prendere l'area dato l'id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
			@ApiResponse(responseCode = "404", description = "Url non definita"),
			@ApiResponse(responseCode = "500", description = "Errore del sistema") })
	@GetMapping(path = "/area/{idArea}")
	public ResponseEntity<ResponseDto<TAreaScomparsaDto>> getArea(
			@PathVariable @NotNull String idArea,
			@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") LocalDate dataRif) {

		TAreaScomparsaDto result = TAreaScomparsaDto.convertToDto(tAreaScomparsaService.getById(idArea, dataRif));
		return ResponseEntity.ok(ResponseDto.<TAreaScomparsaDto>builder().code(HttpStatus.OK.value()).body(result).build());
	}

	@Operation(description = "API per prendere le macroaree della scomparsa")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
			@ApiResponse(responseCode = "404", description = "Url non definita"),
			@ApiResponse(responseCode = "500", description = "Errore del sistema") })
	@GetMapping(path = "/macroAreaScomparsa")
	public ResponseEntity<ResponseDto<List<TMacroAreaScomparsaDto>>> getAllMacroAreaScomparsaByData(
			@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") LocalDate dataRif) {

		List<TMacroAreaScomparsaDto> result = tMacroAreaScomparsaService.getAllByData(dataRif);
		return ResponseEntity.ok(ResponseDto.<List<TMacroAreaScomparsaDto>>builder().code(HttpStatus.OK.value()).body(result).build());
	}

	@Operation(description = "API per prendere la macro area dato l'id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
			@ApiResponse(responseCode = "404", description = "Url non definita"),
			@ApiResponse(responseCode = "500", description = "Errore del sistema") })
	@GetMapping(path = "/macro-area/{idMacroarea}")
	public ResponseEntity<ResponseDto<TMacroAreaScomparsaDto>> getMacroArea(
			@PathVariable @NotNull String idMacroarea,
			@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") LocalDate dataRif) {

		TMacroAreaScomparsaDto result = TMacroAreaScomparsaDto.convertToDto(tMacroAreaScomparsaService.getById(idMacroarea, dataRif));
		return ResponseEntity.ok(ResponseDto.<TMacroAreaScomparsaDto>builder().code(HttpStatus.OK.value()).body(result).build());
	}

	@Operation(description = "API per prendere le informazioni dei prefissi telefonici")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
			@ApiResponse(responseCode = "404", description = "Url non definita"),
			@ApiResponse(responseCode = "500", description = "Errore del sistema") })
	@GetMapping(path = "/infoPrefissi")
	public ResponseEntity<ResponseDto<List<InfoPrefissiDto>>> getInfoPrefissi(
			@RequestParam @NotNull String descrizione,
			@RequestParam @NotNull String inLuogo,
			@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") LocalDate dataRif) {

		List<InfoPrefissiDto> result = tPrefissoLuoghiService.getInfoPrefissi(descrizione, inLuogo, dataRif);
		return ResponseEntity.ok(ResponseDto.<List<InfoPrefissiDto>>builder().code(HttpStatus.OK.value()).body(result).build());
	}

	@Operation(description = "API per prendere le informazioni dei prefissi telefonici")
	@GetMapping(path = "/aree-scomparsa/{idMacroArea}")
	public ResponseEntity<ResponseDto<List<TAreaScomparsaDto>>> getAllAreeByIdMacroArea(
			@PathVariable @NotBlank String idMacroArea,
			@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") LocalDate dataRif) {

		List<TAreaScomparsaDto> result = tAreaScomparsaService.getAllByIdMacroAreaAndData(idMacroArea, dataRif);
		return ResponseEntity.ok(ResponseDto.<List<TAreaScomparsaDto>>builder().code(HttpStatus.OK.value()).body(result).build());
	}

	@Operation(description = "API per recuperare luoghi filtrando per tipo luogo, descrizione luogo e data ")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
			@ApiResponse(responseCode = "404", description = "Url non definita"),
			@ApiResponse(responseCode = "500", description = "Errore del sistema") })
	@GetMapping(path = "/luoghi-noita/{inLuo}")
	public ResponseEntity<ResponseDto<List<LuogoDto>>> getComuniProvinceRegioniNazioneNoIta(
			@PathVariable @NotBlank(message = "Il campo 'Indicatore Luogo' {errore.campo.obbligatorio}") String inLuo,
			@RequestParam @NotBlank(message = "Il campo 'Descrizione Luogo' {errore.campo.obbligatorio}") String desLuo,
			@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") @NotNull(message = "Il campo 'Data Riferimento' {errore.campo.obbligatorio}") LocalDate dataRif) {

		List<LuogoDto> luoghi = luogoService.getLuoghiComuniProvinceRegioniNazioniDiversiDaItaliaByInLuoAndDesLuoAndDataRif(inLuo, desLuo, dataRif);
		ResponseDto<List<LuogoDto>> responseDto = ResponseDto.<List<LuogoDto>>builder().code(HttpStatus.OK.value()).body(luoghi).build();

		return ResponseEntity.ok(responseDto);
	}

}
