package cn.enn.bigdata.dataanalysis.dao.exchange;

import cn.enn.bigdata.dataanalysis.model.exchange.StockInfoExample;
import cn.enn.bigdata.dataanalysis.model.exchange.StockInfo;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface StockInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_info
     *
     * @mbg.generated Tue Dec 17 15:21:54 CST 2019
     */
    long countByExample(StockInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_info
     *
     * @mbg.generated Tue Dec 17 15:21:54 CST 2019
     */
    int deleteByExample(StockInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_info
     *
     * @mbg.generated Tue Dec 17 15:21:54 CST 2019
     */
    @Delete({
        "delete from stock_info",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_info
     *
     * @mbg.generated Tue Dec 17 15:21:54 CST 2019
     */
    @Insert({
        "insert into stock_info (code, name, ",
        "des, is_del)",
        "values (#{code,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{des,jdbcType=VARCHAR}, #{isDel,jdbcType=CHAR})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(StockInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_info
     *
     * @mbg.generated Tue Dec 17 15:21:54 CST 2019
     */
    int insertSelective(StockInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_info
     *
     * @mbg.generated Tue Dec 17 15:21:54 CST 2019
     */
    List<StockInfo> selectByExample(StockInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_info
     *
     * @mbg.generated Tue Dec 17 15:21:54 CST 2019
     */
    @Select({
        "select",
        "id, code, name, des, is_del",
        "from stock_info",
        "where id = #{id,jdbcType=BIGINT}"
    })
//    @ResultMap("StockInfoMapper.BaseResultMap")
    StockInfo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_info
     *
     * @mbg.generated Tue Dec 17 15:21:54 CST 2019
     */
    int updateByExampleSelective(@Param("record") StockInfo record, @Param("example") StockInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_info
     *
     * @mbg.generated Tue Dec 17 15:21:54 CST 2019
     */
    int updateByExample(@Param("record") StockInfo record, @Param("example") StockInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_info
     *
     * @mbg.generated Tue Dec 17 15:21:54 CST 2019
     */
    int updateByPrimaryKeySelective(StockInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_info
     *
     * @mbg.generated Tue Dec 17 15:21:54 CST 2019
     */
    @Update({
        "update stock_info",
        "set code = #{code,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "des = #{des,jdbcType=VARCHAR},",
          "is_del = #{isDel,jdbcType=CHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(StockInfo record);
}