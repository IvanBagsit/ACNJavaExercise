package com.example.demo.controller;

import com.example.demo.service.FileWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fileWriter")
@Slf4j
public class FileWriterController {

    private final FileWriterService fileWriterService;

    @PostMapping("/createFile")
    public String createFileByName() {

        // sonarlint: use logger instead of System.out
        log.info("createTxtFile started...");
        try {
            fileWriterService.createFile();
        } catch (IOException e) {
            log.error("Error encountered: " + e);
            return "Error encountered during file creation...";
        }
        log.info("createTxtFile ended...");
        return "createTxtFile ended...";
    }
}
