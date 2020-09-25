package cn.enn.bigdata.dataanalysis.service.impl;

import cn.enn.bigdata.dataanalysis.dao.local.HouseDataMapper;
import cn.enn.bigdata.dataanalysis.model.local.House;
import cn.enn.bigdata.dataanalysis.entity.HouseResult;
import cn.enn.bigdata.dataanalysis.service.HouseService;
import cn.enn.bigdata.dataanalysis.service.StockServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseDataMapper houseMapper;

    @Autowired
    private StockServer stockServer;

    @Override
    public List<HouseResult> getAllHouse() {
        List<HouseResult> houseResultList = new ArrayList<>();
        Map<String, List<House>> houseMap = new HashMap<>();
        List<House> houses = houseMapper.selectAll();
        houses.forEach(x->{
            List<House> houselist = houseMap.get(x.getCity());
            if(houselist != null){
                houselist.add(x);
            }
            else{
                houselist = new ArrayList<>();
                houselist.add(x);
                houseMap.put(x.getCity(), houselist);
            }
        });

        for(Map.Entry entry: houseMap.entrySet()){
            HouseResult house = new HouseResult();
            house.setCity((String) entry.getKey());
            house.setHouse((List<House>) entry.getValue());
            houseResultList.add(house);
        }
        return houseResultList;
    }
}
