package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.generic

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.Converter

/**
 * @author seckinger
 * @since 10/17/16
 */
trait IteratorConverter<R> implements Converter<Iterator, R> {
    @Override
    R convert(final Iterator iterator) {
        convert(Spliterators.spliteratorUnknownSize(iterator, 0))
    }
}
