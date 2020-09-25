package cn.wp.bigdata.dataanalysis.controller;

import cn.wp.bigdata.dataanalysis.entity.DataWithThreshold;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(value = "数据跳变检测", tags = "数据跳变检测")
@RestController
@RequestMapping("/dataJump")
public class DataJumpController {

    @ApiOperation(value = "检测数据跳变", notes ="检测数据跳变")
    @PostMapping("/detect")
    public List<Double> detectDataJump(@RequestBody List<Double> data) {
        List<Double> jumpData = new ArrayList<>();
        Double tinyNum = 0.00001;
        if (data != null) {
            for (int i = 0; i < data.size()-1; i++) {
                if(Math.abs((tinyNum+data.get(i+1)) / (tinyNum+data.get(i))) > 10){
                    jumpData.add(data.get(i+1));
                }
                else if(Math.abs((tinyNum+data.get(i)) / (tinyNum+data.get(i+1))) > 10){
                    jumpData.add(data.get(i));
                }
            }
        }
        return jumpData;
    }

    @ApiOperation(value = "检测数据跳变，带阈值", notes ="检测数据跳变，带阈值")
    @PostMapping("/detectWithThreshold")
    public List<Double> detectDataJumpWithThreshold(@RequestBody DataWithThreshold dataWithThreshold) {
        List<Double> jumpData = new ArrayList<>();
        List<Double> data  = dataWithThreshold.getData();
        Double tinyNum = 0.00001;

        if (data != null) {
            for (int i = 0; i < data.size()-1; i++) {
                //前后两个值比值较大（比如10,11,12,11,--100--,15,11,13）
                if(Math.abs((tinyNum+data.get(i+1)) / (tinyNum+data.get(i))) > dataWithThreshold.getThreshold()){
                    jumpData.add(data.get(i+1));
                }
                else if(Math.abs((tinyNum+data.get(i)) / (tinyNum+data.get(i+1))) > dataWithThreshold.getThreshold()){
                    jumpData.add(data.get(i));
                }
                //前后两个值差值对比原值较大（比如50,51,52,51,53,-51,50,51,52,51,53）
                else if(Math.abs(((tinyNum+data.get(i+1)) - (tinyNum+data.get(i)))/(tinyNum+data.get(i))) > dataWithThreshold.getThreshold()){
                    jumpData.add(data.get(i+1));
                }
                else if(Math.abs(((tinyNum+data.get(i+1)) - (tinyNum+data.get(i)))/(tinyNum+data.get(i+1))) > dataWithThreshold.getThreshold()){
                    jumpData.add(data.get(i));
                }
                //当与均值的偏差大于3倍标准差，就是跳变数据
                else{
                    //计算标准差
                    //1.计算均值
//                    data.stream().map(x->x).collect(Collectors.minBy())
                }
            }
        }
        return jumpData;
    }
}
