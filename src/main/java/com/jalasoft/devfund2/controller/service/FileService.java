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
import com.jalasoft.devfund2.controller.constant.ErrorConstant;
import com.jalasoft.devfund2.controller.exception.FileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

/**
 * @author car
 * version 1.1
 **/

@Service
public class FileService {

    @Autowired
    private Properties properties;

    public File store(MultipartFile file, String md5) throws FileException{
        try {
            this.createOutPutFolder();
            Path path = this.getFilePath(file.getOriginalFilename());
            String currentMd5 = this.getMd5(path);
            this.validateMd5(currentMd5, md5);

            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return new File(path.toString());
        } catch (IOException ex) {
            throw new FileException(ErrorConstant.FILE_ERROR, ex);
        }
    }

    private void validateMd5(String currentMd5, String md5) throws  FileException{
        if (!currentMd5.equals(md5)){
            throw new FileException("Invalid file received");
        }
    }

    private String getMd5(Path path) throws FileException{
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(Files.readAllBytes(path));
            byte[] hash = messageDigest.digest();
            return DatatypeConverter.printHexBinary(hash).toUpperCase();
        } catch(NoSuchAlgorithmException | IOException ex){
            throw new FileException(ErrorConstant.FILE_ERROR, ex);
        }
    }

    private Path getFilePath(String fileInput) throws FileException{
        try {
            String inputFolder = properties.getInputFolder();
            Files.createDirectories(Paths.get(inputFolder));
            return Paths.get(inputFolder + fileInput);
        } catch (IOException ex){
            throw new FileException(ErrorConstant.FILE_ERROR, ex);
        }
    }

    private void createOutPutFolder() throws FileException{
        try {
            String outputDir = properties.getOutputFolder();
            Files.createDirectories(Paths.get(outputDir));
        } catch (IOException ex){
            throw new FileException(ErrorConstant.FILE_ERROR, ex);
        }
    }

    public String getDownloadLink(File file) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/download/")
                .path(file.getName())
                .toUriString();
    }
}
