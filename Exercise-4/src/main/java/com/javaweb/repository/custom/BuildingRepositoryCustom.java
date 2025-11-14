package com.javaweb.repository.custom;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BuildingRepositoryCustom {
    void deleteAssignmentByBuildingId(Long id);
    void deleteRentAreaByBuildingId(Long id);
//    void deleteRentAreaByBuildingId_IdIn(List<Long> ids);
    List<BuildingEntity> findAll(BuildingSearchRequest buildingSearchRequest, Pageable pageable);
    int countTotalItem();
}
