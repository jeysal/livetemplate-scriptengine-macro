package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.result;

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.Converter;
import com.intellij.codeInsight.template.Result;

/**
 * @author seckinger
 * @since 1/6/17
 */
@FunctionalInterface
public interface ResultConverter<T> extends Converter<T, Result> {
}
