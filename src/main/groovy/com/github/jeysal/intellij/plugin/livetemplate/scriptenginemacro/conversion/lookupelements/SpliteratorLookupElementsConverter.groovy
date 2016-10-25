package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.lookupelements

import com.intellij.codeInsight.lookup.LookupElement

import java.util.function.Function
import java.util.stream.Collectors
import java.util.stream.StreamSupport

/**
 * @author seckinger
 * @since 10/22/16
 */
trait SpliteratorLookupElementsConverter implements Function<Object, LookupElement[]> {
    @Override
    LookupElement[] apply(final obj) {
        obj instanceof Spliterator ?
                StreamSupport.stream(obj, false).map(this.&apply).collect(Collectors.toList()).flatten() as LookupElement[] :
                super.apply(obj)
    }
}
