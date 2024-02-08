package com.example.demo.controller;

import com.example.demo.service.FileWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fileWriter")
@Slf4j
public class FileWriterController {

    private final FileWriterService fileWriterService;

    @PostMapping("/createFile")
    public ResponseEntity<ArrayList<FileWriterService.Person>> createFileByName(@RequestBody ArrayList<FileWriterService.Person> personList) {
        log.info("createTxtFile started. Recieved list: " + personList.toString());
        try {
            return ResponseEntity.ok(fileWriterService.createFile(personList));
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
