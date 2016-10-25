package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.generic

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.Converter

/**
 * @author seckinger
 * @since 10/17/16
 */
trait MapConverter<R> implements Converter<Object, R> {
    R apply(Object obj) {
        obj instanceof Map ?
                apply(obj.entrySet().spliterator()) :
                super.apply(obj)
    }
}
