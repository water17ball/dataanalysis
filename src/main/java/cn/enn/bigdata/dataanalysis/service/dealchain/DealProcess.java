package cn.enn.bigdata.dataanalysis.service.dealchain;

import lombok.Data;

/**
 * 处理过程类
 * deal方法：进行具体处理，并记录处理的过程编号processIndex
 */
@Data
public abstract class DealProcess {
    private Integer processIndex;
    /**
     * 是否终结过程
     */
    private boolean isEndProcess;

//    private ProcessContent content;  处理内容

    private int beforeStatus;
    private int afterStatus;

    public void deal(ProcessContent content){
        dealCurrent(content);
        content.addDealProcessList(this.processIndex);
    }

    /**
     * 具体处理过程，需要实现
     * @param content
     */
    public abstract void dealCurrent(ProcessContent content);

}
