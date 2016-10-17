package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.generic

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.Converter

import java.util.stream.BaseStream

/**
 * @author seckinger
 * @since 10/17/16
 */
trait BaseStreamConverter<R> implements Converter<Object, R> {
    R call(Object obj) {
        obj instanceof BaseStream ?
                call(obj.spliterator()) :
                super.call(obj)
    }
}
