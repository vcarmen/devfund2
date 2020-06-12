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

import com.aspose.pdf.Document;
import com.aspose.pdf.EpubLoadOptions;
import com.jalasoft.devfund2.model.convert.exception.ConvertException;
import com.jalasoft.devfund2.model.convert.exception.ParameterInvalidException;
import com.jalasoft.devfund2.model.convert.parameter.ConvertFileParam;
import com.jalasoft.devfund2.model.convert.result.Result;

/**
 * @author car
 * version 1.1
 **/

public class ConverterFileToPDF implements IConverter<ConvertFileParam>{
    @Override
    public Result convert(ConvertFileParam param) throws ParameterInvalidException, ConvertException {
        param.validate();
        String outputFileName = param.getInputFileBaseName();
        String outputFile = param.getOutputDir() + outputFileName + ".pdf";

        try {
            EpubLoadOptions optionsepub = new EpubLoadOptions();
            // Create Document object
            Document docepub = new Document(param.getInputFile().toString(), optionsepub);
            // Save the resultant PDF document
            docepub.save(outputFile);
            return new Result("converted");
        } catch (Exception ex) {
            throw new ConvertException(ex);
        }
    }
}
