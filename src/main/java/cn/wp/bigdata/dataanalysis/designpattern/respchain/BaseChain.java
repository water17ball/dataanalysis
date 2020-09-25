package cn.wp.bigdata.dataanalysis.designpattern.respchain;

import lombok.Data;

/**
 * 责任链基类
 */
@Data
public abstract class BaseChain {
    public abstract boolean doTask(Task task);
    private BaseChain next;
}
