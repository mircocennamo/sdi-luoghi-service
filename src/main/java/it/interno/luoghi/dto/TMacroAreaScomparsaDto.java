package it.interno.luoghi.dto;

import it.interno.luoghi.entity.TMacroAreaScomparsa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TMacroAreaScomparsaDto {

    private String idMacroAreaScomparsa;
    private String macroAreaScomparsa;
    private LocalDate dataInizioValidita;
    private LocalDate dataFineValidita;

    public TMacroAreaScomparsa convertToEntity(){
        return new TMacroAreaScomparsa(
                idMacroAreaScomparsa,
                macroAreaScomparsa,
                dataInizioValidita,
                dataFineValidita
        );
    }

    public static TMacroAreaScomparsaDto convertToDto(TMacroAreaScomparsa input){
        return new TMacroAreaScomparsaDto(
                input.getIdMacroAreaScomparsa(),
                input.getMacroAreaScomparsa(),
                input.getDataInizioValidita(),
                input.getDataFineValidita()
        );
    }

    public static List<TMacroAreaScomparsa> convertToEntityList(List<TMacroAreaScomparsaDto> input){

        List<TMacroAreaScomparsa> result = new ArrayList<>();

        input.forEach(el -> {
            result.add(el.convertToEntity());
        });

        return result;
    }

    public static List<TMacroAreaScomparsaDto> convertToDtoList(List<TMacroAreaScomparsa> input){

        List<TMacroAreaScomparsaDto> result = new ArrayList<>();

        input.forEach(el -> {
            result.add(convertToDto(el));
        });

        return result;
    }
}
