package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion

/**
 * @author seckinger
 * @since 10/17/16
 */
interface Converter<T, R> {
    R call(T obj)
}
