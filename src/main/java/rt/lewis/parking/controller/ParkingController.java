package rt.lewis.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rt.lewis.parking.entity.ResultBean;
import rt.lewis.parking.service.ParkingService;

/**
 * 代码省略了dao层，只用以展示思想，hsqldb确实不熟练，原本我都是用mysql
 * 如果要写 肯定是mybatis-plus 我的git 上面有这些项目
 * 1.关于剩余车位的一致性的问题，实际情况来说，单一停车场，没有高并发，故可以直接查数据库表
 * 我这里用了原子类来模拟剩余车位的一致性
 * 2.代码省略了dao层,对数据库的操作都是常规代码，
 */
@RestController
@RequestMapping("parking")
public class ParkingController {
    @Autowired
    ParkingService parkingService;
    @GetMapping("ta")
    public ResultBean getTotalAmount(){
      return ResultBean.toBean( parkingService.getTotalAmount());
  }

    @GetMapping("incar")
    public ResultBean goInCar() {
        return ResultBean.toBean( parkingService.goInCar());
    }

    @GetMapping("outcar")
    public ResultBean goOutCar() {
        return ResultBean.toBean( parkingService.goOutCar());
    }


}
