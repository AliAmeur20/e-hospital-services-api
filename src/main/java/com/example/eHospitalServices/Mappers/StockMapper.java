package com.example.eHospitalServices.Mappers;

import com.example.eHospitalServices.DTOs.StockDTO;
import com.example.eHospitalServices.Models.Stock;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StockMapper {

    @Mapping(target = "consumableMDId" , source = "consumableMD.id" )
    StockDTO toDTO (Stock stock);

    @InheritInverseConfiguration
    Stock toEntity (StockDTO stockDTO);
}
