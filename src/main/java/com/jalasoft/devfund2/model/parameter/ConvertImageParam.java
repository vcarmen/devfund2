/*
 * Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.devfund2.model.parameter;

import java.io.File;

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
    public void validate() throws Exception {
        super.validate();
        if (this.convertTo.trim().isEmpty()) {
            throw new Exception("error convertTo");
        }
        if (!"psd".equals(this.convertTo.toLowerCase())) {
            throw new Exception("error convertTo not valid");
        }
    }
}
