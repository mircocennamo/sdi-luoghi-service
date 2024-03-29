package it.interno.luoghi.mapper;

import it.interno.luoghi.dto.LuogoDto;
import it.interno.luoghi.entity.Luogo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface LuogoMapper {


    Luogo luogoDtoToLuogo(LuogoDto luogoDto);

    @InheritInverseConfiguration
    LuogoDto luogoToLuogoDto(Luogo luogo);
}
