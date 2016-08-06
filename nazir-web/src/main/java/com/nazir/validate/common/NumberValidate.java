package com.nazir.validate.common;

import org.apache.commons.lang3.StringUtils;

import com.nazir.utils.RegexUtils;

public class NumberValidate extends AbstractValidate {

    @Override
    protected boolean execute() {
        if (StringUtils.isBlank(value)) {
            return true;
        }
        String pattern = "^\\d+$";
        return RegexUtils.regexMatch(pattern, value);
    }

}
