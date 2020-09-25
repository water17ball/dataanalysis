package cn.enn.bigdata.dataanalysis.controller;

import cn.enn.bigdata.dataanalysis.service.StatemachineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "状态机",tags = "状态机")
@RestController
@RequestMapping("/statemachine")
public class StatemachineController {
    @Autowired
    StatemachineService statemachineService;

    @ApiOperation(value = "执行", notes = "执行")
    @GetMapping("/run")
    public void run(){
        statemachineService.runService();
    }

    @ApiOperation(value = "订单", notes = "订单")
    @GetMapping("/deal")
    public void deal(){
        try {
            statemachineService.runDeal();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
