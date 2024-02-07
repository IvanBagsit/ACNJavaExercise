package com.example.demo.service.impl;

import com.example.demo.model.Person;
import com.example.demo.service.FileWriterService;
import com.opencsv.CSVWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FileWriterServiceImpl implements FileWriterService {

    // all files must be stored in a configurable folder
    @Value("${file.name}")
    private String fileName;


    @Value("${file.directory}")
    private String directory;

    @Value("${file.age}")
    private int age;

    @Override
    public void createFile() throws IOException {
        Person person1 = new Person("Mary", 21);
        Person person2 = new Person("Alina", 22);
        Person person3 = new Person("John", 23);
        Person person4 = new Person("Nicole", 24);
        Person person5 = new Person("Mike", 25);

        // adapt the code to receive this list either from stdin or as a static list
        ArrayList<Person> personArrayList = new ArrayList<>();
        personArrayList.add(person1);
        personArrayList.add(person2);
        personArrayList.add(person3);
        personArrayList.add(person4);
        personArrayList.add(person5);

        // create file and print the names
        File file = new File(directory + fileName);
        FileWriter fileWriter = new FileWriter(file);

        writePersonsByName(personArrayList, fileWriter);
        writePersonsByAge(personArrayList, fileWriter);
        printCsvAsTxt(personArrayList, fileWriter);
        fileWriter.close();
        getPersonsWithLength4(personArrayList);
    }

    private void writePersonsByName(ArrayList<Person> persons, FileWriter fileWriter) throws IOException {
        // write the persons name on a file
        fileWriter.write("Print by Name: \n");
        persons
                .stream()
                .forEach(person -> {
                    try {
                        fileWriter.write(person.getFirstName() + "\n");
                    } catch (IOException e) {
                        log.error("Error encountered during write by Name: " + e);
                    }
                });
    }

    private void writePersonsByAge(ArrayList<Person> persons, FileWriter fileWriter) throws IOException {
        // implement a way to write in the b.txt only persons over a specified age
        List<Person> newList = persons
                .stream()
                .filter(person -> person.getAge() > age)
                .toList();

        fileWriter.write("\nPrint by Age: \n");
        newList.stream().forEach(person -> {
            try {
                fileWriter.write(person.getFirstName() + "\n");
            } catch (IOException e) {
                log.error("Error encountered during write by Age: " + e);
            }
        });
    }

    private void getPersonsWithLength4(ArrayList<Person> persons) {
        // log the count of names with length 4
        long count = persons
                .stream()
                .filter(person -> person.getFirstName().length() == 4)
                .count();
        log.info("Number of persons with names length(4): " + count);
    }

    private void printCsvAsTxt(ArrayList<Person> persons, FileWriter fileWriter) throws IOException {
        // update the above code to receive the name and age for each person and save them as CSV in b.txt file
        try(CSVWriter csvWriter = new CSVWriter(fileWriter)) {
            fileWriter.write("\nPrint in csv format:\n");
            ArrayList<String[]> csvData = new ArrayList<>();
            String[] header = {"Name", "Age"};
            csvData.add(header);
            persons.stream().forEach(person -> {
                String[] personTemp = {person.getFirstName(), String.valueOf(person.getAge())};
                csvData.add(personTemp);
            });
            csvWriter.writeAll(csvData);
        } catch (IOException e) {
            log.error("Error encountered during csv printing: " + e);
        }
    }
}
