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
import com.jalasoft.devfund2.model.convert.parameter.Parameter;
import com.jalasoft.devfund2.model.convert.result.Result;

public interface IConverter<T extends Parameter> {
    Result convert(T param) throws ParameterInvalidException, ConvertException;

}
