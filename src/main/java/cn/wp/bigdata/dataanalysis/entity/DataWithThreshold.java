package cn.wp.bigdata.dataanalysis.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.util.List;

@Data
@ApiOperation(value = "带阈值的数据")
public class DataWithThreshold {
    @ApiModelProperty(value = "阈值", example = "10")
    Double threshold;
    @ApiModelProperty(value = "数据")
    List<Double> data;
}

