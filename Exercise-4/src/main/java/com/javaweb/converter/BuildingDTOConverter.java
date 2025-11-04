package com.javaweb.converter;

import com.javaweb.config.ModelMapperConfig;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.utils.DistrictCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            building.setAddress(item.getStreet() + "," + item.getWard() + "," +districtValue);
        }
        else {
            building.setAddress(item.getStreet() + "," + item.getWard());
        }
        List<RentAreaEntity> rentAreas = item.getItems();
        String areaResult = rentAreas.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
        building.setRentArea(areaResult);
        return building;
    }
    public BuildingEntity toBuildingEntity (BuildingDTO item){
        BuildingEntity building = modelMapper.map(item, BuildingEntity.class);
        String typeCodeStringJoin = item.getTypeCode().stream().collect(Collectors.joining(","));
        building.setType(typeCodeStringJoin);
        String[] rentAreas = item.getRentArea().split(",");
        List<RentAreaEntity> rentAreaEntities = new ArrayList<>();
        for (String area : rentAreas){
            RentAreaEntity rentAreaEntity = new RentAreaEntity();
            rentAreaEntity.setValue(area);
            rentAreaEntities.add(rentAreaEntity);
        }
        building.setItems(rentAreaEntities);
        return building;
    }
    public BuildingEntity entityToEntity (BuildingEntity oldBuilding, BuildingEntity newBuilding){
        oldBuilding = modelMapper.map(newBuilding, BuildingEntity.class);
        return oldBuilding;
    }
    public BuildingDTO toBuidlingDTO_forUpdateBuilding(BuildingEntity item){
        BuildingDTO building = modelMapper.map(item, BuildingDTO.class);
        List<RentAreaEntity> rentAreas = item.getItems();
        String resultArea = rentAreas.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
        building.setRentArea(resultArea);
        String[] typeCodes = item.getType().split(",");
        List<String> typeCode = new ArrayList<>();
        for (String type : typeCodes){
            typeCode.add(type);
        }
        building.setTypeCode(typeCode);
        return building;
    }
}
