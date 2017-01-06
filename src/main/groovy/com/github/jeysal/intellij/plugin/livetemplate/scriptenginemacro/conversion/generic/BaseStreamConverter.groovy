package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.generic

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.Converter

import java.util.stream.BaseStream

/**
 * @author seckinger
 * @since 10/17/16
 */
trait BaseStreamConverter<R> implements Converter<Object, R> {
    R convert(final obj) {
        obj instanceof BaseStream ?
                convert(obj.spliterator()) :
                super.convert(obj)
    }
}
