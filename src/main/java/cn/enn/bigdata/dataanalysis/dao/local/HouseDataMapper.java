package cn.enn.bigdata.dataanalysis.dao.local;

import cn.enn.bigdata.dataanalysis.model.local.House;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HouseDataMapper {
    @Select("select * from househot")
    @Results({ @Result(property = "discountPrice", column = "discount_price", javaType = Short.class),
            @Result(property = "goodRemark", column = "good_remark", javaType= Short.class) })
    public List<House> selectAll();
}
