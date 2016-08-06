package com.nazir.validate.common;

import org.apache.commons.lang3.StringUtils;

import com.nazir.utils.RegexUtils;

public class RegexValidate extends AbstractValidate {

    /**
     * 正则表达式规则
     */
    private String pattern;

    @Override
    protected boolean execute() {
        if (StringUtils.isBlank(value)) {
            return true;
        }

        if (StringUtils.isBlank(pattern)) {
            throw new RuntimeException("pattern is empty");
        }
        return RegexUtils.regexMatch(pattern, value);
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

}
