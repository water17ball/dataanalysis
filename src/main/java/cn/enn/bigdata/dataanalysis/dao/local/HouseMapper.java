package cn.enn.bigdata.dataanalysis.dao.local;

import cn.enn.bigdata.dataanalysis.model.local.House;
import cn.enn.bigdata.dataanalysis.model.local.HouseExample;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface HouseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    long countByExample(HouseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    int deleteByExample(HouseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    @Delete({
            "delete from house",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    @Insert({
            "insert into house (city, picture, ",
            "bed, location, price, ",
            "discount_price, good_remark)",
            "values (#{city,jdbcType=VARCHAR}, #{picture,jdbcType=VARCHAR}, ",
            "#{bed,jdbcType=TINYINT}, #{location,jdbcType=VARCHAR}, #{price,jdbcType=SMALLINT}, ",
            "#{discountPrice,jdbcType=SMALLINT}, #{goodRemark,jdbcType=SMALLINT})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(House record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    int insertSelective(House record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    List<House> selectByExample(HouseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    @Select({
            "select",
            "id, city, picture, bed, location, price, discount_price, good_remark",
            "from house",
            "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("HouseMapper.BaseResultMap")
    House selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    int updateByExampleSelective(@Param("record") House record, @Param("example") HouseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    int updateByExample(@Param("record") House record, @Param("example") HouseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    int updateByPrimaryKeySelective(House record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table house
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    @Update({
            "update house",
            "set city = #{city,jdbcType=VARCHAR},",
            "picture = #{picture,jdbcType=VARCHAR},",
            "bed = #{bed,jdbcType=TINYINT},",
            "location = #{location,jdbcType=VARCHAR},",
            "price = #{price,jdbcType=SMALLINT},",
            "discount_price = #{discountPrice,jdbcType=SMALLINT},",
            "good_remark = #{goodRemark,jdbcType=SMALLINT}",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(House record);
}