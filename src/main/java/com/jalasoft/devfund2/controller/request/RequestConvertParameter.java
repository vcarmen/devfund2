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

import org.springframework.web.multipart.MultipartFile;

/**
 * @author car
 * version 1.1
 **/

public class RequestConvertParameter {
    private String convertTo;
    private String md5;
    private MultipartFile file;

    public RequestConvertParameter(String convertTo, String md5, MultipartFile file) {
        this.convertTo = convertTo;
        this.md5 = md5;
        this.file = file;
    }

    public String getConvertTo() {
        return convertTo;
    }

    public void setConvertTo(String convertTo) {
        this.convertTo = convertTo;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
