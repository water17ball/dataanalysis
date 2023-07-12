package cn.enn.bigdata.dataanalysis.service.dealchain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理内容类
 * 在多个处理流程中流转的内容对象
 * @param <T>
 */
@Data
public class ProcessContent<T> {
    /**
     * 处理数据
     */
    private T data;

    /**
     * 记录处理的过程编号的列表
     */
    private List<Integer> dealProcessList = new ArrayList<>();

    public void addDealProcessList(Integer index){
        dealProcessList.add(index);
    }

}
