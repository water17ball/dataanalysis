package cn.wp.bigdata.dataanalysis.entity;

import cn.wp.bigdata.dataanalysis.model.local.House;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class HouseResult {
    @ApiModelProperty(value = "城市", example = "beijing")
    private String city;

    @ApiModelProperty(value = "房屋")
    private List<House> house;
}
