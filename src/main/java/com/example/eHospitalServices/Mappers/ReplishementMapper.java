package com.example.eHospitalServices.Mappers;

import com.example.eHospitalServices.DTOs.ReplishementDTO;
import com.example.eHospitalServices.Models.Replishement;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReplishementMapper {

    @Mapping(target = "consumableMDId" , source = "consumableMD.id" )
    @Mapping(target = "consumableMDName" , source = "consumableMD.name" )
    ReplishementDTO toDTO (Replishement replishement);

    @InheritInverseConfiguration
    Replishement toEntity (ReplishementDTO replishementDTO);
}
