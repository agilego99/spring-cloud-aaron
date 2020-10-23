package com.atguigu.principle.inversion;

public class DependecyInversion {

	public static void main(String[] args) {
		Person person = new Person();
		person.receive(new Email());
	}

}


class Email {
	public String getInfo() {
		return "����]����Ϣ: hello,world";
	}
}


//���Person������Ϣ�Ĺ���
//��ʽ1����
//1. ���Σ����^�����뵽
//2. ����҂��@ȡ�Č����� ΢�ţ����ŵȵȣ��t�����ͬ�rPeronsҲҪ���������Ľ��շ���
//3. ��Q˼·������һ������Ľӿ�IReceiver, ��ʾ������, �@��Person��c�ӿ�IReceiver�l����ه
//   ���Email, WeiXin �ȵȌ�춽��յĹ������������Ԍ��FIReceiver �ӿھ�ok, �@���҂��ͷ�̖��ه���Dԭ�t
class Person {
	public void receive(Email email ) {

		System.out.println(email.getInfo());
	}
}
