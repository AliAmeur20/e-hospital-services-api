package com.example.eHospitalServices.Mappers;

import com.example.eHospitalServices.DTOs.DevicePackageDTO;
import com.example.eHospitalServices.Models.DevicePackage;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DevicePackageMapper {

    @Mapping(target = "stockId" , source = "stock.id" )
    @Mapping(target = "replishementId" , source = "replishement.id" )
    DevicePackageDTO toDTO (DevicePackage devicePackage);

    @InheritInverseConfiguration
    DevicePackage toEntity (DevicePackageDTO devicePackageDTO);
}
