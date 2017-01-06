package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.lookupelements;

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.Converter;
import com.intellij.codeInsight.lookup.LookupElement;

/**
 * @author seckinger
 * @since 1/6/17
 */
@FunctionalInterface
public interface LookupElementsConverter extends Converter<Object, LookupElement[]> {
}
