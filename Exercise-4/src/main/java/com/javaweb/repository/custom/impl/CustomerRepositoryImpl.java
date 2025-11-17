package com.javaweb.repository.custom.impl;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
@Repository
@PropertySource("classpath:application.properties")
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    @Value("${spring.datasource.url}")
    private String DB_URL;
    @Value("${spring.datasource.username}")
    private String USER;
    @Value("${spring.datasource.password}")
    private String PASS;
    public static void joinTable (CustomerSearchRequest customerSearchRequest, StringBuilder sql){
        Long staffId = customerSearchRequest.getStaffId();
        if (staffId != null){
            sql.append(" JOIN assignmentcustomer as ON customer.id = as.customerid ");
        }
    }
    public static void querySpecial (CustomerSearchRequest customerSearchRequest, StringBuilder where){
        Long staffId = customerSearchRequest.getStaffId();
        if (staffId != null){
            where.append(" AND as.staffid = " + staffId + " ");
        }
    }
    public static void queryNormal(CustomerSearchRequest customerSearchRequest, StringBuilder where){
        try {
            Field[] fields = customerSearchRequest.getClass().getDeclaredFields();
            for (Field field : fields){
                field.setAccessible(true);
                String fieldName = field.getName();
                if (!fieldName.equals("staffid")){
                    Object value = field.get(customerSearchRequest);
                    if (value != null && value != ""){
                        if (field.getType().getName().equals("java.lang.Long") || field.getType().getName().equals("java.lang.Integer")){
                            where.append(" AND customer." +fieldName + " = " + value + " ") ;
                        }
                        if (field.getType().getName().equals("java.lang.String")){
                            where.append(" AND customer." + fieldName + " LIKE '%" + value + "%' ");
                        }
                    }
                }
            }
        }

        catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public List<CustomerEntity> findAll(CustomerSearchRequest customerSearchRequest, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT * FROM customer ");
        List<CustomerEntity> result = new ArrayList<>();
        StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
        joinTable(customerSearchRequest, sql);
        queryNormal(customerSearchRequest, where);
        querySpecial(customerSearchRequest, where);
        sql.append(where);
        where.append(" AND customer.is_active = 1 ");
        where.append(" GROUP BY customer.id ");
        if (pageable != null){
            int t = pageable.getPageSize();
            Long m = pageable.getOffset();
            sql.append(where).append(" LIMIT " + t). append(" OFFSET " + m);
        }
        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
        return query.getResultList();
    }

    @Override
    public int countTotalItem() {
        String sql = "SELECT * FROM customer WHERE 1 = 1 ";
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList().size();
    }
}
