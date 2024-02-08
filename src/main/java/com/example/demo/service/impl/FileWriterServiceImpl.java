package com.example.demo.service.impl;

import com.example.demo.service.FileWriterService;
import com.opencsv.CSVWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FileWriterServiceImpl implements FileWriterService {

    @Value("${file.name}")
    private String fileName;

    @Value("${file.csvName}")
    private String csvName;

    @Value("${file.directory}")
    private String directory;

    @Value("${file.specificAge}")
    private int age;

    @Value("${file.characterLength}")
    private int characterLength;

    @Value("${file.headers}")
    private String[] headers;

    @Override
    public ArrayList<Person> createFile(ArrayList<Person> personArrayList) throws IOException {
        File file = new File(directory + fileName);
        FileWriter fileWriter = new FileWriter(file);

        fileWriter.write("\nPrint by Name: \n");
        printPersons(personArrayList, fileWriter);

        fileWriter.write("\nPrint by Age: \n");
        printPersons(
                personArrayList
                        .stream()
                        .filter(person -> person.age() > age)
                        .collect(Collectors.toCollection(ArrayList::new)
                        ), fileWriter);

        fileWriter.close();

        printCSV(personArrayList);

        log.info("Number of persons with names length(4): " + getPersonsWithLength(personArrayList));

        return personArrayList;
    }

    private void printPersons(ArrayList<Person> persons, FileWriter fileWriter) {
        persons
                .forEach(person -> {
                    try {
                        fileWriter.write(person.firstName() + "\n");
                    } catch (IOException e) {
                        log.error("Error encountered during writing: " + e);
                    }
                });
    }

    private long getPersonsWithLength(ArrayList<Person> persons) {
        return persons
                .stream()
                .filter(person -> person.firstName().length() == characterLength)
                .count();
    }

    private void printCSV(ArrayList<Person> persons) throws IOException {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(directory + csvName))) {
            ArrayList<String[]> csvData = new ArrayList<>();
            csvData.add(headers);

            persons.forEach(person -> {
                String[] personTemp = {person.firstName(), String.valueOf(person.age())};
                csvData.add(personTemp);
            });
            csvWriter.writeAll(csvData);
        } catch (IOException e) {
            log.error("Error encountered during csv printing: " + e);
            throw new IOException(e);
        }
    }
}
