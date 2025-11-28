package com.javaweb.repository.custom.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@PropertySource("classpath:application.properties")
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    @Value("${spring.datasource.url}")
    private String DB_URL;
    @Value("${spring.datasource.username}")
    private String USER;
    @Value("${spring.datasource.password}")
    private String PASS;
    public static void joinTable(BuildingSearchRequest buildingSearchRequest, StringBuilder sql){
        if (buildingSearchRequest.getStaffId() != null){
            sql.append(" JOIN assignmentbuilding a on a.buildingid = b.id ");
        }
    }
    public static void queryNormal(BuildingSearchRequest buildingSearchRequest, StringBuilder where){
        try {
            Field fields[] = buildingSearchRequest.getClass().getDeclaredFields();
            for (Field item :fields){
                item.setAccessible(true);
                String fieldName = item.getName();
                if (!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("rentPrice") &&
                        !fieldName.startsWith("area")){
                    Object value = item.get(buildingSearchRequest);
                    if (value != null && value != ""){
                        if (item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer"))
                        {
                            where.append(" AND b." + fieldName + " = " + value + " ");
                        }
                        else if (item.getType().getName().equals("java.lang.String")){
                            where.append(" AND b." + fieldName + " LIKE '%" + value + "%' ");
                        }
                    }
                }
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void querySpecial (BuildingSearchRequest buildingSearchRequest, StringBuilder where){
        Long staffId = buildingSearchRequest.getStaffId();
        if (staffId != null){
            where.append(" AND a.staffid = " + staffId);

        }
        Long rentAreaFrom = buildingSearchRequest.getAreaFrom();
        Long rentAreaTo = buildingSearchRequest.getAreaTo();
        if (rentAreaFrom != null  || rentAreaTo != null){
            where.append(" AND EXIST (SELECT * FROM rentarea r where r.buildingid = b.id ");
            if (rentAreaFrom != null){
                where.append(" AND r.value >= " + rentAreaFrom + " ");
            }if (rentAreaTo != null){
                where.append(" AND r.value <= " + rentAreaTo + " ");
            }
            where.append(") ");
        }
        Long rentPriceFrom = buildingSearchRequest.getRentPriceFrom();
        Long rentPriceTo = buildingSearchRequest.getRentPriceTo();
        if (rentPriceFrom != null) {
            where.append(" b.rentprice >= " + rentPriceFrom + " ");
        }
        if (rentPriceTo != null){
            where.append(" b.rentprice <= " + rentPriceTo + " ");
        }
        List<String> typeCode = buildingSearchRequest.getTypeCode();
        if (typeCode != null && typeCode.size() != 0){
            where.append(" AND (");
            String sql = typeCode.stream().map(it -> "b.type LIKE '%" + it + "%' ").collect(Collectors.joining(","));
            where.append(sql);
            where.append(") ");
        }
    }
    @Transactional
    @Override
    public void deleteAssignmentByBuildingId(Long id) {
        String sql = "DELETE assignmentbuilding a where a.buildingid = " + id;
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }
    @Transactional
    @Override
    public void deleteRentAreaByBuildingId(Long id) {
        String sql = "DELETE rentarea r where r.buildingid = " + id;
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Override
    public List<BuildingEntity> findAll(BuildingSearchRequest buildingSearchRequest, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT b.* from building b ");
        joinTable(buildingSearchRequest, sql);
        StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
        queryNormal(buildingSearchRequest, where);
        querySpecial(buildingSearchRequest, where);
        where.append(" GROUP BY b.id ");
        if (pageable != null){
            int t = pageable.getPageSize();
            Long m = pageable.getOffset();
            sql.append(where).append(" LIMIT " + t). append(" OFFSET " + m);
        }
        sql.append(" ; ");
//        List<BuildingEntity> result = new ArrayList<>();
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

    @Override
    public int countTotalItem() {
        String sql = "SELECT * FROM building b WHERE 1 = 1 ";
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList().size();
    }

}
