package cn.wp.bigdata.dataanalysis.service.impl;

import cn.wp.bigdata.dataanalysis.dao.exchange.DealInfoMapper;
import cn.wp.bigdata.dataanalysis.dao.exchange.OperInfoMapper;
import cn.wp.bigdata.dataanalysis.dao.exchange.StockInfoMapper;
import cn.wp.bigdata.dataanalysis.entity.DealWaitInfo;
import cn.wp.bigdata.dataanalysis.entity.ExchangeConstant;
import cn.wp.bigdata.dataanalysis.entity.StockExchangeInfo;
import cn.wp.bigdata.dataanalysis.model.exchange.OperInfo;
import cn.wp.bigdata.dataanalysis.model.exchange.StockInfo;
import cn.wp.bigdata.dataanalysis.model.exchange.StockInfoExample;
import cn.wp.bigdata.dataanalysis.service.StockServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
@Service
//@ConditionalOnBean(SqlSessionTemplate.class)
//@Order(2)
//@Scope(name = "singleton", description = "singleton")
public class StockServerImpl implements StockServer {

    @Autowired
    private StockInfoMapper stockInfoMapper;

    @Autowired
    private DealInfoMapper dealInfoMapper;

    @Autowired
    private OperInfoMapper operInfoMapper;

    //是否初始化
    private volatile boolean isInitial;

    private HashMap<Long, StockInfo> stockInfos;


    //股票标的信息
    //卖信息
    private ConcurrentHashMap<Long, StockExchangeInfo> buyStockMap;
    //买信息
    private ConcurrentHashMap<Long, StockExchangeInfo> sellStockMap;

    StockServerImpl() {
        isInitial = false;
        stockInfos = new HashMap<>();
        buyStockMap = new ConcurrentHashMap<>();
        sellStockMap = new ConcurrentHashMap<>();
    }

    /**
     * 交易服务初始化
     */
    @PostConstruct
    public void init() {
        System.out.println("service [stockServer] init-------------------------------------------------->>> ");
        if (!isInitial) {
            StockInfoExample stockExample = new StockInfoExample();
            stockExample.createCriteria().andIsDelNotEqualTo("1");
            List<StockInfo> infoList = stockInfoMapper.selectByExample(stockExample);
            if ((infoList == null) || (infoList.size() <= 0)) {
                log.error("StockServer init Error!  StockInfo is empty! ");
                return;
            }

            //填充基本信息
            for (StockInfo stockInfo : infoList) {
                stockInfos.put(stockInfo.getId(), stockInfo);
                buyStockMap.put(stockInfo.getId(), new StockExchangeInfo(stockInfo, ExchangeConstant.OPERATION_TYPE_BUY));
                sellStockMap.put(stockInfo.getId(), new StockExchangeInfo(stockInfo, ExchangeConstant.OPERATION_TYPE_SELL));
            }

            isInitial = true;
        }

    }

    //操作方法
    public Boolean buy(OperInfo operInfo) {

        if (!operInfo.getOperType().equals(ExchangeConstant.OPERATION_TYPE_BUY)) {
            log.error("buy Error!  OperType[{}] is wrong", operInfo.getOperType());
            return false;
        }

        StockExchangeInfo stockExchangeInfo = sellStockMap.get(operInfo.getStockId());
        if (stockExchangeInfo == null) {
            log.error("buy Error!  stock_id[{}] is not found", operInfo.getStockId());
            return false;
        }
        //加锁 卖出队列，有符合成交范围的就成交。
        synchronized (stockExchangeInfo) {
            if (stockExchangeInfo.getCount() > 0) {
                if (stockExchangeInfo.getMinPrice().compareTo(operInfo.getPrice()) <= 0) {//有可成交部分
                    ArrayList<DealWaitInfo> orderedLink = stockExchangeInfo.getOrderedLink();
                    if (orderedLink != null) {
                        for (DealWaitInfo dealWaitInfo : orderedLink) {
                            if (dealWaitInfo.getPrice().compareTo(operInfo.getPrice()) <= 0) {

                            }
                        }
                    }
                }
            } else {//没有可成交部分，直接挂到buy队列中,同时排序

            }

        }
        return true;
    }

    public Boolean sell(OperInfo operInfo) {

        return true;
    }

    //信息查询方法

    //查询某个标的交易信息
    public StockExchangeInfo getBuy(Long id) {
        return this.buyStockMap.get(id);
    }

    //查询某个标的交易信息
    public StockExchangeInfo getSell(Long id) {
        return this.sellStockMap.get(id);
    }


}
