package it.interno.luoghi.dto;

import it.interno.luoghi.entity.TAreaScomparsa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TAreaScomparsaDto {

    private String idAreaScomparsa;
    private String idMacroAreaScomparsa;
    private String areaScomparsa;
    private LocalDate dataInizioValidita;
    private LocalDate dataFineValidita;

    public TAreaScomparsa convertToEntity(){
        return new TAreaScomparsa(
                idAreaScomparsa,
                idMacroAreaScomparsa,
                areaScomparsa,
                dataInizioValidita,
                dataFineValidita
        );
    }

    public static TAreaScomparsaDto convertToDto(TAreaScomparsa input){
        return new TAreaScomparsaDto(
                input.getIdAreaScomparsa(),
                input.getIdMacroAreaScomparsa(),
                input.getAreaScomparsa(),
                input.getDataInizioValidita(),
                input.getDataFineValidita()
        );
    }

    public static List<TAreaScomparsa> convertToEntityList(List<TAreaScomparsaDto> input){

        List<TAreaScomparsa> result = new ArrayList<>();

        input.forEach(el -> {
            result.add(el.convertToEntity());
        });

        return result;
    }

    public static List<TAreaScomparsaDto> convertToDtoList(List<TAreaScomparsa> input){

        List<TAreaScomparsaDto> result = new ArrayList<>();

        input.forEach(el -> {
            result.add(convertToDto(el));
        });

        return result;
    }
}
