package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.lookupelements

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.Converter
import com.intellij.codeInsight.lookup.LookupElement

import java.util.stream.Collectors
import java.util.stream.StreamSupport

/**
 * @author seckinger
 * @since 10/22/16
 */
trait SpliteratorLookupElementsConverter implements Converter<Object, LookupElement[]> {
    @Override
    LookupElement[] call(Object obj) {
        obj instanceof Spliterator ?
                StreamSupport.stream(obj, false).map(this.&call).collect(Collectors.toList()).flatten() as LookupElement[] :
                super.call(obj)
    }
}
