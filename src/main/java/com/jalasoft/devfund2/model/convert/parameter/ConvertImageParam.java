/*
 * Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.devfund2.model.convert.parameter;

import com.jalasoft.devfund2.common.exception.InvalidDataException;
import com.jalasoft.devfund2.common.validation.ConvertToValidation;
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

public class ConvertImageParam extends Parameter{
    private String convertTo;
    private String outputDir;

    public ConvertImageParam(File inputFile, String convertTo, String outputDir) {
        super(inputFile);
        this.convertTo = convertTo;
        this.outputDir = outputDir;
    }

    public String getConvertTo() {
        return convertTo;
    }

    public void setConvertTo(String convertTo) {
        this.convertTo = convertTo;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    @Override
    public void validate() throws InvalidDataException {

        if (this.convertTo.trim().isEmpty()){
            System.out.println("convertTo: "+ this.convertTo);
            System.out.println("convertTo is empty");
        }

        List<IValidatorStrategy> strategyList = Arrays.asList(
                new FileValidation(this.inputFile, true),
                new NotNullOrEmptyValidation("outputDir", this.outputDir),
                new FileValidation(new File(this.outputDir), false),
                new NotNullOrEmptyValidation("convertTo", this.convertTo),
                new ConvertToValidation(this.convertTo)
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
