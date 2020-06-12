/*
 * Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 *
 */

package com.jalasoft.devfund2.controller.request;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author car
 * version 1.1
 **/

public class RequestConvertFileParameter {
    private String outputFolder;
    private String md5;
    private String options;
    private MultipartFile file;

    public RequestConvertFileParameter(String outputFolder, String options, MultipartFile file) {
        this.outputFolder = outputFolder;
        this.options = options;
        this.file = file;
    }

    public String getOutputFolder() {
        return outputFolder;
    }

    public void setOutputFolder(String outputFolder) {
        this.outputFolder = outputFolder;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
