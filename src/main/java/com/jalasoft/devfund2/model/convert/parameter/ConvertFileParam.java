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

import com.jalasoft.devfund2.common.exception.InvalidDataException;
import com.jalasoft.devfund2.common.validation.FileValidation;
import com.jalasoft.devfund2.common.validation.IValidatorStrategy;
import com.jalasoft.devfund2.common.validation.NotNullOrEmptyValidation;
import com.jalasoft.devfund2.common.validation.ValidationContext;

import java.io.File;
import java.util.Arrays;
import java.util.List;

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
    public void validate() throws InvalidDataException {

        List<IValidatorStrategy> strategyList = Arrays.asList(
                new FileValidation(this.inputFile, true),
                new NotNullOrEmptyValidation("outputDir", this.outputDir),
                new FileValidation(new File(this.outputDir), false),
                new NotNullOrEmptyValidation("options", this.options)
        );

        ValidationContext context = new ValidationContext(strategyList);
        context.validate();
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
