package cn.wp.bigdata.dataanalysis.service;

import java.util.List;

public interface RelationOp {
    public List<Long> getParent(Long id);
    public List<Long> getSons(Long id);
}
