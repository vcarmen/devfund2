/*
 * Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 *
 */

package com.jalasoft.devfund2.model.convert.parameter;

import com.jalasoft.devfund2.model.convert.exception.ParameterInvalidException;

import java.io.File;

/**
 * @author car
 * version 1.1
 **/

public class ConvertFileParam extends Parameter{
    private String options;
    private String outputDir;

    public ConvertFileParam(File inputFile, String options, String outputDir) {
        super(inputFile);
        this.options = options;
        this.outputDir = outputDir;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    @Override
    public void validate() throws ParameterInvalidException {
        if (inputFile.isHidden()) {
            throw new ParameterInvalidException("file", inputFile.getName());
        }
        if (!inputFile.isFile()) {
            throw new ParameterInvalidException("file", inputFile.getName());
        }
        if (!inputFile.exists()){
            throw new ParameterInvalidException("file", inputFile.getName());
        }
    }

    public String getInputFileBaseName() {
        String fileName = this.inputFile.getName();
        int index = fileName.lastIndexOf('.');
        if (index == -1) {
            return fileName;
        } else {
            return fileName.substring(0, index);
        }
    }
}
