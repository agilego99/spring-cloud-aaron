package com.atguigu.principle.singleresponsibility;

public class SingleResponsibility2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RoadVehicle roadVehicle = new RoadVehicle();
		roadVehicle.run("Ħ��܇");
		roadVehicle.run("��܇");

		AirVehicle airVehicle = new AirVehicle();
		airVehicle.run("�w�C");

		WaterVehicle waterVehicle = new WaterVehicle();
		waterVehicle.run("݆��");
	}

}

//����2�ķ���
//1. ���؆�һ؟ԭ�t
//2. �����@�����ĸĄӺܴ󣬼���ֽ⣬ͬ�r�޸Ŀ͑���
//3. ���M��ֱ���޸�Vehicle ��ĄӵĴ��a�����^��=>����3

class RoadVehicle {
	public void run(String vehicle) {
		System.out.println(vehicle + "�ڹ�·�\��");
	}
}

class AirVehicle {
	public void run(String vehicle) {
		System.out.println(vehicle + "������\��");
	}
}

class WaterVehicle {
	public void run(String vehicle) {
		System.out.println(vehicle + "��ˮ���\��");
	}
}