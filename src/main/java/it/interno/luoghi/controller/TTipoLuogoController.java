package it.interno.luoghi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.interno.luoghi.dto.ResponseDto;
import it.interno.luoghi.dto.TTipoLuogoDto;
import it.interno.luoghi.service.TTipoLuogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/tipo")
public class TTipoLuogoController {

    @Autowired
    private TTipoLuogoService tTipoLuogoService;

    @Operation(description = "API per recuperare luoghi filtrando per descrizione luogo, tipo luogo e data ")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema") })
    @GetMapping
    public ResponseEntity<ResponseDto<List<TTipoLuogoDto>>> getTTipoLuogo(@RequestParam String tipoDenuncia) {

        List<TTipoLuogoDto> luogoDtos = tTipoLuogoService.findTipoLuogoByTipoDenuncia(tipoDenuncia);
        return ResponseEntity.ok(ResponseDto.<List<TTipoLuogoDto>>builder().code(HttpStatus.OK.value()).body(luogoDtos).build());
    }

    @Operation(description = "API per recuperare il tipo luogo filtrando per id")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema") })
    @GetMapping(path = "/{idTipoLuogo}")
    public ResponseEntity<ResponseDto<TTipoLuogoDto>> getTTipoLuogoById(@PathVariable String idTipoLuogo) {
        TTipoLuogoDto tipoLuogoDto = tTipoLuogoService.findTipoLuogoById(idTipoLuogo);
        return ResponseEntity.ok(ResponseDto.<TTipoLuogoDto>builder().code(HttpStatus.OK.value()).body(tipoLuogoDto).build());
    }

}
