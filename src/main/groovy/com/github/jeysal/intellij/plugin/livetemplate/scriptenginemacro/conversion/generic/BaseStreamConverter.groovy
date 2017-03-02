package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.generic

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.Converter

import java.util.stream.BaseStream

/**
 * @author seckinger
 * @since 10/17/16
 */
trait BaseStreamConverter<R> implements Converter<BaseStream, R> {
    @Override
    R convert(final BaseStream stream) {
        convert(stream.spliterator())
    }
}
