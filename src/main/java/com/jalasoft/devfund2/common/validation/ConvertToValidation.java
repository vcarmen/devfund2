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

import com.jalasoft.devfund2.common.configuration.ConfigurationProperty;
import com.jalasoft.devfund2.common.constant.ErrorMessageConstant;
import com.jalasoft.devfund2.common.exception.InvalidDataException;

import java.util.List;

/**
 * @author car
 * version 1.1
 **/

public class ConvertToValidation implements IValidatorStrategy{

    private String convertTo;
    private List<String> supportedFormats;

    public ConvertToValidation(String convertTo) throws InvalidDataException{
        this.convertTo = convertTo;
        this.supportedFormats = ConfigurationProperty.getSupportedFormats();
    }

    @Override
    public void validate() throws InvalidDataException {
        if (!supportedFormats.contains(convertTo)){
            throw new InvalidDataException(ErrorMessageConstant.CONVERT_TO_ERROR_MESSAGE);
        }
    }
}
