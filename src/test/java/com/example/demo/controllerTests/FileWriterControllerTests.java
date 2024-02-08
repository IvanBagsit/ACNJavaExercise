package com.example.demo.controllerTests;

import com.example.demo.controller.FileWriterController;
import com.example.demo.service.FileWriterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class FileWriterControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FileWriterController fileWriterController;

    @Test
    void createFileTest() {
        FileWriterService.Person person1 = new FileWriterService.Person("Mike", 21);
        FileWriterService.Person person2 = new FileWriterService.Person("Paul", 22);

        ArrayList<FileWriterService.Person> personArrayList = new ArrayList<>();
        personArrayList.add(person1);
        personArrayList.add(person2);

        ArrayList<FileWriterService.Person> response = fileWriterController.createFile(personArrayList).getBody();
        assertNotNull(response);
    }

    @Test
    void createFileBadRequestTest() throws Exception {
        mockMvc.perform(post("http://localhost:8080/fileWriter/createFile"))
                .andExpect(status().is4xxClientError());
    }

}
