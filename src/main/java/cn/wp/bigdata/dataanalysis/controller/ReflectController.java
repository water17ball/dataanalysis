package cn.wp.bigdata.dataanalysis.controller;

import cn.wp.bigdata.dataanalysis.entity.ClassMethod;
import cn.wp.bigdata.dataanalysis.service.ReflectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "反射", tags = "反射")
@RestController
@RequestMapping("/reflect")
public class ReflectController {

    @Autowired
    ReflectService reflectService;

    @ApiOperation(value = "执行类的方法", notes = "执行类的方法")
    @PostMapping("/runClassMethod")
    public String runClassMethod(@RequestBody ClassMethod classMethod) {
        String result = "run Success!";
        result = reflectService.runClassMethod(classMethod);
        return result;
    }
}
