package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.generic

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.Converter

/**
 * @author seckinger
 * @since 10/17/16
 */
trait OptionalConverter<R> implements Converter<Optional, R> {
    R convert(final Optional optional) {
        convert(optional.orElse('empty'))
    }
}
