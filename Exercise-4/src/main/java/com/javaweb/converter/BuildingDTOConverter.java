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
    public BuildingSearchResponse toBuildingSearchResponse (BuildingEntity item) {
        BuildingSearchResponse building = modelMapper.map(item,BuildingSearchResponse.class);
        building.setName(item.getName());
        if (item.getDistrict() != null && item.getDistrict() != ""){
            String districtkey = item.getDistrict();
            String districtvalue = DistrictCode.type().get(districtkey);
            building.setAddress(item.getStreet() + " " + item.getWard() + " " + districtvalue);
        }
        else {
            building.setAddress(item.getStreet() + " " + item.getWard());
        }
        String result = item.getItems().stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
        building.setRentArea(result);
        return building;
    }
    public BuildingEntity toBuildingEntity (BuildingDTO item){
        BuildingEntity buildingEntity = modelMapper.map(item, BuildingEntity.class);
        String typeCodeJoin =  item.getTypeCode().stream().collect(Collectors.joining(","));
        buildingEntity.setType(typeCodeJoin);
        String[] rentAreas = item.getRentArea().split(",");
        List<RentAreaEntity> rentAreaEntities = new ArrayList<>();
        for (String rentArea : rentAreas){
            RentAreaEntity rentAreaEntity = new RentAreaEntity();
            rentAreaEntity.setValue(rentArea);
            rentAreaEntities.add(rentAreaEntity);
        }
        buildingEntity.setItems(rentAreaEntities);
        return buildingEntity;
    }
    public BuildingEntity entityToEntity (BuildingEntity oldBuilding, BuildingEntity newBuildingEntity){
        oldBuilding = modelMapper.map(newBuildingEntity, BuildingEntity.class);
        return oldBuilding;
    }
    public BuildingDTO toBuildingDTO_forUpdateBuilding (BuildingEntity buildingEntity){
        BuildingDTO building = modelMapper.map(buildingEntity, BuildingDTO.class);
        List<RentAreaEntity> item = buildingEntity.getItems();
        String rentAreas = item.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
        building.setRentArea(rentAreas);
        String[] typeCode = buildingEntity.getType().split(",");
        List<String> typeCodes = new ArrayList<>();
        for (String type : typeCode){
            typeCodes.add(type);
        }
        building.setTypeCode(typeCodes);
        return building;
    }
}
