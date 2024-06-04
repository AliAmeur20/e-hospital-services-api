package com.example.eHospitalServices.Mappers;
import com.example.eHospitalServices.DTOs.ConsumableMDDTO;
import com.example.eHospitalServices.Models.ConsumableMD;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConsumableMDMapper {

    @Mapping(target = "image", ignore = true)
    ConsumableMDDTO toDTO (ConsumableMD consumableMD);

    ConsumableMD toEntity (ConsumableMDDTO consumableMDDTO);
}
