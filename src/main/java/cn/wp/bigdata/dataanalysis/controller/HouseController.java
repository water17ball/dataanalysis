package cn.wp.bigdata.dataanalysis.controller;

import cn.wp.bigdata.dataanalysis.entity.HouseResult;
import cn.wp.bigdata.dataanalysis.entity.Student;
import cn.wp.bigdata.dataanalysis.service.HouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableAsync
@Api(value = "house控制接口", tags = "house控制接口")
@RestController
@RequestMapping("/house")
public class HouseController {
    @Autowired
    private HouseService houseService;

    @Autowired
    Student student;

//    @Async
    @ApiOperation(value = "获取house", notes = "获取house")
    @GetMapping("/getAll")
    public List<HouseResult> getAllHouse(){
        List<HouseResult> houseResultList = houseService.getAllHouse();
        System.out.println(houseResultList);
        return houseResultList;
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
