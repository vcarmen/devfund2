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

import java.awt.image.ImagingOpException;
import java.io.File;
import java.util.Locale;

/**
 * @aythor car
 * version 1.1
 **/

public class Converter {

    public String convert(String inputFile, String convertTo, String outputDir){
        Locale locale = new Locale("en", "US");
        Locale.setDefault(locale);

        String outputFileName = this.getBaseName(new File(inputFile).getName());
        String outputFile = outputDir + outputFileName+"."+convertTo.toLowerCase();

        try {
            // load document for conversion
            Image img = Image.load(inputFile);
            // convert to PSD Conversion
            img.save(outputFile, new PsdOptions());
            return "converted";

        } catch (ImageException ex){
            return "error";
        }
    }

    public static String getBaseName(String fileName) {
        int index = fileName.lastIndexOf('.');
        if (index == -1) {
            return fileName;
        } else {
            return fileName.substring(0, index);
        }
    }
}
