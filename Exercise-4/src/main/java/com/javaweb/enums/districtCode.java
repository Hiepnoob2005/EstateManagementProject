package com.javaweb.enums;

import java.util.HashMap;
import java.util.Map;

public enum  districtCode {
    QUAN_1 ("Quận 1"),
    QUAN_2 ("Quận 2"),
    QUAN_3 ("Quận 3"),
    QUAN_4 ("Quận 4"),
    QUAN_5 ("Quận 5"),
    QUAN_6 ("Quận 6"),
    QUAN_7 ("Quận 7"),
    QUAN_8 ("Quận 8"),
    Quan_9 ("Quận 9"),
    Quan_11 ("Quận 11"),
    Quan_BINH_THACH("Quận Bình Thạnh")
    ;

    private final String districtName;
    districtCode(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public static Map<String,String> type(){
//        Map<String,String> listType = new HashMap<>();
//        for(districtCode item : districtCode.values()){
//            listType.put(item.toString() , item.districtName);
//        }
//        return listType;
        Map<String,String> districts = new HashMap<>();
        for (districtCode it:  districtCode.values()){
            districts.put(it.toString(),it.getDistrictName());
        }
        return districts;
    }
}
