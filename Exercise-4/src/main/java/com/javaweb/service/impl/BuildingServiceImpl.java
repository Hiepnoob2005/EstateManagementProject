package com.javaweb.service.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.config.ModelMapperConfig;
import com.javaweb.converter.BuildingDTOConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingServiceImpl implements IBuildingService {
    @Autowired
    public BuildingRepository buildingRepository;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public RentAreaRepository rentAreaRepository;
    @Autowired
    public AssignmentBuildingRepository assignmentBuildingRepository;
    @Autowired
    public BuildingDTOConverter buildingDTOConverter;
    @Autowired
    public ModelMapperConfig modelMapper;
    @Override
    public ResponseDTO listStaffs(Long buildingId) {
        BuildingEntity building = buildingRepository.findById(buildingId).get();
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1,"STAFF");
        List<UserEntity> staffAssignment = userRepository.findUsersByBuilding(building);
//        List<UserEntity> staffAssignment = building.getUserEntities();
        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO();
        for (UserEntity it : staffs){
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setFullName(it.getFullName());
            staffResponseDTO.setStaffId(it.getId());
            if (staffAssignment.contains(it)){
                staffResponseDTO.setChecked("checked");
            }
            else {
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOS.add(staffResponseDTO);
        }
        responseDTO.setData(staffResponseDTOS);
        responseDTO.setMessage("success");
        return responseDTO;
    }

    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest) {
        List<BuildingEntity> building = buildingRepository.findAll(buildingSearchRequest);
        List<BuildingSearchResponse> buildingSearchResponses = new ArrayList<>();
        for (BuildingEntity buildingEntity : building){
            BuildingSearchResponse buildingSearchResponse = buildingDTOConverter.toBuildingSearchResponse(buildingEntity);
            buildingSearchResponses.add(buildingSearchResponse);
        }
        return buildingSearchResponses;
    }

    @Override
    public void deleteBuildings(List<Long> ids) {
        for (Long id : ids){
            buildingRepository.deleteAssignmentByBuildingId(id);
            buildingRepository.deleteRentAreaByBuildingId(id);
            buildingRepository.deleteById(id);
        }
//        buildingRepository.deleteByIdIn(ids);
    }

    @Override
    public BuildingDTO findBuildingById(Long id) {
        BuildingEntity building = buildingRepository.findById(id).get();
        BuildingDTO buildingDTO = buildingDTOConverter.toBuildingDTO_forUpdateBuilding(building);
        return buildingDTO;
    }
    @Transactional
    @Override
    public void addOrUpdateBuildings(BuildingDTO buildingDTO) {
        BuildingEntity updateOrAddBuilding = buildingDTOConverter.toBuildingEntity(buildingDTO);
        BuildingEntity existingBuilding;
        if (buildingDTO.getId() != null){
            existingBuilding = buildingRepository.findById(buildingDTO.getId()).get();
        }
        else {
            existingBuilding = new BuildingEntity();
        }
        existingBuilding = buildingDTOConverter.entityToEntity(existingBuilding, updateOrAddBuilding);
        buildingRepository.save(existingBuilding);
        rentAreaRepository.deleteBuildingById(existingBuilding.getId());
        for (RentAreaEntity rentAreaEntity : updateOrAddBuilding.getItems()){
            if (rentAreaEntity.getValue() != ""){
                rentAreaEntity.setBuilding(updateOrAddBuilding);
                rentAreaRepository.save(rentAreaEntity);
            }
        }
    }

    @Override
    public int countTotalItem() {
        return 0;
    }
}
