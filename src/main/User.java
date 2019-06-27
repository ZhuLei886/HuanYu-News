package cn.itcast.bean;

/**
 * @ProjectName: testShopping
 * @Package: cn.itcast.bean
 * @ClassName: User
 * @Description: java类作用描述
 * @Author: 唐朝
 * @CreateDate: 2018/8/6 20:51
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/6 20:51
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */

public class User {
    private String name;
    private Integer age;
    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User(){
        System.out.println( "这是空参构造方法!!" );
    }

    public User(String name, Car car) {
        this.name = name;
        this.car = car;
        System.out.println( "(String name, Car car)!!" );
    }
    public User(Car car, String name) {
        this.name = name;
        this.car = car;
        System.out.println( "(Car car, String name)!!" );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void init(){
        System.out.println( "初始方法" );
    }
    public void destroy(){
        System.out.println( "销毁方法" );
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", car=" + car +
                '}';
    }
}
