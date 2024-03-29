package it.interno.luoghi.mapper;

import it.interno.luoghi.dto.TTipoLuogoDto;
import it.interno.luoghi.entity.TTipoLuogo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TTipoLuogoMapper {

    TTipoLuogo toTTipoLuogo(TTipoLuogoDto tTipoLuogoDto);

    @InheritInverseConfiguration
    TTipoLuogoDto toTTipoLuogoDto(TTipoLuogo tTipoLuogo);

}
