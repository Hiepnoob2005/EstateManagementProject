package com.javaweb.model.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildingDTO extends AbstractDTO{
    private String name;
    private Long floorArea;
    private String district;
    private String ward;
    private String street;
    private Long numberOfBasement;
    private String direction;
    private Long level;
    private String rentArea;
    private Long rentPrice;
    private String rentpricedescription;
    private Long serviceFee;
    private String structure;
    private Long carFee;
    private Long motobikeFee;
    private Long overtimeFee;
    private Long electricityFee;
    private Long deposit;
    private Long payment;

    public String getRentpricedescription() {
        return rentpricedescription;
    }

    public void setRentpricedescription(String rentpricedescription) {
        this.rentpricedescription = rentpricedescription;
    }

    private Long rentTime;
    private Long decorationTime;
    private Long brokerageFee;
    private Long note;
    private String managerName;
    private String managerPhone;
    private List<String> typeCode;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Long floorArea) {
        this.floorArea = floorArea;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Long getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(Long numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

//    public Long getAreaFrom() {
//        return areaFrom;
//    }
//
//    public void setAreaFrom(Long areaFrom) {
//        this.areaFrom = areaFrom;
//    }
//
//    public Long getAreaTo() {
//        return areaTo;
//    }
//
//    public void setAreaTo(Long areaTo) {
//        this.areaTo = areaTo;
//    }
//
//    public Long getRentPriceFrom() {
//        return rentPriceFrom;
//    }
//
//    public void setRentPriceFrom(Long rentPriceFrom) {
//        this.rentPriceFrom = rentPriceFrom;
//    }

//    public Long getRentPriceTo() {
//        return rentPriceTo;
//    }
//
//    public void setRentPriceTo(Long rentPriceTo) {
//        this.rentPriceTo = rentPriceTo;
//    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public String getRentArea() {
        return rentArea;
    }

    public void setRentArea(String rentArea) {
        this.rentArea = rentArea;
    }

    public Long getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Long rentPrice) {
        this.rentPrice = rentPrice;
    }
    public List<String> getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(List<String> typeCode) {
        this.typeCode = typeCode;
    }



    public Long getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Long serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public Long getCarFee() {
        return carFee;
    }

    public void setCarFee(Long carFee) {
        this.carFee = carFee;
    }

    public Long getMotobikeFee() {
        return motobikeFee;
    }

    public void setMotobikeFee(Long motobikeFee) {
        this.motobikeFee = motobikeFee;
    }

    public Long getOvertimeFee() {
        return overtimeFee;
    }

    public void setOvertimeFee(Long overtimeFee) {
        this.overtimeFee = overtimeFee;
    }

    public Long getElectricityFee() {
        return electricityFee;
    }

    public void setElectricityFee(Long electricityFee) {
        this.electricityFee = electricityFee;
    }

    public Long getDeposit() {
        return deposit;
    }

    public void setDeposit(Long deposit) {
        this.deposit = deposit;
    }

    public Long getPayment() {
        return payment;
    }

    public void setPayment(Long payment) {
        this.payment = payment;
    }

    public Long getRentTime() {
        return rentTime;
    }

    public void setRentTime(Long rentTime) {
        this.rentTime = rentTime;
    }

    public Long getDecorationTime() {
        return decorationTime;
    }

    public void setDecorationTime(Long decorationTime) {
        this.decorationTime = decorationTime;
    }

    public Long getBrokerageFee() {
        return brokerageFee;
    }

    public void setBrokerageFee(Long brokerageFee) {
        this.brokerageFee = brokerageFee;
    }

    public Long getNote() {
        return note;
    }

    public void setNote(Long note) {
        this.note = note;
    }
}