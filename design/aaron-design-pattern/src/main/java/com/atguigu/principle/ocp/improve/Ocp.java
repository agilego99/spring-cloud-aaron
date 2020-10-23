package com.atguigu.principle.ocp.improve;

public class Ocp {

	public static void main(String[] args) {
		//使用看看存在的問題
		GraphicEditor graphicEditor = new GraphicEditor();
		graphicEditor.drawShape(new Rectangle());
		graphicEditor.drawShape(new Circle());
		graphicEditor.drawShape(new Triangle());
		graphicEditor.drawShape(new OtherGraphic());
	}

}

//這是一個用於繪圖的類 [使用方]
class GraphicEditor {
	//接收Shape對象，調用draw方法
	public void drawShape(Shape s) {
		s.draw();
	}


}

//Shape類，基類
abstract class Shape {
	int m_type;

	public abstract void draw();//抽象方法
}

class Rectangle extends Shape {
	Rectangle() {
		super.m_type = 1;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println(" 繪制矩形 ");
	}
}

class Circle extends Shape {
	Circle() {
		super.m_type = 2;
	}
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println(" 繪制圓形 ");
	}
}

//新增畫三角形
class Triangle extends Shape {
	Triangle() {
		super.m_type = 3;
	}
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println(" 繪制三角形 ");
	}
}

//新增一個圖形
class OtherGraphic extends Shape {
	OtherGraphic() {
		super.m_type = 4;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println(" 繪制其它圖形 ");
	}
}
