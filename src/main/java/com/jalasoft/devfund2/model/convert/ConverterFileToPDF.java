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
import com.aspose.pdf.MarginInfo;
import com.jalasoft.devfund2.common.exception.InvalidDataException;
import com.jalasoft.devfund2.model.convert.exception.ConvertException;
import com.jalasoft.devfund2.model.convert.parameter.ConvertFileParam;
import com.jalasoft.devfund2.model.convert.result.Result;

/**
 * @author car
 * version 1.1
 **/

public class ConverterFileToPDF implements IConverter<ConvertFileParam>{
    @Override
    public Result convert(ConvertFileParam param) throws InvalidDataException, ConvertException {
        String outputFileName = param.getInputFileBaseName();
        String outputFile = param.getOutputDir() + outputFileName + ".pdf";

        try {
            param.validate();
            MarginInfo marginInfo = new MarginInfo();
            marginInfo.setBottom(1.0);
            marginInfo.setLeft(1.0);
            marginInfo.setRight(1.0);
            marginInfo.setTop(1.1);

            EpubLoadOptions optionsepub = new EpubLoadOptions();
            optionsepub.setMargin(marginInfo);
            // Create Document object
            Document docepub = new Document(param.getInputFile().toString(), optionsepub);
            // Save the resultant PDF document
            docepub.save(outputFile);
            return new Result("converted");
        } catch (NullPointerException ex){
            throw new InvalidDataException(ex);
        } catch (Exception ex) {
            throw new ConvertException(ex);
        }
    }
}
