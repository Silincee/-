package main.java.com.silince.juc;

/**
 * @program: 多线程高并发
 * @description:
 * @author: Silince
 * @create: 2020-09-01 14:10
 **/
public class Person {
    private Integer id;
    private String personName;

    public Person(){}
    public Person(String personName){
        this.personName=personName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}
