package cn.enn.bigdata.dataanalysis.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Cup {
    private String name;
    private int capacity;
    private int id;
}
