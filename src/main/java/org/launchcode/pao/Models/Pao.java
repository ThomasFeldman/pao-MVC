package org.launchcode.pao.Models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Pao {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private int PaoNum;

    private String person;

    private String action;

    private String object;

    public Pao(String person, String action, String object) {
        this.person = person;
        this.action = action;
        this.object = object;
    }

    public Pao() {}

    public int getId() { return id; }

    public int getPaoNum() { return PaoNum; }

    public void setPaoNum(int paoNum) { PaoNum = paoNum; }

    public String getPerson() { return person; }

    public void setPerson(String person) { this.person = person; }

    public String getAction() { return action; }

    public void setAction(String action) { this.action = action; }

    public String getObject() { return object; }

    public void setObject(String object) { this.object = object; }
}
