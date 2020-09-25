package cn.enn.bigdata.dataanalysis.service.impl;

import cn.enn.bigdata.dataanalysis.dao.local.CalcCodeRelationMapper;
import cn.enn.bigdata.dataanalysis.dao.local.CalcCodeRelationSQLMapper;
import cn.enn.bigdata.dataanalysis.model.local.CalcCodeRelation;
import cn.enn.bigdata.dataanalysis.service.RelationOp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RelationOpImpl implements RelationOp {

    @Autowired
    private CalcCodeRelationSQLMapper calcCodeRelationSQLMapper;

    @Override
    public List<Long> getParent(Long id) {
        List<Long> parents = calcCodeRelationSQLMapper.getParents(id);
        return parents;
    }

    @Override
    public List<Long> getSons(Long id) {
//        List<CalcCodeRelation> relations =  calcCodeRelationSQLMapper.getCalcCodeRelation();
//        List<Long> sons = new ArrayList<>();
//        relations.forEach(x->{
//            if(x.getPid() == id) sons.add(x.getId());
//        });
        List<Long> sons = new ArrayList<>();
        sons = calcCodeRelationSQLMapper.getSons(id);

        return sons;
    }
}
