/*
 * Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 *
 */

package com.jalasoft.devfund2.common.validation;

import com.jalasoft.devfund2.common.constant.ErrorMessageConstant;
import com.jalasoft.devfund2.common.exception.InvalidDataException;

import java.io.File;

/**
 * @author car
 * version 1.1
 **/

public class FileValidation implements IValidatorStrategy {

    private File file;
    private boolean isFile;

    public FileValidation(File file, boolean isFile){
        this.file = file;
        this.isFile = isFile;
    }

    @Override
    public void validate() throws InvalidDataException {
    if (this.isFile) {
        this.validateFile();
    } else {
        this.validateFolder();
        }
    }

    private void commonValidation() throws InvalidDataException {
        if (!this.file.exists() || this.file.isHidden() || this.file.toPath().toString().contains("..")) {
            throw new InvalidDataException(ErrorMessageConstant.FILE_ERROR_MESSAGE);
        }
    }

    private void validateFile() throws InvalidDataException {
        this.commonValidation();
        if (!this.file.isFile()){
            throw new InvalidDataException(ErrorMessageConstant.FILE_ERROR_MESSAGE);
        }
    }

    private void validateFolder() throws InvalidDataException {
        this.commonValidation();
        if (!this.file.isDirectory()){
            throw new InvalidDataException(ErrorMessageConstant.FOLDER_ERROR_MESSAGE);
        }
    }

}
