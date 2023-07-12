package cn.enn.bigdata.dataanalysis.controller;


import cn.enn.bigdata.dataanalysis.service.dealchain.DealChain;
import cn.enn.bigdata.dataanalysis.service.dealchain.ProcessContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/deal/process")
public class DealProcessController {
    @Resource
    private DealChain dealChain;

    @GetMapping("/do")
    public String doProcess() {
        ProcessContent<Integer> processContent = new ProcessContent<>();
        processContent.setData(0);

        dealChain.deal(processContent);

        log.info("content: {}",processContent.getData());
        log.info("process List: {}",processContent.getDealProcessList());



        return "ok";
    }









}
