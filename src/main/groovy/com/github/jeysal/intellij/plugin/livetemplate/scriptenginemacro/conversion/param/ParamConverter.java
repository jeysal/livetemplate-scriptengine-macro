package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.param;

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.Converter;
import com.intellij.codeInsight.template.Result;

/**
 * @author seckinger
 * @since 1/6/17
 */
@FunctionalInterface
public interface ParamConverter extends Converter<Result, Object> {
}
