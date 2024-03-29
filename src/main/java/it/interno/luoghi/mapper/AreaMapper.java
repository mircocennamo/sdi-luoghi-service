package it.interno.luoghi.mapper;

import it.interno.luoghi.dto.TAreaScomparsaDto;
import it.interno.luoghi.entity.TAreaScomparsa;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AreaMapper {
    TAreaScomparsa toEntity(TAreaScomparsaDto areaScomparsaDto);

    @InheritInverseConfiguration
    TAreaScomparsaDto toDto(TAreaScomparsa areaScomparsa);
}
