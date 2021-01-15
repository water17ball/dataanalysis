package cn.enn.bigdata.dataanalysis.controller;


import cn.enn.bigdata.dataanalysis.WebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KafkaFakeServer.class)
@EmbeddedKafka(count = 4, ports = {9092, 9093, 9094, 9095})
public class KafkaFakeServer {

    @Test
    public void contextLoads() throws IOException {
        System.in.read();
    }
}
