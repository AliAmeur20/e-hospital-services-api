package com.example.eHospitalServices.Mappers;

import com.example.eHospitalServices.DTOs.OrderDTO;
import com.example.eHospitalServices.Models.Order;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "consumableMDId" , source = "consumableMD.id" )
    @Mapping(target = "consumableMDName" , source = "consumableMD.name" )
    OrderDTO toDTO (Order order);

    @InheritInverseConfiguration
    Order toEntity (OrderDTO orderDTO);
}
