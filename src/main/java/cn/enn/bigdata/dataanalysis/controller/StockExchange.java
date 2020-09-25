package cn.enn.bigdata.dataanalysis.controller;

import cn.enn.bigdata.dataanalysis.entity.ApiResultDto;
import cn.enn.bigdata.dataanalysis.entity.Student;
import cn.enn.bigdata.dataanalysis.model.exchange.OperInfo;
import cn.enn.bigdata.dataanalysis.service.StockServer;
import cn.enn.bigdata.dataanalysis.service.impl.StockServerImpl;
import cn.hutool.db.StatementUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Api(value = "交易控制接口", tags = "交易控制接口")
@RestController
@RequestMapping("/exchange")
public class StockExchange {

    @Autowired
    private StockServer stockServer;

    @Autowired
    Student student;

    @ApiOperation(value = "买入", notes = "买入")
    @GetMapping("/buy")
    public ApiResultDto buy(OperInfo operInfo) {
        ApiResultDto resultDto = new ApiResultDto();

        stockServer.buy(operInfo);
        return resultDto;
    }

    @ApiOperation(value = "卖出", notes = "卖出")
    @GetMapping("/sell")
    public ApiResultDto sell(OperInfo operInfo) {
        ApiResultDto resultDto = new ApiResultDto();

        return resultDto;
    }


    @ApiOperation(value = "基本信息", notes = "基本信息")
    @GetMapping("/info")
    public ApiResultDto info() {
        ApiResultDto resultDto = new ApiResultDto();
        resultDto.setData("基本信息V1.0");
        return resultDto;
    }

    @ApiOperation(value = "获取信息", notes = "获取信息")
    @PostMapping("/get")
    public ApiResultDto get(@RequestBody Long id) {
        ApiResultDto resultDto = new ApiResultDto();
        resultDto.setData(stockServer.getBuy(id));
        return resultDto;
    }


    @ApiOperation(value = "测试异步线程池", notes = "测试异步线程池")
    @GetMapping("/testExec")
    public ApiResultDto testExec() {
        ApiResultDto resultDto = new ApiResultDto();

//        ExecutorService es = Executors.newSingleThreadExecutor();
//        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
//        singleThreadExecutor.submit(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    long i = 1L;
//                    while (true){
//                    Thread.sleep(1000);
//                        System.out.println(i++);
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        singleThreadExecutor.shutdown();

//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    long i = 1L;
//                    while (true){
//                    Thread.sleep(1000);
//                        System.out.println(i++);
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

//        thread.start();

        Callable cs = () -> {

            try {
                long i = 1L;
                while (i < 10) {
                    Thread.sleep(1000);
                    System.out.println(i++);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "Success!";
        };

        try {
            Object call = cs.call();
            resultDto.setData(cs);
        } catch (Exception e) {
            e.printStackTrace();
        }


//        resultDto.setData("执行完成");
        return resultDto;
    }


    @GetMapping("/getSingletonStudent")
    public String getStudent() {
        System.out.println(student);
        return student.toString();
    }


    @GetMapping("/setSingletonStudent")
    public String setStudent() {
//        student.setId(2);
        student.setName("house Student");
        student.setAge(30);
        System.out.println(student);
        return student.toString();
    }

}
