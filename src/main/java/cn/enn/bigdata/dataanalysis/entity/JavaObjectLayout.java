package cn.enn.bigdata.dataanalysis.entity;

import lombok.Data;
//import org.openjdk.jol.info.ClassLayout;

@Data
public class JavaObjectLayout {
    public static void main(String[] args) {
        JavaObjectLayout objectLayout = new JavaObjectLayout();
        synchronized (objectLayout){


//            System.out.println(ClassLayOut);
        }
    }
}
