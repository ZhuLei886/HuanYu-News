/*
 	��ʽ���������⣺
 		�������ͣ���ʽ�����ĸı䲻Ӱ��ʵ�ʲ���
 		�������ͣ���ʽ�����ĸı�ֱ��Ӱ��ʵ�ʲ���
 */

package ����;
//��ʽ�����ǻ�������
class Demo{
	public int sum(int a,int b) {
		return a+b;
	}
}

//��ʽ��������������
class student{
	public void show() {
		System.out.println("�Ұ�ѧϰ");
	}
}

class studentDemo{
	//����㿴����һ����������ʽ������һ�������ͣ��������ͣ���������ʵ��Ҫ���Ǹ���Ķ���
	public void method(student s) {//���õ�ʱ�򣬰�main�����е�s�ĵ�ַ���ݵ������� student s=new student();k,
		s.show();
	}
}

class ArgsTest {
	public static void main(String[] args) {
		//��ʽ�����ǻ������͵ĵ���
		Demo d=new Demo();
		int result=d.sum(10,20);
		System.out.println(result);
		System.out.println(".....................");
		
		//��ʽ�������������͵ĵ���
		//������Ҫ����studentDemo���е�method��������
		studentDemo sd=new studentDemo();
		//����ѧ������
		student s=new student();
		sd.method(s);//��s�ĵ�ַ����������
		
	}
}
