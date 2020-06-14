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

/**
 * @author car
 * version 1.1
 **/

public class RequestConvertFileParameter extends RequestParameter{
    private String options;

    public RequestConvertFileParameter(String md5, MultipartFile file, String options) {
        super(md5, file);
        this.options = options;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
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
        if (this.options == null){
            throw new RequestParamInvalidException("options is null");
        }
    }
}
