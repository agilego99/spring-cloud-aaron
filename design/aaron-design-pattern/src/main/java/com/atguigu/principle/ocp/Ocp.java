package com.atguigu.principle.ocp;

public class Ocp {

	public static void main(String[] args) {
		//ʹ�ÿ������ڵĆ��}
		GraphicEditor graphicEditor = new GraphicEditor();
		graphicEditor.drawShape(new Rectangle());
		graphicEditor.drawShape(new Circle());
		graphicEditor.drawShape(new Triangle());
	}

}

//�@��һ������L�D��� [ʹ�÷�]
class GraphicEditor {
	//����Shape����Ȼ�����type�����L�Ʋ�ͬ�ĈD��
	public void drawShape(Shape s) {
		if (s.m_type == 1)
			drawRectangle(s);
		else if (s.m_type == 2)
			drawCircle(s);
		else if (s.m_type == 3)
			drawTriangle(s);
	}

	//�L�ƾ���
	public void drawRectangle(Shape r) {

		System.out.println(" �L�ƾ��� ");
	}

	//�L�ƈA��
	public void drawCircle(Shape r) {

		System.out.println(" �L�ƈA�� ");
	}

	//�L��������
	public void drawTriangle(Shape r) {

		System.out.println(" �L�������� ");
	}
}

//Shape����
class Shape {
	int m_type;
}

class Rectangle extends Shape {
	Rectangle() {
		super.m_type = 1;
	}
}

class Circle extends Shape {
	Circle() {
		super.m_type = 2;
	}
}

//������������
class Triangle extends Shape {
	Triangle() {
		super.m_type = 3;
	}
}
