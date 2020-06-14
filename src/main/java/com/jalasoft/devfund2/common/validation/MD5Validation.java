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

/**
 * @author car
 * version 1.1
 **/

public class MD5Validation implements IValidatorStrategy{

    private String md5;
    public final static String REGEX_MD5 = "[a-fA-F0-9]{32}";

    public MD5Validation(String md5) {
        this.md5 = md5;
    }

    @Override
    public void validate() throws InvalidDataException {
        if (!this.md5.matches(REGEX_MD5)){
            throw new InvalidDataException(ErrorMessageConstant.MD5_ERROR_MESSAGE);
        }
    }
}
