package rt.lewis.parking.service;

import org.springframework.stereotype.Service;
import rt.lewis.parking.entity.ParkingDO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ParkingServiceImpl implements ParkingService {

    //也可以通过@value注入,甚至可以在spring初始化完bean之后查库初始化
    //总位置是常量
    static final int totalSpace=100;

    AtomicInteger remainSpace = new AtomicInteger(100);

    public int getTotalAmount(){
        int totalAmount=0;
        //数据库条件查询已出库的车辆
        List<ParkingDO> carDOs= new ArrayList<>();
        carDOs.add(new ParkingDO().setCarType(1).setGoinTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(10,10,10)))
                .setGoOutTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(20,20,10))));

        carDOs.add(new ParkingDO().setCarType(1).setGoinTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(10,10,10)))
                .setGoOutTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(20,18,10))));


        if(!carDOs.isEmpty()){
            for (ParkingDO carDO : carDOs) {
                totalAmount+=carDO.getTotalAmount();
            }
        }
        return totalAmount;

    }
    //返回剩余车位
    public int  goInCar(){
        int i = remainSpace.decrementAndGet();
        if(i<0){
            return 0;
        }
        //插入新入场车辆，入场时间
        return i;

    }

    public int goOutCar(){
        //更新车辆离场状态。离场时间
        return remainSpace.incrementAndGet();

    }

    //@Test
    public static void main(String[] args) {
        ParkingServiceImpl parkingService = new ParkingServiceImpl();
        //parkingService.goInCar();

        new Thread(()->{
            for(int i=0;i<10;i++){
                int iu = parkingService.goInCar();
                System.out.println("进来后，还有空位个数:  "+iu);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
            }

        }).start();

       /* try {
            Thread.sleep(22000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
*/
        new Thread(()->{
            for(int i=0;i<10;i++){
                int iu = parkingService.goOutCar();
                System.out.println("出去后，还有空位个数:  "+iu);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {


                }
            }

        }).start();
    }

}
