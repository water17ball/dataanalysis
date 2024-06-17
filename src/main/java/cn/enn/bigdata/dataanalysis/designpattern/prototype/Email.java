package cn.enn.bigdata.dataanalysis.designpattern.prototype;

import java.io.*;

public class Email implements Cloneable, Serializable {
    private Attachment attachment;
    Email(){
        attachment = new Attachment();
    }

    public Attachment getAttachment(){
        return this.attachment;
    }

    public void display(){
        System.out.println("查看邮件");
    }

    /**
     * 深拷贝，就是将成员变量的内容也一模一样（但是地址不一样，另外一份）
     * @return
     */
    public Object deepClone(){

        /**
         * 1.深拷贝最简单的方式就是序列化另外一份，再反序列化回来。
         */
        try {
            //将对象写入流中
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(this);


            //将对象从流中取出
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object readObject = objectInputStream.readObject();
            return readObject;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        /**
         * 2.自定义的深拷贝，就是给各个变量值，新建一份内容一样的。new
         * 但是这种方法比较繁琐，对每个成员变量都需要new出来一个。
         */
        return null;
    }


    public static void main(String[] args) {
        Email email = new Email();
        try {
            /**
             * 浅拷贝：对象不一样，但是成员变量指向的地址都一样
             */
            System.out.println("----------浅拷贝-----------");
            Email clone = (Email) email.clone();
            System.out.println(email == clone);
            System.out.println(email.attachment == clone.attachment);

            System.out.println("----------深拷贝-----------");
            Email deepClone = (Email) email.deepClone();
            System.out.println(email == deepClone);
            System.out.println(email.attachment == deepClone.attachment);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}