package org.launchcode.pao.Models;


import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


@Entity
public class Pao {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String PaoNum;

    private String person;

    private String action;

    private String object;

//    @OneToOne
//    private User user;

    public Pao(String PaoNum, String person, String action, String object) {
        this.PaoNum = PaoNum;
        this.person = person;
        this.action = action;
        this.object = object;
    }

    public Pao() {}

    public int getId() { return id; }

    public String getPaoNum() { return PaoNum; }

    public void setPaoNum(String paoNum) { PaoNum = paoNum; }

    public String getPerson() { return person; }

    public void setPerson(String person) { this.person = person; }

    public String getAction() { return action; }

    public void setAction(String action) { this.action = action; }

    public String getObject() { return object; }

    public void setObject(String object) { this.object = object; }


    public void getAllClassAndFields() throws InvocationTargetException, IllegalAccessException {
        Pao pao = new Pao();
        Method[] methods = pao.getClass().getDeclaredMethods();
        for (Method method : methods) {
            Object object = method.invoke(pao, new Object[]{});
            System.out.println("Class Name = " + object.getClass().getName());
            printFields(object);
        }
    }

    public static void printFields(Object obj) {
        Field[] fields = obj.getClass().getFields();
        for (Field field : fields) {
            System.out.println("Field Name = " + field.getName());
        }
    }
}
