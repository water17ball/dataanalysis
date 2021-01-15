package cn.enn.bigdata.dataanalysis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@Slf4j
@RequestMapping("/kafka")
@RestController
public class KafkaTempController {
    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @GetMapping("/send/{msg}")
    public String sendMsg(String msg) throws ExecutionException, InterruptedException {
        ListenableFuture<SendResult<String, Object>> listenableFuture = kafkaTemplate.send("topic_input", msg);
        SendResult<String, Object> sendResult = listenableFuture.get();
        return sendResult.toString();
    }

//    @KafkaListener(id = "webGroup", topics = "topic_input")
//    @SendTo()
//    public String listenMsg(String msg){
//        log.info("listenMsg: group[webGroup], msg:[{}]",msg);
//        return msg;
//    }

}
