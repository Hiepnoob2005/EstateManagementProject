package com.javaweb.controller.admin;



import com.javaweb.constant.SystemConstant;
import com.javaweb.enums.buildingType;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IBuildingService iBuildingService;
    @GetMapping(value = "/admin/building-list")
    public ModelAndView buildingList(@ModelAttribute (SystemConstant.MODEL) BuildingSearchRequest buildingSearchRequest, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/list");
        mav.addObject("modelSearch",buildingSearchRequest);
        //xuong db lấy data ok r
        List<BuildingSearchResponse> responseList = iBuildingService.findAll(buildingSearchRequest);
        buildingSearchRequest.setListResult(responseList);
        buildingSearchRequest.setTotalItems(iBuildingService.countTotalItem());
        mav.addObject("buildingList", buildingSearchRequest);
        mav.addObject("listStaffs",iUserService.getStaffs());
        mav.addObject("districts",districtCode.type());
        mav.addObject("typeCodes",buildingType.type());

        return mav;
    }
    @GetMapping(value = "/admin/building-edit")
    public ModelAndView buildingEdit(@ModelAttribute ("buildingEdit") BuildingDTO buildingDTO, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/edit");
//        mav.addObject("building", buildingDTO);
        mav.addObject("districts", districtCode.type());
        mav.addObject("typeCodes", buildingType.type());
        return mav;
    }
    @RequestMapping(value = "/admin/building-edit-{id}",method = RequestMethod.GET)
    public ModelAndView buildingEdit(@PathVariable("id") Long id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        BuildingDTO buildingDTO = iBuildingService.findBuildingById(id);
        mav.addObject("buildingEdit",buildingDTO);
        mav.addObject("districts", districtCode.type());
        mav.addObject("typeCodes", buildingType.type());
        //xuong db tìm building theo id
//        mav.addObject("building", buildingDTO);
        return mav;
    }
}
