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

import com.jalasoft.devfund2.model.convert.exception.ConvertException;
import com.jalasoft.devfund2.model.convert.exception.ParameterInvalidException;
import com.jalasoft.devfund2.model.convert.parameter.ConvertImageParam;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class ConverterImageToPsdTest {
    private final static String PATH = "src/test/resources/convertImage/";
    private final static String OUTPUT_PATH = "src/test/resources/convertImage/output/";

    @Test
    public void convertJPGValidImage() throws ParameterInvalidException, ConvertException {
        ConvertImageParam param = new ConvertImageParam(
                new File(PATH + "tree.jpg"),
                "psd",
                OUTPUT_PATH
        );

        ConverterImageToPsd con = new ConverterImageToPsd();
        assertEquals(OUTPUT_PATH+"tree.psd", con.convert(param).getText());
    }

    @Test(expected = ConvertException.class)
    public void convertTxtInvalidImage() throws ParameterInvalidException, ConvertException {
        ConvertImageParam param = new ConvertImageParam(
                new File(PATH + "test.txt"),
                "psd",
                OUTPUT_PATH
        );

        ConverterImageToPsd con = new ConverterImageToPsd();
        con.convert(param);
    }

    @Test(expected =  ParameterInvalidException.class)
    public void convertToInvalidFormat() throws ParameterInvalidException, ConvertException {
        ConvertImageParam param = new ConvertImageParam(
                new File(PATH + "tree.jpg"),
                "png",
                OUTPUT_PATH
        );

        ConverterImageToPsd con = new ConverterImageToPsd();
        con.convert(param);
    }

    @Test(expected = ParameterInvalidException.class)
    public void convertToEmptyFormat() throws ParameterInvalidException, ConvertException {
        ConvertImageParam param = new ConvertImageParam(
                new File(PATH + "tree.jpg"),
                "",
                OUTPUT_PATH
        );

        ConverterImageToPsd con = new ConverterImageToPsd();
        con.convert(param);
    }

    @Test(expected = ParameterInvalidException.class)
    public void convertNoExistenceInputFile() throws ParameterInvalidException, ConvertException {
        ConvertImageParam param = new ConvertImageParam(
                new File(PATH + "treeNo.jpg"),
                "psd",
                OUTPUT_PATH
        );

        ConverterImageToPsd con = new ConverterImageToPsd();
        con.convert(param);
    }

    @Test(expected = ParameterInvalidException.class)
    public void convertFolderAsInputFile() throws ParameterInvalidException, ConvertException {
        ConvertImageParam param = new ConvertImageParam(
                new File(PATH),
                "psd",
                OUTPUT_PATH
        );

        ConverterImageToPsd con = new ConverterImageToPsd();
        con.convert(param);
    }

    @Test(expected = ParameterInvalidException.class)
    public void convertInvalidParameter() throws ParameterInvalidException, ConvertException {
        ConverterImageToPsd con = new ConverterImageToPsd();
        con.convert(null);
    }
}