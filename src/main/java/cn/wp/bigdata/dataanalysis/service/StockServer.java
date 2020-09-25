package cn.wp.bigdata.dataanalysis.service;

import cn.wp.bigdata.dataanalysis.entity.StockExchangeInfo;
import cn.wp.bigdata.dataanalysis.model.exchange.OperInfo;

public interface StockServer {
    public void init();

    public Boolean buy(OperInfo operInfo);

    public Boolean sell(OperInfo operInfo);

    public StockExchangeInfo getBuy(Long id);

    public StockExchangeInfo getSell(Long id);
}
