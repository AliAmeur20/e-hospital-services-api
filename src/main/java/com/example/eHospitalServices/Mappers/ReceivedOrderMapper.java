package com.example.eHospitalServices.Mappers;

import com.example.eHospitalServices.DTOs.ReceivedOrderDTO;
import com.example.eHospitalServices.Models.ReceivedOrder;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReceivedOrderMapper {

    @Mapping(target = "consumableMDId" , source = "consumableMD.id" )
    @Mapping(target = "consumableMDName" , source = "consumableMD.name" )
    ReceivedOrderDTO toDto(ReceivedOrder receivedOrder);

    @InheritInverseConfiguration
    ReceivedOrder toEntity(ReceivedOrderDTO receivedOrderDTO);
}
