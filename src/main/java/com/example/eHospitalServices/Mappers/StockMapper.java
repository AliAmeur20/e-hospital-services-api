package com.example.eHospitalServices.Mappers;

import com.example.eHospitalServices.DTOs.StockDTO;
import com.example.eHospitalServices.Models.Stock;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DevicePackageMapper.class})
public interface StockMapper {

    @Mapping(target = "consumableMDId" , source = "consumableMD.id" )
    @Mapping(target = "consumableMDName" , source = "consumableMD.name" )
    StockDTO toDTO (Stock stock);

    @InheritInverseConfiguration
    @Mapping(target ="devicePackages", ignore = true)
    Stock toEntity (StockDTO stockDTO);
}
