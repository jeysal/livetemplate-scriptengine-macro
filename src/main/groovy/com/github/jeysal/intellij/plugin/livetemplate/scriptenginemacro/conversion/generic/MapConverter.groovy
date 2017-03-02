package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.generic

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.Converter

/**
 * @author seckinger
 * @since 10/17/16
 */
trait MapConverter<R> implements Converter<Map, R> {
    @Override
    R convert(final Map map) {
        convert(map.values().spliterator())
    }
}
