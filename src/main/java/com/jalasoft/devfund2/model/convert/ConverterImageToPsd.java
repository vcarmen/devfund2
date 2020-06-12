/*
 * Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 *
 */

package com.jalasoft.devfund2.model.convert;

import com.aspose.imaging.Image;
import com.aspose.imaging.coreexceptions.ImageException;
import com.aspose.imaging.imageoptions.PsdOptions;
import com.jalasoft.devfund2.model.convert.exception.ConvertException;
import com.jalasoft.devfund2.model.convert.exception.ParameterInvalidException;
import com.jalasoft.devfund2.model.convert.parameter.ConvertImageParam;
import com.jalasoft.devfund2.model.convert.result.Result;

import java.util.Locale;

/**
 * @author car
 * version 1.1
 **/

public class ConverterImageToPsd implements IConverter<ConvertImageParam> {

    @Override
    public Result convert(ConvertImageParam param) throws ParameterInvalidException, ConvertException {
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
            return new Result(outputFile);
        } catch (ImageException ex){
            throw new ConvertException(ex);
        }
    }

}
