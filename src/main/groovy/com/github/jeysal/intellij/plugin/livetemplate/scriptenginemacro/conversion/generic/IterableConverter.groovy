package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.generic

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.Converter

/**
 * @author seckinger
 * @since 10/17/16
 */
trait IterableConverter<R> implements Converter<Iterable, R> {
    @Override
    R convert(final Iterable iterable) {
        convert(iterable.spliterator())
    }
}
