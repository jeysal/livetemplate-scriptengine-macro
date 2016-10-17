package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.generic

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.Converter

/**
 * @author seckinger
 * @since 10/17/16
 */
trait IteratorConverter<R> implements Converter<Object, R> {
    R call(Object obj) {
        obj instanceof Iterator ?
                call(Spliterators.spliteratorUnknownSize(obj, 0)) :
                super.call(obj)
    }
}
