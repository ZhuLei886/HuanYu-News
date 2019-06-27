/*
 	形式参数的问题：
 		基本类型：形式参数的改变不影响实际参数
 		引用类型：形式参数的改变直接影响实际参数
 */

package 对象;
//形式参数是基本类型
class Demo{
	public int sum(int a,int b) {
		return a+b;
	}
}

//形式参数是引用类型
class student{
	public void show() {
		System.out.println("我爱学习");
	}
}

class studentDemo{
	//如果你看到了一个方法的形式参数是一个类类型（引用类型），这里其实需要的是该类的对象
	public void method(student s) {//调用的时候，把main方法中的s的地址传递到了这里 student s=new student();k,
		s.show();
	}
}

class ArgsTest {
	public static void main(String[] args) {
		//形式参数是基本类型的调用
		Demo d=new Demo();
		int result=d.sum(10,20);
		System.out.println(result);
		System.out.println(".....................");
		
		//形式参数是引用类型的调用
		//需求：我要调用studentDemo类中的method（）方法
		studentDemo sd=new studentDemo();
		//创建学生对象
		student s=new student();
		sd.method(s);//把s的地址给到了这里
		
	}
}
