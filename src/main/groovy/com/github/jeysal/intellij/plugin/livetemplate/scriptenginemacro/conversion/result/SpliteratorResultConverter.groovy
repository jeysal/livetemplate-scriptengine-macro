package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.result

import com.intellij.codeInsight.template.Result

import java.util.stream.StreamSupport

/**
 * @author seckinger
 * @since 10/17/16
 */
trait SpliteratorResultConverter implements ResultConverter {
    @Override
    Result convert(final obj) {
        obj instanceof Spliterator ?
                convert(StreamSupport.stream(obj, false).findFirst().orElse(null)) :
                super.convert(obj)
    }
}
