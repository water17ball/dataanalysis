package cn.enn.bigdata.dataanalysis;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;


/**
 * Created by renlc on 2019/01/08
 */
@ComponentScan
@EnableAutoConfiguration(exclude = PageHelperAutoConfiguration.class)
@EnableAsync
public class WebApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebApplication.class, args);

    }
}
