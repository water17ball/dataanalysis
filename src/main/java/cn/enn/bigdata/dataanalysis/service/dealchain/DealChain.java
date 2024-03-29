package cn.enn.bigdata.dataanalysis.service.dealchain;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 处理链类
 * 1.包含多个处理类，每个处理继承DealProcess虚基类，实现dealCurrent具体方法
 * 2.处理类中具有执行顺序index，可按顺序执行处理； 具有终结属性，如果是终结处理，则执行完成后停止处理。（可以像积木一样串行编排处理任务）
 * 3.ProcessContent处理内容：data为模板T类型
 */
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
