package com.atguigu.principle.inversion.improve;

public class DependecyInversion {

    public static void main(String[] args) {
        //�͑��˟o���׃
        Person person = new Person();
        person.receive(new Email());
        person.receive(new Line());
        person.receive(new WeiXin());
    }

}

//���x�ӿ�
interface IReceiver {
    public String getInfo();
}

class Email implements IReceiver {
    public String getInfo() {

        return "����]����Ϣ: hello,world";
    }
}


//����΢��
class Line implements IReceiver {
    public String getInfo() {

        return "Line: hello,ok";
    }
}


//����΢��
class WeiXin implements IReceiver {
    public String getInfo() {

        return "΢����Ϣ: hello,ok";
    }
}

//��ʽ2
class Person {
    //�@�e�҂��ǌ��ӿڵ���ه
    public void receive(IReceiver receiver) {

        System.out.println(receiver.getInfo());
    }
}
