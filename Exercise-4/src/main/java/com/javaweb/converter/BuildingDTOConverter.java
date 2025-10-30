package com.javaweb.converter;

import com.javaweb.config.ModelMapperConfig;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.utils.DistrictCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuildingDTOConverter {
    @Autowired
    private ModelMapper modelMapper;
    public BuildingSearchResponse toBuildingSearchResponse (BuildingEntity item){
        BuildingSearchResponse building =modelMapper.map(item, BuildingSearchResponse.class);
        building.setName(item.getName());
        if (item.getDistrict() != null){
            String districtKey = item.getDistrict();
            String districtValue = DistrictCode.type().get(districtKey);
            building.setAddress("");
        }


        return building;
    }
}
