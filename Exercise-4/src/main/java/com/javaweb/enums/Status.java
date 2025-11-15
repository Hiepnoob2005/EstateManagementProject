package com.javaweb.enums;

import java.util.HashMap;
import java.util.Map;

public enum Status {
    DANG_XL ("Đang xử lý"),
    DA_XL("Đã xử lý"),
    CHUA_XL("Chưa xử lý");

    private final String statusname;
    Status(String statusName) {
        this.statusname = statusName;
    }


    public static Map<String,String> statusType(){
        Map<String,String> status = new HashMap<>();
        for(Status item : Status.values()){
            status.put(item.toString() , item.statusname);
        }
        return status;
    }
}
