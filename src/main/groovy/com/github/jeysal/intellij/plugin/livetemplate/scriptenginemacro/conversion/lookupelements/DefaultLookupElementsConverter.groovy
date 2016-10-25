package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.lookupelements

import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder

import java.util.function.Function

/**
 * @author seckinger
 * @since 10/18/16
 */
class DefaultLookupElementsConverter implements Function<Object, LookupElement[]> {
    @Override
    LookupElement[] apply(Object obj) {
        obj == null ?
                [] :
                [LookupElementBuilder.create(obj)]
    }
}
