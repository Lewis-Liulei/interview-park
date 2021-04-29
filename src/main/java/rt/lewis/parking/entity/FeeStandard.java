package rt.lewis.parking.entity;

import lombok.Data;

/**
 * 收费标准枚举
 */
public enum FeeStandard {

    BigCar(1,10,120),
    SmallCar(2,5,60);
    Integer carType;
    Integer price;

    public Integer getCarType() {
        return carType;
    }

    public void setCarType(Integer carType) {
        this.carType = carType;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getMaxOneDay() {
        return MaxOneDay;
    }

    public void setMaxOneDay(Integer maxOneDay) {
        MaxOneDay = maxOneDay;
    }

    Integer MaxOneDay;
    FeeStandard(Integer carType ,Integer price,Integer MaxOneDay){
        this.price=price;
        this.carType=carType;
        this.MaxOneDay=MaxOneDay;
    }

    public static FeeStandard getFeeStandardByType(Integer CarType){
        if(CarType==null){
            return null;
        }
        FeeStandard[] values = FeeStandard.values();

        for (FeeStandard value : values) {
            if(value.getCarType()==CarType){
                return value;
            }
        }
        return null;


    }


}
