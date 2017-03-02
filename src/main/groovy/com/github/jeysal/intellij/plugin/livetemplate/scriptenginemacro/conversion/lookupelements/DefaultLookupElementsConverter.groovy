package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.lookupelements

import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder

/**
 * @author seckinger
 * @since 10/18/16
 */
class DefaultLookupElementsConverter implements LookupElementsConverter<Object> {
    @Override
    LookupElement[] convert(final obj) {
        obj == null ?
                [] :
                [LookupElementBuilder.create(obj)]
    }
}
