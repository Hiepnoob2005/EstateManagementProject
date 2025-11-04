package com.javaweb.service;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBuildingService {
    ResponseDTO listStaffs(Long buildingId);
    List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest);
    void deleteBuildings (List<Long> ids);
    BuildingDTO findBuildingById (Long id);
    void addOrUpdateBuildings (BuildingDTO buildingDTO);
    int countTotalItem();
}
