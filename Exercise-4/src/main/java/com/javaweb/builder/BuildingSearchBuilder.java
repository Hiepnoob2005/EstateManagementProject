package com.javaweb.builder;

import java.util.List;

public class BuildingSearchBuilder {
    private String name;
    private Long floorArea;
    private Long districtId;
    private String street;
    private String ward;
    private Long numberOfBasement;
    private String direction;
    private String level;
    private Long areaFrom;
    private Long areaTo;
    private Long rentPriceFrom;
    private Long rentPriceTo;
    private String managerName;
    private String managerPhoneNumber;
    private List<String> typeCode;
    private Long staffId;
    private BuildingSearchBuilder (Builder builder){
        this.name = builder.name;
        this.floorArea = builder.floorArea;
        this.ward = builder.ward;
        this.street = builder.street;
        this.districtId = builder.districtId;
        this.numberOfBasement = builder.numberOfBasement;
        this.typeCode = builder.typeCode;
        this.managerName = builder.managerName;
        this.managerPhoneNumber = builder.managerPhoneNumber;
        this.areaFrom = builder.areaFrom;
        this.areaTo = builder.areaTo;
        this.rentPriceFrom = builder.rentPriceFrom;
        this.rentPriceTo = builder.rentPriceTo;
        this.staffId = builder.staffId;
    }
    public String getName() {
        return name;
    }

    public Long getFloorArea() {
        return floorArea;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public String getStreet() {
        return street;
    }

    public String getWard() {
        return ward;
    }

    public Long getNumberOfBasement() {
        return numberOfBasement;
    }

    public String getDirection() {
        return direction;
    }

    public String getLevel() {
        return level;
    }

    public Long getAreaFrom() {
        return areaFrom;
    }

    public Long getAreaTo() {
        return areaTo;
    }

    public Long getRentPriceFrom() {
        return rentPriceFrom;
    }

    public Long getRentPriceTo() {
        return rentPriceTo;
    }

    public String getManagerName() {
        return managerName;
    }

    public String getManagerPhoneNumber() {
        return managerPhoneNumber;
    }

    public Long getStaffId() {
        return staffId;
    }

    public List<String> getTypeCode() {
        return typeCode;
    }
    public static class Builder {
        private String name;
        private Long floorArea;
        private Long districtId;
        private String street;
        private String ward;
        private Long numberOfBasement;
        private String direction;
        private String level;
        private Long areaFrom;
        private Long areaTo;
        private Long rentPriceFrom;
        private Long rentPriceTo;
        private String managerName;
        private String managerPhoneNumber;
        private List<String> typeCode;
        private Long staffId;
        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        public Builder setFloorArea(Long floorArea) {
            this.floorArea = floorArea;
            return this;
        }
        public Builder setWard (String ward) {
            this.ward = ward;
            return this;
        }
        public Builder setStreet (String street) {
            this.street = street;
            return this;
        }
        public Builder setDistrictId (Long districtId) {
            this.districtId = districtId;
            return this;
        }
        public Builder setNumberOfBasement (Long numberOfBasement) {
            this.numberOfBasement = numberOfBasement;
            return this;
        }
        public Builder setTypeCode (List<String> typeCode) {
            this.typeCode = typeCode;
            return this;
        }
        public Builder setManagerName (String managerName) {
            this.managerName = managerName;
            return this;
        }
        public Builder setManagerPhoneNumber (String managerPhoneNumber) {
            this.managerPhoneNumber = managerPhoneNumber;
            return this;
        }
        public Builder setRentAreaTo (Long areaTo) {
            this.areaTo = areaTo;
            return this;
        }
        public Builder setRentAreaFrom (Long areaFrom) {
            this.areaFrom = areaFrom;
            return this;
        }
        public Builder setRentPriceFrom (Long rentPriceFrom) {
            this.rentPriceFrom = rentPriceFrom;
            return this;
        }
        public Builder setRentPriceTo (Long rentPriceTo) {
            this.rentPriceTo = rentPriceTo;
            return this;
        }
        public Builder setStaffId (Long staffId) {
            this.staffId = staffId;
            return this;
        }
        public BuildingSearchBuilder build() {
            return new BuildingSearchBuilder(this);
        }
    }
}
