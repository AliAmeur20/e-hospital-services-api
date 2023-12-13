package com.example.eHospitalServices.Mappers;

import com.example.eHospitalServices.DTOs.ConsumptionDTO;
import com.example.eHospitalServices.Models.Consumption;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConsumptionMapper {

    @Mapping(target = "consumableMDId" , source = "consumableMD.id" )
    @Mapping(target = "consumableMDName" , source = "consumableMD.name" )
    ConsumptionDTO toDTO (Consumption consumption);

    @InheritInverseConfiguration
    Consumption toEntity (ConsumptionDTO consumptionDto);
}
