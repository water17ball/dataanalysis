package cn.enn.bigdata.dataanalysis.controller;

import cn.enn.bigdata.dataanalysis.aop.annotation.PrintMethod;
import cn.enn.bigdata.dataanalysis.aop.annotation.PrintMethodResult;
import cn.enn.bigdata.dataanalysis.entity.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Api(value = "hello控制接口", tags = "hello控制接口")
@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    Student student;

    public static String[] chars = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "J", "H", "I", "G", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    @PrintMethod
    @PrintMethodResult
    @ApiOperation(value = "获取hello", notes = "获取hello")
    @GetMapping("/get")
    public String getHello(){
        return "Hello";
    }

    @GetMapping("/sendEmail")
    public String sendEmail() {
//        MailUtil.send("water18ball@163.com","hello mail", "heheheloooooo!", false, null);

        return "sendEmail";
    }

    @GetMapping("/getRandomENN")
    public String getRandom() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("ENNB");
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        for (int i = 0; i < 4; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
//            buffer.append(chars[x%0x3E]);
            buffer.append(chars[x % 0x24]);
        }
        return buffer.toString();
    }

    @GetMapping("/getUUID")
    public String getUUID() {

        return UUID.randomUUID().toString().replaceAll("-", "");

    }

    @GetMapping("/getAutoBox")
    public void getAutoBox() {

        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;

        System.out.println(c == d);
        System.out.println(e == f);
        System.out.println(c == (a + b));
        System.out.println(c.equals(a + b));
        System.out.println(g == (a + b));
        System.out.println(g.equals(a + b));


    }

    @GetMapping("/getSingletonStudent")
    public String getStudent() {
        System.out.println(student);
        return student.toString();
    }


    @GetMapping("/setSingletonStudent")
    public String setStudent() {
//        student.setId(1);
        student.setName("hello Student");
        student.setAge(20);
        System.out.println(student);
        return student.toString();
    }






}
