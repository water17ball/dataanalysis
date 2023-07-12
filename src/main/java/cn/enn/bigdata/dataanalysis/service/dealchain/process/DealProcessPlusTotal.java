package cn.enn.bigdata.dataanalysis.service.dealchain.process;

import cn.enn.bigdata.dataanalysis.service.dealchain.DealProcess;
import cn.enn.bigdata.dataanalysis.service.dealchain.ProcessContent;
import org.springframework.stereotype.Component;

@Component
public class DealProcessPlusTotal extends DealProcess {
    DealProcessPlusTotal(){
        this.setProcessIndex(5);
        this.setEndProcess(true);
    }
    /**
     * 具体处理过程，需要实现
     *
     * @param content
     */
    @Override
    public void dealCurrent(ProcessContent content) {
        content.setData((Integer)content.getData() + 5);
    }
}
