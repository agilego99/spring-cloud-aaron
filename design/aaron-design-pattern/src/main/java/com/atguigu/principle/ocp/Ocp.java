package com.atguigu.principle.ocp;

public class Ocp {

	public static void main(String[] args) {
		//使用看看存在的問題
		GraphicEditor graphicEditor = new GraphicEditor();
		graphicEditor.drawShape(new Rectangle());
		graphicEditor.drawShape(new Circle());
		graphicEditor.drawShape(new Triangle());
	}

}

//這是一個用於繪圖的類 [使用方]
class GraphicEditor {
	//接收Shape對象，然後根據type，來繪制不同的圖形
	public void drawShape(Shape s) {
		if (s.m_type == 1)
			drawRectangle(s);
		else if (s.m_type == 2)
			drawCircle(s);
		else if (s.m_type == 3)
			drawTriangle(s);
	}

	//繪制矩形
	public void drawRectangle(Shape r) {

		System.out.println(" 繪制矩形 ");
	}

	//繪制圓形
	public void drawCircle(Shape r) {

		System.out.println(" 繪制圓形 ");
	}

	//繪制三角形
	public void drawTriangle(Shape r) {

		System.out.println(" 繪制三角形 ");
	}
}

//Shape類，基類
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

//新增畫三角形
class Triangle extends Shape {
	Triangle() {
		super.m_type = 3;
	}
}
