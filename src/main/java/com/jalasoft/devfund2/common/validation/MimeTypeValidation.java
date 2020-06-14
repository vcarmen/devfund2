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

public class MimeTypeValidation implements IValidatorStrategy {

    private String mimeType;
    private List<String> mimeTypeList;

    public MimeTypeValidation(String mimeType) throws InvalidDataException {
        this.mimeType = mimeType;
        this.mimeTypeList = ConfigurationProperty.getMimeType();
    }

    @Override
    public void validate() throws InvalidDataException {
        if (!mimeTypeList.contains(mimeType)){
            throw new InvalidDataException(ErrorMessageConstant.MIME_TYPE_ERROR_MESSAGE);
        }
    }
}
