package com.example.ispy;

public class childRegisterDB
{
    private String name;
    private String mob;
    private String email;
    private String  pass;
    public childRegisterDB(){
    }

    public childRegisterDB(String name, String mob, String email, String pass) {
        this.name = name;
        this.mob = mob;
        this.email = email;
        this.pass = pass;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
