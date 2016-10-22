package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.lookupelements

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.Converter
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder

/**
 * @author seckinger
 * @since 10/18/16
 */
class DefaultLookupElementsConverter implements Converter<Object, LookupElement[]> {
    @Override
    LookupElement[] call(Object obj) {
        obj == null ?
                [] :
                [LookupElementBuilder.create(obj)]
    }
}
