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

import com.jalasoft.devfund2.common.exception.InvalidDataException;

import java.util.List;

/**
 * @author car
 * version 1.1
 **/

public class ValidationContext {
    List<IValidatorStrategy> validationList;

    public ValidationContext(List<IValidatorStrategy> validationList) {
        this.validationList = validationList;
    }

    public void validate() throws InvalidDataException {
        for (IValidatorStrategy strategy : validationList){
            strategy.validate();
        }
    }
}
