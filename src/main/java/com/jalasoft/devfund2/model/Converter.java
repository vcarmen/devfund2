/*
 * Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 *
 */

package com.jalasoft.devfund2.model;

import com.aspose.imaging.IImageCreator;
import com.aspose.imaging.Image;
import com.aspose.imaging.coreexceptions.ImageException;
import com.aspose.imaging.imageoptions.PsdOptions;
import com.jalasoft.devfund2.model.parameter.ConvertImageParam;

import java.util.Locale;

/**
 * @author car
 * version 1.1
 **/

public class Converter {

    public String convert(ConvertImageParam param) throws Exception{
        Locale locale = new Locale("en", "US");
        Locale.setDefault(locale);

        param.validate();
        String outputFileName = param.getInputFileBaseName();
        String outputFile = param.getOutputDir() + outputFileName+"."+param.getConvertTo().toLowerCase();

        try {
            // load document for conversion
            Image img = Image.load(param.getInputFile().toString());
            // convert to PSD Conversion
            img.save(outputFile, new PsdOptions());
            return "converted";

        } catch (ImageException ex){
            throw new Exception(ex.getMessage());
        }
    }

}
