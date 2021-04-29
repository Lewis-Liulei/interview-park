package rt.lewis.parking.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Accessors(chain = true)
public class ParkingDO {

    Integer id;

    String carNo;

    Integer carType;

    Integer state;

    LocalDateTime goinTime;

    LocalDateTime goOutTime;

    public int getTotalAmount(){
        if(goOutTime==null){
            return 0;
        }
        Duration duration = Duration.between(goinTime, goOutTime);

        int stopHours=0;
        int dminutes = (int) (duration.toMinutes());
        if(dminutes%60>0)
            stopHours= dminutes/60+1;
        else
            stopHours=dminutes/60;

        FeeStandard feeStandardByType = FeeStandard.getFeeStandardByType(carType);
        return stopHours / 24 * feeStandardByType.getMaxOneDay()
               +stopHours%24 * feeStandardByType.getPrice();
    }

  /*  public static void main(String[] args) {
        System.out.println(getTotalAmount());
    }*/

}
