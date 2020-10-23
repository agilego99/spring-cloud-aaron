package com.atguigu.principle.inversion.improve;

public class DependecyInversion {

    public static void main(String[] args) {
        //客戶端無需改變
        Person person = new Person();
        person.receive(new Email());
        person.receive(new Line());
        person.receive(new WeiXin());
    }

}

//定義接口
interface IReceiver {
    public String getInfo();
}

class Email implements IReceiver {
    public String getInfo() {

        return "電子郵件信息: hello,world";
    }
}


//增加微信
class Line implements IReceiver {
    public String getInfo() {

        return "Line: hello,ok";
    }
}


//增加微信
class WeiXin implements IReceiver {
    public String getInfo() {

        return "微信信息: hello,ok";
    }
}

//方式2
class Person {
    //這裡我們是對接口的依賴
    public void receive(IReceiver receiver) {

        System.out.println(receiver.getInfo());
    }
}
