package com.atguigu.principle.singleresponsibility;

public class SingleResponsibility3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vehicle2 vehicle2  = new Vehicle2();
		vehicle2.run("��܇");
		vehicle2.runAir("�w�C");
		vehicle2.runWater("݆��");
	}
}


//��ʽ3�ķ���
//1. �@�N�޸ķ����]�Ќ�ԭ����������޸ģ�ֻ�����ӷ���
//2. �@�e�mȻ�]������@�����e�����؆�һ؟ԭ�t�������ڷ������e�ϣ���Ȼ�����؆�һ؟
class Vehicle2 {
	public void run(String vehicle) {
		System.out.println(vehicle + " �ڹ�·���\��....");
	}

	public void runAir(String vehicle) {
		System.out.println(vehicle + " ��������\��....");
	}
	
	public void runWater(String vehicle) {
		System.out.println(vehicle + " ��ˮ����....");
	}
}
