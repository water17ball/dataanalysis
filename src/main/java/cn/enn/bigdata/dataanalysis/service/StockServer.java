package cn.enn.bigdata.dataanalysis.service;

import cn.enn.bigdata.dataanalysis.entity.StockExchangeInfo;
import cn.enn.bigdata.dataanalysis.model.exchange.OperInfo;

public interface StockServer {
    public void init();

    public Boolean buy(OperInfo operInfo);

    public Boolean sell(OperInfo operInfo);

    public StockExchangeInfo getBuy(Long id);

    public StockExchangeInfo getSell(Long id);
}
