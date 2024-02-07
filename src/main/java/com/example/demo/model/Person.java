package com.example.demo.model;

import lombok.Data;

@Data
public class Person {

    // sonarlint: remove public and use getters and setters
    String firstName;
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    int age;
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public Person(String firstName, int age) {
        this.firstName = firstName;
        this.age = age;
    }
}
