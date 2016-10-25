package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.generic

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.Converter

/**
 * @author seckinger
 * @since 10/17/16
 */
trait IteratorConverter<R> implements Converter<Object, R> {
    R apply(Object obj) {
        obj instanceof Iterator ?
                apply(Spliterators.spliteratorUnknownSize(obj, 0)) :
                super.apply(obj)
    }
}
