package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.lookupelements

import com.intellij.codeInsight.lookup.LookupElement

import java.util.function.Function

/**
 * @author seckinger
 * @since 10/22/16
 */
trait SingleLookupElementConverter implements Function<Object, LookupElement[]> {
    @Override
    LookupElement[] apply(final obj) {
        obj instanceof LookupElement ?
                [obj] as LookupElement[] :
                super.apply(obj)
    }
}
