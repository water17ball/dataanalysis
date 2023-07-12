package cn.enn.bigdata.dataanalysis.service.dealchain;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DealChain {

    /**
     * 注入全部类型的处理
     */
    @Resource
    Map<String, DealProcess> dealProcessMap = new ConcurrentHashMap<>();

    private List<DealProcess> processList;

    /**
     * 排序
     */
    @PostConstruct
    public void sort(){
        List<DealProcess> values = new ArrayList(dealProcessMap.values());
        Collections.sort(values, Comparator.comparing(DealProcess::getProcessIndex));
        processList = values;
    }

    public void deal(ProcessContent content){
        if(CollectionUtils.isEmpty(processList)){
            return;
        }

        for (DealProcess process : processList) {
            process.deal(content);
            if(process.isEndProcess()){
                return;
            }
        }
    }
}
