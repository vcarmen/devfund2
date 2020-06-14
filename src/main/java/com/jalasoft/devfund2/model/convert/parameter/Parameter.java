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

public abstract class Parameter {
    protected File inputFile;

    public Parameter(File inputFile) {
        this.inputFile = inputFile;
    }

    public File getInputFile() {
        return inputFile;
    }

    public abstract void validate() throws ParameterInvalidException;

}
