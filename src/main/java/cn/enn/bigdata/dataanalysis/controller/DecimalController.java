package cn.enn.bigdata.dataanalysis.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Api(value = "decimal商业数值计算", tags = "decimal商业数值计算")
@RestController
@RequestMapping("/decimal")
public class DecimalController {

    @ApiOperation(value = "获取结果", notes = "获取结果")
    @GetMapping("/getResult")
    public String getResult(){
        BigDecimal plusDecimalA = new BigDecimal("0.004");
        BigDecimal plusDecimalB = new BigDecimal("0.032");
        BigDecimal add = plusDecimalA.add(plusDecimalB);

        System.out.println(add.toEngineeringString());
        System.out.println(add.toPlainString());

        return add.toString();
    }
}
