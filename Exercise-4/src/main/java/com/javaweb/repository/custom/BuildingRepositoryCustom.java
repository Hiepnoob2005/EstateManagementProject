package com.javaweb.repository.custom;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;

import java.util.List;

public interface BuildingRepositoryCustom {
    void deleteAssignmentByBuildingId(BuildingEntity buildingEntity);
    void deleteRentAreaByBuildingId(BuildingEntity buildingEntity);
    List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder);
}
