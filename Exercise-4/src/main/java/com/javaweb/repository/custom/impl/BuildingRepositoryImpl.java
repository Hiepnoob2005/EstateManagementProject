package com.javaweb.repository.custom.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
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
    public static void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql){
        Long staffId = buildingSearchBuilder.getStaffId();
        if (staffId != null){
            sql.append("JOIN assignmentbuilding a ON b.id = a.buildingid");
        }
    }
    public static void queryNormal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where){
        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for (Field item : fields){
                item.setAccessible(true);
                String fieldName = item.getName();
                if (!fieldName.equals("staffId") && !fieldName.startsWith("area") && !fieldName.startsWith("rentPrice") &&!fieldName.equals("typeCode")){
                    Object value = item.get(buildingSearchBuilder);
                    if (value != null){
                        if (item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer")){
                            where.append(" AND b." + fieldName + " = " + value + " ");
                        }
                        else if (item.getType().getName().equals("java.lang.String")){
                            where.append(" AND b." + fieldName + " LIKE '%" + value + "%' ");
                        }
                    }
                }
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void querySpecial (BuildingSearchBuilder buildingSearchBuilder, StringBuilder where){
        Long staffId = buildingSearchBuilder.getStaffId();
        if (staffId != null){
            where.append(" AND a.staffid = " + staffId);
        }
        Long rentAreaTo = buildingSearchBuilder.getAreaTo();
        Long rentAreaFrom = buildingSearchBuilder.getAreaFrom();
        if (rentAreaFrom != null || rentAreaTo != null){
            where.append(" AND EXISTS (SELECT * FROM rentarea r WHERE r.buildingid = b.id ");
            if (rentAreaFrom != null){
                where.append(" AND r.value >= " + rentAreaFrom + " ");
            }
            if (rentAreaTo != null){
                where.append(" AND r.value <= " + rentAreaTo + " ");
            }
            where.append(") ");
        }
        Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
        Long rentPriceTo = buildingSearchBuilder.getRentPriceTo();

        if (rentPriceFrom != null ){
                where.append(" AND b.rentprice >= " + rentPriceFrom + " ");
        }
        if (rentPriceTo != null) {
                where.append(" AND b.rentprice <= " + rentPriceTo + " ");

        }
        List<String> typeCode = buildingSearchBuilder.getTypeCode();
        if (typeCode != null && typeCode.size() != 0){
            where.append("AND( ");
            String sql = typeCode.stream().map(it -> "renttype.code LIKE" + "'%" + it + "%' ").collect(Collectors.joining(" OR "));
            where.append(sql);
            where.append(" ) ");
        }
    }
    @Transactional
    @Override
    public void deleteAssignmentByBuildingId(BuildingEntity buildingEntity) {
        String sql = "DELETE assignmentbuilding a where a.buildingid = " + buildingEntity.getId();
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }
    @Transactional
    @Override
    public void deleteRentAreaByBuildingId(BuildingEntity buildingEntity) {
        String sql = "DELETE rentarea r WHERE r.building = " + buildingEntity.getId();
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sql = new StringBuilder("SELECT b.* from building b ");
        StringBuilder where = new StringBuilder("WHERE 1 = 1 ");
        joinTable(buildingSearchBuilder, sql);
        queryNormal(buildingSearchBuilder, where);
        querySpecial(buildingSearchBuilder, where);
        where.append(" GROUP BY b.id ");
        sql.append(where.toString());
        List<BuildingEntity> result = new ArrayList<>();
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }
}
