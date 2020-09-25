package cn.wp.bigdata.dataanalysis.designpattern.respchain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 任务类
 */
@Data
@AllArgsConstructor
public class Task {
    private String name;
    private int value;

}
