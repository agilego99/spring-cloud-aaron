package com.atguigu.principle.singleresponsibility;

/**
 * ���x��һ���ԓֻ��һ����׃��ԭ��(�R��.����)
 */
public class SingleResponsibility1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vehicle vehicle = new Vehicle();
		vehicle.run("Ħ��܇");
		vehicle.run("��܇");
		vehicle.run("�w�C");
	}
}

// ��ͨ�����
// ��ʽ1
// 1. �ڷ�ʽ1 ��run�����У��`���ˆ�һ؟ԭ�t
// 2. ��Q�ķ����ǳ��ĺ��Σ�������ͨ�����\�з�����ͬ���ֽ�ɲ�ͬ���
class Vehicle {
	public void run(String vehicle) {
		System.out.println(vehicle + " �ڹ�·���\��....");
	}
}
