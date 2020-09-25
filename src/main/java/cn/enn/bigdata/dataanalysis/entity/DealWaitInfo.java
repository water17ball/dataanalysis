package cn.enn.bigdata.dataanalysis.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@ApiModel("交易等待信息")
@Data
public class DealWaitInfo {
    @ApiModelProperty(value = "价格", example = "12.05")
    private BigDecimal price;

    @ApiModelProperty(value = "数量", example = "100")
    private Long count;
}
