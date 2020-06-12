/*
 * Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 *
 */

package com.jalasoft.devfund2.model.convert.exception;

/**
 * @author car
 * version 1.1
 **/

public class ParameterInvalidException extends  Exception{

    private static final String GENERAL_MESSAGE = "Invalid parameter";
    private static final String FIELD_MESSAGE = "Invalid value = %s, on field = %s";

    public ParameterInvalidException(){
        super(GENERAL_MESSAGE);
    }

    public ParameterInvalidException(String currentMessage){
        super(currentMessage);
    }

    public ParameterInvalidException(Throwable ex){
        super(GENERAL_MESSAGE, ex);
    }

    public ParameterInvalidException(String value, String field ){
        super(String.format(FIELD_MESSAGE, value, field));
    }
}
