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

import com.jalasoft.devfund2.controller.exception.RequestParamInvalidException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

/**
 * @author car
 * version 1.1
 **/

public class RequestConvertImageParameter extends RequestParameter{
    private String convertTo;
    private final static List<String> SUPPORTED_FORMATS = Arrays.asList("pds","bmp");

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
    public void validate() throws RequestParamInvalidException {
        if (this.md5 == null || this.md5.trim().isEmpty()){
            throw new RequestParamInvalidException("md5 is null or empty");
        }
        if (!this.md5.matches("[a-fA-F0-9]{32}")){
            throw new RequestParamInvalidException("md5 is invalid");
        }
        if (this.file == null || this.file.isEmpty()){
            throw new RequestParamInvalidException("file is null or empty");
        }
        if (this.file.getContentType() == null || !this.file.getContentType().startsWith("image")){
            throw new RequestParamInvalidException("invalid file format");
        }
        if (this.file.getName().contains("..")){
            throw new RequestParamInvalidException("invalid file name");
        }
        if (this.convertTo == null || this.convertTo.trim().isEmpty()){
            throw new RequestParamInvalidException("convertTo is null or empty");
        }
        if (!SUPPORTED_FORMATS.contains(convertTo)){
            throw new RequestParamInvalidException("convertTo is not allowed");
        }
    }
}
