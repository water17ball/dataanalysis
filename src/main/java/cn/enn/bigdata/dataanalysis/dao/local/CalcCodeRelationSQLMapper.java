package cn.enn.bigdata.dataanalysis.dao.local;

import cn.enn.bigdata.dataanalysis.model.local.CalcCodeRelation;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CalcCodeRelationSQLMapper {
    @Select("select * from calc_code_relation")
    public List<CalcCodeRelation> getCalcCodeRelation();

    @Select("select pid from calc_code_relation where id=#{id}")
    public List<Long> getParents(@Param("id") Long id);

    @Select("select id from calc_code_relation where pid=#{pid}")
    public List<Long> getSons(@Param("pid") Long pid);


}
