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
import org.springframework.web.multipart.MultipartFile;

/**
 * @author car
 * version 1.1
 **/

public class MultipartValidation implements IValidatorStrategy {

    private MultipartFile file;

    public MultipartValidation(MultipartFile file) {
        this.file = file;
    }

    @Override
    public void validate() throws InvalidDataException {
        if (this.file == null || this.file.isEmpty() || this.file.getOriginalFilename().contains("..")){
            throw new InvalidDataException(ErrorMessageConstant.MULTIPART_ERROR_MESSAGE);
        }
    }
}
