/*
 * Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 *
 */

package com.jalasoft.devfund2.common.configuration;

import com.jalasoft.devfund2.common.constant.PropertyConstant;
import com.jalasoft.devfund2.common.exception.InvalidDataException;

import java.util.List;

/**
 * @author car
 * version 1.1
 **/

public class ConfigurationProperty {
    public static List<String> getSupportedFormats() throws InvalidDataException{
        return PropertyHandler.getInstance().getValueAsList(PropertyConstant.CONVERT_IMAGE_SUPPORTED_CONVERT_TO);
    }

    public static List<String> getMimeType() throws InvalidDataException {
        return PropertyHandler.getInstance().getValueAsList(PropertyConstant.CONVERT_IMAGE_MIME_TYPE);
    }
}
