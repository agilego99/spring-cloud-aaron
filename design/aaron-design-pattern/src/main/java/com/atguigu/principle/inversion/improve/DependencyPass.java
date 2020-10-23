package com.atguigu.principle.inversion.improve;

public class DependencyPass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChangHong changHong = new ChangHong();
//		OpenAndClose openAndClose = new OpenAndClose();
//		openAndClose.open(changHong);
		
		//ͨ�^�������M����ه���f
//		OpenAndClose openAndClose = new OpenAndClose(changHong);
//		openAndClose.open();
		//ͨ�^setter�����M����ه���f
		OpenAndClose openAndClose = new OpenAndClose();
		openAndClose.setTv(changHong);
		openAndClose.open();

	}

}

// ��ʽ1�� ͨ�^�ӿڂ��f���F��ه
// �_�P�Ľӿ�
// interface IOpenAndClose {
// public void open(ITV tv); //���󷽷�,���սӿ�
// }
//
// interface ITV { //ITV�ӿ�
// public void play();
// }
//
// class ChangHong implements ITV {
//
//	@Override
//	public void play() {
//		// TODO Auto-generated method stub
//		System.out.println("�L���ҕ�C�����_");
//	}
//
// }
//// ���F�ӿ�
// class OpenAndClose implements IOpenAndClose{
// public void open(ITV tv){
// tv.play();
// }
// }

// ��ʽ2: ͨ�^���췽����ه���f
// interface IOpenAndClose {
// public void open(); //���󷽷�
// }
// interface ITV { //ITV�ӿ�
// public void play();
// }
// class OpenAndClose implements IOpenAndClose{
// public ITV tv; //�ɆT
// public OpenAndClose(ITV tv){ //������
// this.tv = tv;
// }
// public void open(){
// this.tv.play();
// }
// }


// ��ʽ3 , ͨ�^setter�������f
interface IOpenAndClose {
	public void open(); // ���󷽷�

	public void setTv(ITV tv);
}

interface ITV { // ITV�ӿ�
	public void play();
}

class OpenAndClose implements IOpenAndClose {
	private ITV tv;

	public void setTv(ITV tv) {
		this.tv = tv;
	}

	public void open() {
		this.tv.play();
	}
}

class ChangHong implements ITV {

	@Override
	public void play() {
		// TODO Auto-generated method stub
		System.out.println("�L���ҕ�C�����_");
	}
}