package cn.enn.bigdata.dataanalysis.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@ApiModel("交易信息")
@Data
public class DealAction {

    @ApiModelProperty(value = "标的Id", example = "600406")
    private Long id;

    @ApiModelProperty(value = "标的数量", example = "106")
    private Long count;

    @ApiModelProperty(value = "标的价格", example = "12.5")
    private BigDecimal price;
}
