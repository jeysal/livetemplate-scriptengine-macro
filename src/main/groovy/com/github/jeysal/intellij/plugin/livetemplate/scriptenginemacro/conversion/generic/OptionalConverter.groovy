package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.generic

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.Converter

/**
 * @author seckinger
 * @since 10/17/16
 */
trait OptionalConverter<R> implements Converter<Object, R> {
    R convert(final obj) {
        obj instanceof Optional ?
                convert(obj.orElse('empty')) :
                super.convert(obj)
    }
}
