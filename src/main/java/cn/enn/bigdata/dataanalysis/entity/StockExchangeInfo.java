package cn.enn.bigdata.dataanalysis.entity;

import cn.enn.bigdata.dataanalysis.model.exchange.StockInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;

@ApiModel("stock信息")
@Data
public class StockExchangeInfo extends StockInfo {

    @ApiModelProperty(value = "最低价格")
    private BigDecimal minPrice;
    @ApiModelProperty(value = "最高价格")
    private BigDecimal maxPrice;
    @ApiModelProperty(value = "交易总数量")
    private Long count;

    @ApiModelProperty(value = "交易类型  1：买， 2：卖")
    private String operType;

    @ApiModelProperty(value = "交易队列.buy队列按照价格降序排列， sell队列按照升序排列。方便从头就可以开始交易。")
    private ArrayList<DealWaitInfo> OrderedLink;


    public StockExchangeInfo(StockInfo stock, String operTypeStr) {
        this.setId(stock.getId());
        this.setCode(stock.getCode());
        this.setName(stock.getName());
        this.setDes(stock.getDes());
        this.setIsDel(stock.getIsDel());

        this.setOperType(operTypeStr);
        this.setOrderedLink(new ArrayList<>());

        this.setCount(0L);
    }

}
