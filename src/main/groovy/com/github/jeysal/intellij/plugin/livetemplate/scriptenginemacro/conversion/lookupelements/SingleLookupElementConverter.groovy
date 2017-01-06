package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.lookupelements

import com.intellij.codeInsight.lookup.LookupElement

/**
 * @author seckinger
 * @since 10/22/16
 */
trait SingleLookupElementConverter implements LookupElementsConverter {
    @Override
    LookupElement[] convert(final obj) {
        obj instanceof LookupElement ?
                [obj] as LookupElement[] :
                super.convert(obj)
    }
}
