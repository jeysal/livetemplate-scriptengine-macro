package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.result

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.Converter
import com.intellij.codeInsight.template.Result

import java.util.stream.StreamSupport

/**
 * @author seckinger
 * @since 10/17/16
 */
trait SpliteratorResultConverter implements Converter<Object, Result> {
    @Override
    Result call(Object obj) {
        obj instanceof Spliterator ?
                call(StreamSupport.stream(obj, false).findFirst().orElse(null)) :
                super.call(obj)
    }
}
