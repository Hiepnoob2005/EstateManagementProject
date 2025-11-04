package com.javaweb.api.admin;


import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "buildingAPIOfAdmin")
@RequestMapping("/api/building")
public class BuildingAPI {
    @Autowired
    private IBuildingService IBuildingService;
    @Autowired
    private IUserService IUserService;
    //tim kiem toa nha
    @GetMapping
    public List<BuildingSearchResponse> getBuilding (BuildingSearchRequest buildingSearchRequest){
        List<BuildingSearchResponse> result = IBuildingService.findAll(buildingSearchRequest);
        return result;
    }
    @PostMapping
    public Boolean  addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO){
        //xuong db update hoac them moi
        IBuildingService.addOrUpdateBuildings(buildingDTO);
        System.out.print("thêm/sửa ok");
        return true;
    }
    @DeleteMapping("/{ids}")
    public Boolean deleteBuilding(@PathVariable List<Long> ids ){
        //xuong db de xoa building theo ds id gui ve
        IBuildingService.deleteBuildings(ids);
        System.out.print("ok");
        return true;
    }
    @GetMapping("/{id}/staffs")
    public ResponseDTO loadStaffs(@PathVariable Long id){
        ResponseDTO result = IBuildingService.listStaffs(id);
        return result;
    }
    @PostMapping("/assignment")
    public Boolean updateAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO){
        IUserService.updateAssignmentBuilding(assignmentBuildingDTO);
        System.out.print("ok");
        return true;
    }
}
