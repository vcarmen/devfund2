/*
 * Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 *
 */

package com.jalasoft.devfund2.controller.service;

import com.jalasoft.devfund2.controller.component.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author car
 * version 1.1
 **/

@Service
public class FileService {

    @Autowired
    private Properties properties;

    public File store(MultipartFile file, String md5) throws Exception{
        if(md5.trim().isEmpty()){
            //return ResponseEntity.badRequest().body(new Response("","error md5", "400"));
            throw new Exception("md5 error");
        }
        /*if(md5.length() != 32){
            throw new Exception("md5 error");
        }*/

        String fileInput;
        String outputDir;

        String inputFolder = properties.getInputFolder();
        Files.createDirectories(Paths.get(inputFolder));
        fileInput = inputFolder + file.getOriginalFilename();

        Path path = Paths.get(fileInput);
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        outputDir = properties.getOutputFolder();
        Files.createDirectories(Paths.get(outputDir));

        return new File(fileInput);
    }
}
