package cn.enn.bigdata.dataanalysis.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("通用restApi返回结果")
@Data
public class ApiResultDto<E> {

    @ApiModelProperty("错误代码")
    private Integer code;
    @ApiModelProperty("错误信息")
    private String message;
    @ApiModelProperty("返回结果")
    private E data;

    public ApiResultDto() {
        this.code = 200;
        this.message = "执行成功";
    }
}
