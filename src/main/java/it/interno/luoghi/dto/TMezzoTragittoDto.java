package it.interno.luoghi.dto;

import it.interno.luoghi.entity.TMezzoTragitto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TMezzoTragittoDto {

    private String idMezzoTragitto;
    private String mezzoTragitto;
    private LocalDate dataInizioValidita;
    private LocalDate dataFineValidita;

    public TMezzoTragitto convertToEntity(){
        return new TMezzoTragitto(
                idMezzoTragitto,
                mezzoTragitto,
                dataInizioValidita,
                dataFineValidita
        );
    }

    public static TMezzoTragittoDto convertToDto(TMezzoTragitto input){
        return new TMezzoTragittoDto(
                input.getIdMezzoTragitto(),
                input.getMezzoTragitto(),
                input.getDataInizioValidita(),
                input.getDataFineValidita()
        );
    }

    public static List<TMezzoTragitto> convertToEntityList(List<TMezzoTragittoDto> input){

        List<TMezzoTragitto> result = new ArrayList<>();

        input.forEach(el -> {
            result.add(el.convertToEntity());
        });

        return result;
    }

    public static List<TMezzoTragittoDto> convertToDtoList(List<TMezzoTragitto> input){

        List<TMezzoTragittoDto> result = new ArrayList<>();

        input.forEach(el -> {
            result.add(convertToDto(el));
        });

        return result;
    }
}
