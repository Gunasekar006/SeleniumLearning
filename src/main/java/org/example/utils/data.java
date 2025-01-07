package org.example.utils;

import java.util.Random;

public class data {
    private String userNmae;
    private String password;


    public String getUserNmae() {
        return userNmae;
    }

    public void setUserNmae(String userNmae) {
        Random rand = new Random();
        int rand_int1 = rand.nextInt(1000);
        this.userNmae = "gunasekar006" + rand_int1 + "@gmail.com";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = "Welcome@1234";
    }
}
