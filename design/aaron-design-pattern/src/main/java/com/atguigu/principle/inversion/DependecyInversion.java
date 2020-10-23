package com.atguigu.principle.inversion;

public class DependecyInversion {

	public static void main(String[] args) {
		Person person = new Person();
		person.receive(new Email());
	}

}


class Email {
	public String getInfo() {
		return "電子郵件信息: hello,world";
	}
}


//完成Person接收消息的功能
//方式1分析
//1. 簡單，比較容易想到
//2. 如果我們獲取的對象是 微信，短信等等，則新增類，同時Perons也要增加相應的接收方法
//3. 解決思路：引入一個抽象的接口IReceiver, 表示接收者, 這樣Person類與接口IReceiver發生依賴
//   因為Email, WeiXin 等等屬於接收的範圍，他們各自實現IReceiver 接口就ok, 這樣我們就符號依賴倒轉原則
class Person {
	public void receive(Email email ) {

		System.out.println(email.getInfo());
	}
}
