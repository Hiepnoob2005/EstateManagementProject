package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.AssignmentBuildingRepositoryCustom;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
//@PropertySource("classpath:application.properties")
public class AssignmentBuildingRepositoryImpl implements AssignmentBuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    @Override
    public void deleteAssignmentBuildingEntityByBuilding(BuildingEntity buildingEntity) {
        String sql = "DELETE assignmentbuilding from assignmentbuiling a where a.buildingid = " + buildingEntity.getId();
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }
}
