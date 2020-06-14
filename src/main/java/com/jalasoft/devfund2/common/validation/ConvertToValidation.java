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

import java.util.Arrays;
import java.util.List;

/**
 * @author car
 * version 1.1
 **/

public class ConvertToValidation implements IValidatorStrategy{

    private String convertTo;
    private final static List<String> SUPPORTED_FORMATS = Arrays.asList("psd", "pdf");

    public ConvertToValidation(String convertTo) {
        this.convertTo = convertTo;
    }

    @Override
    public void validate() throws InvalidDataException {
        if (!SUPPORTED_FORMATS.contains(convertTo)){
            throw new InvalidDataException(ErrorMessageConstant.CONVERT_TO_ERROR_MESSAGE);
        }
    }
}
