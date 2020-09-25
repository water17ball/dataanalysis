package cn.enn.bigdata.dataanalysis.dao.exchange;

import cn.enn.bigdata.dataanalysis.model.exchange.OperInfo;
import cn.enn.bigdata.dataanalysis.model.exchange.OperInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface OperInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oper_info
     *
     * @mbg.generated Tue Dec 17 15:21:54 CST 2019
     */
    long countByExample(OperInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oper_info
     *
     * @mbg.generated Tue Dec 17 15:21:54 CST 2019
     */
    int deleteByExample(OperInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oper_info
     *
     * @mbg.generated Tue Dec 17 15:21:54 CST 2019
     */
    @Delete({
        "delete from oper_info",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oper_info
     *
     * @mbg.generated Tue Dec 17 15:21:54 CST 2019
     */
    @Insert({
        "insert into oper_info (stock_id, oper_user, ",
        "oper_type, count, price, ",
        "biz_time)",
        "values (#{stockId,jdbcType=BIGINT}, #{operUser,jdbcType=BIGINT}, ",
        "#{operType,jdbcType=CHAR}, #{count,jdbcType=BIGINT}, #{price,jdbcType=DECIMAL}, ",
        "#{bizTime,jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(OperInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oper_info
     *
     * @mbg.generated Tue Dec 17 15:21:54 CST 2019
     */
    int insertSelective(OperInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oper_info
     *
     * @mbg.generated Tue Dec 17 15:21:54 CST 2019
     */
    List<OperInfo> selectByExample(OperInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oper_info
     *
     * @mbg.generated Tue Dec 17 15:21:54 CST 2019
     */
    @Select({
        "select",
        "id, stock_id, oper_user, oper_type, count, price, biz_time",
        "from oper_info",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("cn.enn.bigdata.dataanalysis.dao.exchange.OperInfoMapper.BaseResultMap")
    OperInfo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oper_info
     *
     * @mbg.generated Tue Dec 17 15:21:54 CST 2019
     */
    int updateByExampleSelective(@Param("record") OperInfo record, @Param("example") OperInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oper_info
     *
     * @mbg.generated Tue Dec 17 15:21:54 CST 2019
     */
    int updateByExample(@Param("record") OperInfo record, @Param("example") OperInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oper_info
     *
     * @mbg.generated Tue Dec 17 15:21:54 CST 2019
     */
    int updateByPrimaryKeySelective(OperInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oper_info
     *
     * @mbg.generated Tue Dec 17 15:21:54 CST 2019
     */
    @Update({
        "update oper_info",
        "set stock_id = #{stockId,jdbcType=BIGINT},",
          "oper_user = #{operUser,jdbcType=BIGINT},",
          "oper_type = #{operType,jdbcType=CHAR},",
          "count = #{count,jdbcType=BIGINT},",
          "price = #{price,jdbcType=DECIMAL},",
          "biz_time = #{bizTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(OperInfo record);
}