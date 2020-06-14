/*
 * Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 *
 */

package com.jalasoft.devfund2.controller.request;

import com.jalasoft.devfund2.common.exception.InvalidDataException;
import com.jalasoft.devfund2.common.validation.ConvertToValidation;
import com.jalasoft.devfund2.common.validation.IValidatorStrategy;
import com.jalasoft.devfund2.common.validation.MD5Validation;
import com.jalasoft.devfund2.common.validation.MimeTypeValidation;
import com.jalasoft.devfund2.common.validation.MultipartValidation;
import com.jalasoft.devfund2.common.validation.NotNullOrEmptyValidation;
import com.jalasoft.devfund2.common.validation.ValidationContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

/**
 * @author car
 * version 1.1
 **/

public class RequestConvertImageParameter extends RequestParameter{
    private String convertTo;
    private final static List<String> SUPPORTED_FORMATS = Arrays.asList("psd","bmp");

    public RequestConvertImageParameter(String convertTo, String md5, MultipartFile file) {
        super(md5, file);
        this.convertTo = convertTo;
    }

    public String getConvertTo() {
        return convertTo;
    }

    public void setConvertTo(String convertTo) {
        this.convertTo = convertTo;
    }

    @Override
    public void validate() throws InvalidDataException {
        List<IValidatorStrategy> strategyList = Arrays.asList(
                new NotNullOrEmptyValidation("md5", this.md5),
                new MD5Validation(this.md5),
                new MultipartValidation(this.file),
                new MimeTypeValidation(this.file.getContentType()),
                new NotNullOrEmptyValidation("convertTo", this.convertTo),
                new ConvertToValidation(this.convertTo.toLowerCase())
        );

        ValidationContext context = new ValidationContext(strategyList);
        context.validate();

    }
}
