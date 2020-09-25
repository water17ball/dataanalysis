package cn.wp.bigdata.dataanalysis.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

public class InfoFactory {
    public static Info getInstance(){
        ServiceLoader<Info> serviceLoader = ServiceLoader.load(Info.class);
        Iterator<Info> infoIterable =  serviceLoader.iterator();

        return infoIterable.next();
    }
}
