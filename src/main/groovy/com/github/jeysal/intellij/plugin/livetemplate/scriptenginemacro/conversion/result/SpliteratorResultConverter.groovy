package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.result

import com.intellij.codeInsight.template.Result

import java.util.function.Function
import java.util.stream.StreamSupport

/**
 * @author seckinger
 * @since 10/17/16
 */
trait SpliteratorResultConverter implements Function<Object, Result> {
    @Override
    Result apply(final obj) {
        obj instanceof Spliterator ?
                apply(StreamSupport.stream(obj, false).findFirst().orElse(null)) :
                super.apply(obj)
    }
}
