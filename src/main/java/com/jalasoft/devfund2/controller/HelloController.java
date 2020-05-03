package com.jalasoft.devfund2.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @PostMapping
    public String sayHello(@RequestParam(value="name") String name,
                           @RequestParam(value="lastName") String lname) {
        return ("Hello Work " + name + " " + lname);
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam(value="file") MultipartFile file) throws IOException {

        String inputFolderName = "inputFiles/";
        Files.createDirectories(Paths.get(inputFolderName));

        if (file.isEmpty()){
            return "Please, select a file";
        } else {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(inputFolderName + file.getOriginalFilename());
            Files.write(path, bytes);
        }

        return ("File uploaded");
    }
}
