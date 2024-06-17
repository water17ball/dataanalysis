package cn.enn.bigdata.dataanalysis.designpattern.prototype;

import java.io.Serializable;

public class Attachment implements Serializable {
    public void download(){
        System.out.println("下载附件" );
    }
}
