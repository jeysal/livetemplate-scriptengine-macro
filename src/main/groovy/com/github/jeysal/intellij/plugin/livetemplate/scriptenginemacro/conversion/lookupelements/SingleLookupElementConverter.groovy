package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.lookupelements

import com.intellij.codeInsight.lookup.LookupElement

/**
 * @author seckinger
 * @since 10/22/16
 */
trait SingleLookupElementConverter implements LookupElementsConverter<LookupElement> {
    @Override
    LookupElement[] convert(final LookupElement elem) {
        [elem] as LookupElement[]
    }
}
