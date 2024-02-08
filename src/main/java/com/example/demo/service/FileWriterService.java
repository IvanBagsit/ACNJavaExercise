package com.example.demo.service;

import java.io.IOException;
import java.util.ArrayList;

public interface FileWriterService {
    record Person(String firstName, int age) {
    }

    ArrayList<Person> createFile(ArrayList<Person> personList) throws IOException;
}
