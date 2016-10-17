package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.generic

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.Converter

/**
 * @author seckinger
 * @since 10/17/16
 */
trait IterableConverter<R> implements Converter<Object, R> {
    R call(Object obj) {
        obj instanceof Iterable ?
                call(obj.spliterator()) :
                super.call(obj)
    }
}
